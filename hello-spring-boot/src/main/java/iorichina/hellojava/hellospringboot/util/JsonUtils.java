/*
 *  created by iorichina 20-10-29 下午2:44
 */

package iorichina.hellojava.hellospringboot.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {
    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        //禁止序列化空值
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        // 禁止遇到空原始类型时抛出异常，用默认值代替。
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        // 禁止遇到未知（新）属性时报错，支持兼容扩展
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    private JsonUtils() {
    }

    public static String toJsonString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("toJsonString throws {}", e.toString(), e);
        }
        return null;
    }

    public static <T> T convertValue(Object obj, TypeReference<T> toValueTypeRef) {
        try {
            return OBJECT_MAPPER.convertValue(obj, toValueTypeRef);
        } catch (Exception e) {
            log.error("convertValue throws {}", e.toString(), e);
        }
        return null;
    }

    public final static String ERROR_MESSAGE = "Exception thrown while parsing ObjectMapper.";

    /**
     * 将String类型的json数据解析为{@code List}集合， 可以指定集合元素类型
     *
     * @param data 需要解析的内容
     * @param type 需要解析的对象类型
     * @param <T>  集合元素的类型
     * @return 一个 {@link List}，如果出现异常则返回null.
     */
    public static <T> List<T> parseList(byte[] data, Class<T> type) {
        try {
            CollectionType collectionType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, type);
            return OBJECT_MAPPER.readValue(data, collectionType);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

    public static <T> T parseObject(byte[] data, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(data, type);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

    public static <T> T parseObject(String data, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(data, type);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

    public static <T> T parseObject(String data, TypeReference<T> toValueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(data, toValueTypeRef);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }
    public static <T> T parseObject(byte[] data, TypeReference<T> toValueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(data, toValueTypeRef);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

    public static JsonNode parseObject(byte[] data) {
        try {
            return OBJECT_MAPPER.readTree(data);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

    public static JsonNode parseObject(String data) {
        try {
            return OBJECT_MAPPER.readTree(data);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

    /**
     * 将byte类型的json数据解析为{@code Map}集合， 可以指定集合元素类型
     *
     * @param data      需要解析的内容
     * @param keyType   需要解析Map集合Key的类型
     * @param valueType 需要解析Map集合Value的类型
     * @param <K>       Map集合Key的类型
     * @param <V>       Map集合Value的类型
     * @return 一个 {@link Map}，当解析产生异常而{@param swallowException}为false的时候则会将异常抛出，
     * 客户方需要捕获异常，为true则会吞掉异常，返回null
     */
    public static <K, V> Map<K, V> parseMap(byte[] data, Class<K> keyType, Class<V> valueType) {
        MapType mapType = OBJECT_MAPPER.getTypeFactory().constructMapType(HashMap.class, keyType, valueType);
        try {
            return OBJECT_MAPPER.readValue(data, mapType);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
        }
        return null;
    }

}
