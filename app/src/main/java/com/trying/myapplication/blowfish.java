package com.trying.myapplication;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class blowfish {
    static byte[] secretKeyBytes = new byte[1000];
    static String secretKeyString;
    static byte[] raw;

    static void generate_symetric_key() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            secretKeyBytes = get_raw_key(knumb);
            secretKeyString = new String(secretKeyBytes);  //blowfish secret key

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    private static byte[] get_raw_key(byte[] seed) throws Exception {
        KeyGenerator key = KeyGenerator.getInstance("Blowfish");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG"); //Pseudo Random Number Generator (PRNG) algorithm
        secureRandom.setSeed(seed);
        key.init(128, secureRandom); // 128, 256 and 448 bits may not be available
        SecretKey secretKey = key.generateKey();
        raw = secretKey.getEncoded();
        return raw;
    }
    static byte[] encrypt(byte[] input) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(raw, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(input);  //return the encrypted text
    }

    static byte[] decrypt(byte[] encrypted) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(raw, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encrypted);  //return the decrypted text
    }

}
