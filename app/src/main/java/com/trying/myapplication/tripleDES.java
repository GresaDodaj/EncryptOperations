package com.trying.myapplication;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class  tripleDES{

    private static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168); //112 ose 168
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    static void encrypt(byte[] input) throws Exception {
        byte [] key = initKey();
        SecretKey secretKey = new SecretKeySpec(key, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipher.doFinal(input);
    }

  /*  static byte[] decrypt(byte[] encryptedInput, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");  //modi cbc, padding:
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedInput);
    }*/

    /*static String byte_to_string(byte[] result_in_bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte resultByte : result_in_bytes) {
            if (Integer.toHexString(0xFF & resultByte).length() == 1) {
                builder.append(0).append(Integer.toHexString(0xFF & resultByte));
            } else {
                builder.append(Integer.toHexString(0xFF & resultByte));
            }
        }
        return builder.toString();
    }*/
}
