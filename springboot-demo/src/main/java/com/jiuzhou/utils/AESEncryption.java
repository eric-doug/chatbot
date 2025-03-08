package com.jiuzhou.utils;

import com.alibaba.fastjson.JSON;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES加密工具类
 */
public class AESEncryption {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static String encrypt(String plainText, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decode = Base64.getDecoder().decode(cipherText);
        byte[] plainText = cipher.doFinal(decode);
        return new String(plainText, StandardCharsets.UTF_8);
    }


    public static String  addEncrypt( Object o,String key){
        try {
            String result = AESEncryption.encrypt(JSON.toJSONString(o), key);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        String plainText = "{\"groupName\":\"1\",\"userId\":1}";
        String key = "mN4Yn8Or8r7SH1w3";
        String cipherText = encrypt(plainText, key);
        System.out.println("Cipher text: " + cipherText);
        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted text: " + decryptedText);
    }
}