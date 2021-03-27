package com.iorichina.utils.cipher;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 基础加密组件
 *
 * @author linsongzheng
 * @version 1.0
 * @since 1.0
 */
public abstract class Hmac {
    public static final String ENCODING = "UTF-8";
    public static final String ENC_NAME_HMAC_SHA1 = "HmacSHA1";

    public static String Encrypt(String sourceText, String key, String mode) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        return HmacEncrypt(sourceText, key.getBytes(ENCODING), "Hmac" + mode);
    }

    public static String Sha1Encrypt(String sourceText, String key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        return HmacEncrypt(sourceText, key.getBytes(ENCODING), ENC_NAME_HMAC_SHA1);
    }

    private static String HmacEncrypt(String sourceText, byte[] SecretKeyData, String encMode) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(SecretKeyData, encMode);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(encMode);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = sourceText.getBytes(ENCODING);
        //完成 Mac 操作
        //第三方SDK给过来的算法是 (new BASE64Encoder()).encode(mac.doFinal(text));
        return new String(Base64.getEncoder().encode(mac.doFinal(text)), StandardCharsets.UTF_8);
    }
}