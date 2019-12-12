package com.trying.myapplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
    private static final byte[] keyValue =
            new byte[]{'c', 'o', 'd', 'i', 'n', 'g', 'a', 'n', 'd', 'r', 'o', 'i', 'd', 'c', 'o', 'm'};

    public static String encrypt(String cleartext)
            throws Exception {
        long startTime = System.nanoTime();
        byte[] rawKey = get_raw_key();
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        long endTime = System.nanoTime();
        long distance = (endTime - startTime) / 1000000;
        return byte_to_hex(result);
    }

    public static String decrypt(String encrypted)
            throws Exception {

        byte[] encryptedBytes = hex_to_byte(encrypted);
        byte[] result = decrypt(encryptedBytes);
        return new String(result);
    }

    private static byte[] get_raw_key() throws Exception {
        SecretKey key = new SecretKeySpec(keyValue, "AES");
        byte[] raw = key.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKey skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(clear);   //return encrypted text
    }

    private static byte[] decrypt(byte[] encrypted)
            throws Exception {
        SecretKey skeySpec = new SecretKeySpec(keyValue, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(encrypted);   //return the decrypted text in bytes
    }

    public static byte[] hex_to_byte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }

    public static String byte_to_hex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            append_hex(result, buf[i]);
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void append_hex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}