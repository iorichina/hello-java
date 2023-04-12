/*
 * Created by iorichina 21-8-9 下午7:04
 */

package iorichina.hellojava.hellospringboot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ThriftUtils {

    /**
     * 输出经过简单压缩的二进制字符
     */
    public static String toCompat(TBase<?, ?> base) {
        try {
            return new TSerializer(new TCompactProtocol.Factory()).toString(base, StandardCharsets.UTF_8.displayName());//ISO_8859_1
        } catch (Exception e) {
            log.error("序列化thrift对象报错:{}:{}:{}", base, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    public static <T extends TBase<?, ?>> T fromCompat(T base, String data) {
        try {
            new TDeserializer(new TCompactProtocol.Factory()).deserialize(base, data, StandardCharsets.UTF_8.displayName());
            return base;
        } catch (Exception e) {
            log.error("反序列化thrift对象报错:{}:{}:{}", data, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    /**
     * 输出二进制字符
     */
    public static String toString(TBase<?, ?> base) {
        try {
            return new TSerializer().toString(base, StandardCharsets.UTF_8.displayName());//ISO_8859_1
        } catch (Exception e) {
            log.error("序列化thrift对象报错:{}:{}:{}", base, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    public static <T extends TBase<?, ?>> T fromString(T base, String data) {
        try {
            new TDeserializer().deserialize(base, data, StandardCharsets.UTF_8.displayName());
            return base;
        } catch (Exception e) {
            log.error("反序列化thrift对象报错:{}:{}:{}", data, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    /**
     * 输出仅thrift可读的json字符串
     */
    public static String toThriftJson(TBase<?, ?> base) {
        try {
            return new TSerializer(new TJSONProtocol.Factory()).toString(base, StandardCharsets.UTF_8.displayName());
        } catch (Exception e) {
            log.error("json化thrift对象报错:{}:{}:{}", base, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    public static <T extends TBase<?, ?>> T fromThriftJson(T base, String data) {
        try {
            new TDeserializer(new TJSONProtocol.Factory()).deserialize(base, data, StandardCharsets.UTF_8.displayName());
            return base;
        } catch (Exception e) {
            log.error("反json化thrift对象报错:{}:{}:{}", data, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    /**
     * 输出所有语言通用的json字符串
     */
    public static String toJson(TBase<?, ?> base) {
        try {
            return new TSerializer(new TSimpleJSONProtocol.Factory()).toString(base, StandardCharsets.UTF_8.displayName());
        } catch (Exception e) {
            log.error("jackson化thrift对象报错:{}:{}:{}", base, e.getClass().getSimpleName(), e.toString());
            return null;
        }
    }

    public static <T extends org.apache.thrift.TServiceClient> T client(Class<T> clazz, String url, int connectTimeout, int readTimeout) throws TTransportException {
        // HttpClient
        HttpClient httpClient = createHttpClient(connectTimeout, readTimeout);

        THttpClient transport = new THttpClient(url, httpClient);
        transport.setConnectTimeout(connectTimeout);
        transport.setReadTimeout(readTimeout);

        //transport.setCustomHeader("protocol", "json");
        //TProtocol protocol = new TJSONProtocol(transport);
        TProtocol protocol = new TBinaryProtocol(transport);

        T client;
        try {
            client = clazz.getConstructor(TProtocol.class).newInstance(protocol);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        transport.open();

        return client;
    }

    protected static CloseableHttpClient createHttpClient(int connectTimeout, int socketTimeout) {
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .setSoTimeout(socketTimeout)
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .build();

        BasicHttpClientConnectionManager connectManager = new BasicHttpClientConnectionManager();
        connectManager.setSocketConfig(socketConfig);

        return HttpClients.custom()
                .setConnectionManager(connectManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

    }

    public static String getCurl(String url, TBase<?, ?> obj) {
        String curlBody = toThriftJson(obj);
        return "curl -H 'protocol:json' -H 'Content-Type:application/x-thrift' -d '" + curlBody + "' '" + url + "'";
    }

}
