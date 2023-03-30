package iorichina.hellojava.hellospringboot.util;

import io.netty.handler.codec.http.HttpHeaders;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public final class NetworkUtils {
    /**
     * Logger for this class
     */
    private static final Logger log = LoggerFactory.getLogger(NetworkUtils.class);

    /**
     * 可用于sdk中thrift接口获取客户端ip地址
     */
    public static String getClientIpAddress() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (null == request) {
            return null;
        }
        return getClientIpAddress(request);
    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        String from = "X-Forwarded-For";

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
                from = "X-Real-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-SyncClient-IP");
                from = "Proxy-SyncClient-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                from = "Proxy-Client-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                from = "WL-Proxy-Client-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                from = "HTTP_CLIENT_IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_REAL_IP");
                from = "HTTP_X_REAL_IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                from = "HTTP_X_FORWARDED_FOR";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                from = "RemoteAddr";
            }
        }
        if (ip.contains(",")) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.warn("getClientIpAddress(HttpServletRequest) - [{}] - String ip=[{}]", from, ip);
        }
        return ip;
    }

    public static Pair<String, String> getWsIpFromHeaders(HttpHeaders headers, io.netty.channel.Channel channel) {
        String ip = headers.get("X-Forwarded-For");
        String from = "X-Forwarded-For";
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("X-Real-IP");
                from = "X-Real-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("Proxy-SyncClient-IP");
                from = "Proxy-SyncClient-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("Proxy-Client-IP");
                from = "Proxy-Client-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("WL-Proxy-Client-IP");
                from = "WL-Proxy-Client-IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("HTTP_CLIENT_IP");
                from = "HTTP_CLIENT_IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("HTTP_X_REAL_IP");
                from = "HTTP_X_REAL_IP";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = headers.get("HTTP_X_FORWARDED_FOR");
                from = "HTTP_X_FORWARDED_FOR";
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                String[] split = channel.remoteAddress().toString().split("/");
                ip = split.length > 1 ? split[1].split(":")[0] : "";
                from = "RemoteAddr";
            }
        }
        if (ip.contains(",")) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return Pair.of(ip, from);
    }
}