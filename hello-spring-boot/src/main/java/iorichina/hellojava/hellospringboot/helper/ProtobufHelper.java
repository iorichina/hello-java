package iorichina.hellojava.hellospringboot.helper;

import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;
import com.googlecode.protobuf.format.JsonJacksonFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
public class ProtobufHelper {
    static JsonJacksonFormat jacksonFormat = new JsonJacksonFormat();

    public static <T extends Message> String toJson(T obj) {
        try {
            return jacksonFormat.printToString(obj);
        } catch (Exception e) {
            log.error("JsonJacksonFormat.printToString {} throw {}", TextFormat.shortDebugString(obj), e.toString(), e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Message> T fromJson(byte[] obj, T.Builder target) {
        ByteArrayInputStream in = new ByteArrayInputStream(obj);
        try {
            jacksonFormat.merge(in, target);
        } catch (IOException e) {
            log.error("JsonJacksonFormat.merge {} throw {}", target.build().getClass().getSimpleName(), e.toString(), e);
            return null;
        }
        return (T) target.build();
    }

}
