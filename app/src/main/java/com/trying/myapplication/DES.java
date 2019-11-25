/*
package com.trying.myapplication;

import android.content.Context;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {

    static String encrypt(String value, Context c) {

        //App.Loger("Encrypt Started ...");

        String crypted = "";

        try {


            byte[] cleartext = value.getBytes("UTF-8");

           */
/* KeyGenerator keygen = KeyGenerator.getInstance("DES");
            keygen.init(56);
            byte[] keyz = keygen.generateKey().getEncoded();*//*


            String desKey = "0123456789abcdef"; // value from user
            byte[] keyBytes =(desKey).getBytes();

            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey key = factory.generateSecret(new DESKeySpec(keyBytes));

            //SecretKeySpec key = new SecretKeySpec(keyz, "DES");

            Cipher cipher = Cipher.getInstance("DES/CBC/ZeroBytePadding");

            // Initialize the cipher for decryption
            cipher.init(Cipher.ENCRYPT_MODE, key);

            crypted = Base64.encodeToString(cipher.doFinal(cleartext),Base64.DEFAULT);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error1";
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error2";
        }
        catch (IllegalBlockSizeException e) {
            e.printStackTrace();
           // App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error3";
        }
        catch (BadPaddingException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error4";
        }
        catch (InvalidKeyException e) {
            e.printStackTrace();
           // App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error5";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          //  App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error6";
        }
        catch (Exception e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Encrypt Error","Error" + "\n" + e.getMessage());
            return "Encrypt Error7";
        }

       // App.Loger("Encrypt Finished ...");

        //return "code==" + crypted;
        return crypted;
    }

    static String decrypt(String value,Context c) {

        //App.Loger("Decrypt Started ...");

        String coded;
        if(value.startsWith("code==")){
            coded = value.substring(6,value.length()).trim();
        }else{
            coded = value.trim();
        }

        String result = null;

        try {


            String desKey = "0123456789abcdef"; // value from user
            byte[] keyBytes =(desKey).getBytes();

            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey key = factory.generateSecret(new DESKeySpec(keyBytes));

            // Decoding base64
            //byte[] bytesDecoded = Base64.decode(coded.getBytes("UTF-8"),Base64.DEFAULT);

            //SecretKeySpec key = new SecretKeySpec(value.getBytes(), "DES");

            Cipher cipher = Cipher.getInstance("DES/ECB/ZeroBytePadding");

            // Initialize the cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Decrypt the text
            byte[] textDecrypted = cipher.doFinal(bytesDecoded);

            result = new String(textDecrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error";
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error1";
        }
        catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error2";
        }
        catch (BadPaddingException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error3";
        }
        catch (InvalidKeyException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error4";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error5";
        }
        catch (Exception e) {
            e.printStackTrace();
            //App.DialogMaker(c,"Decrypt Error","Erorr:" + "\n" + e.getMessage());
            return "Decrypt Error6";
        }

        //App.Loger("Decrypt Finished ...");
        return result;
    }
}*/
