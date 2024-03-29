package com.trying.myapplication;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class DES {
    static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(64);//should be 16 or 24 bytes / 112 or 168 bits
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    static byte[] encrypt(byte[] input, byte[] key) throws Exception {

        SecureRandom secureRandom = new SecureRandom();
        SecretKey secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS7Padding");

        byte[] iv = new byte[cipher.getBlockSize()];
        secureRandom.nextBytes(iv);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivParameterSpec);

        return cipher.doFinal(input);
    }

    static byte[] decrypt(byte[] encryptedInput, byte[] key, byte[] iv) throws Exception {



        SecretKey secretKey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));


        return cipher.doFinal(encryptedInput);
    }

    static String byte_to_string(byte[] result_in_bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte resultByte : result_in_bytes) {
            if (Integer.toHexString(0xFF & resultByte).length() == 1) {
                builder.append(0).append(Integer.toHexString(0xFF & resultByte));
            } else {
                builder.append(Integer.toHexString(0xFF & resultByte));
            }
        }
        return builder.toString();
    }

}
