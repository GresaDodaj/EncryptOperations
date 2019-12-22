package com.trying.myapplication;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class blowfish {
    private static byte[] raw;

    static void generate_symmetric_key() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            byte[] secretKeyBytes = get_raw_key(knumb);
            String secretKeyString = new String(secretKeyBytes);  //blowfish secret key

        }
        catch(Exception e) { e.printStackTrace(); }
    }
    private static byte[] get_raw_key(byte[] seed) throws Exception {
        KeyGenerator key = KeyGenerator.getInstance("Blowfish");
        key.init(448); // 128, 256 and 448 bits may not be available
        SecretKey secretKey = key.generateKey();
        raw = secretKey.getEncoded();
        return raw;
    }
    static void encrypt(byte[] input) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(raw, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipher.doFinal(input);
    }

  /*  static byte[] decrypt(byte[] encrypted) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(raw, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encrypted);  //return the decrypted text
    }*/

}
