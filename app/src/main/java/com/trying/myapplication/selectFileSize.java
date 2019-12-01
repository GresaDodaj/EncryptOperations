package com.trying.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class selectFileSize extends AppCompatActivity {

    private static final String TAG = "err";

    public selectFileSize()  {
    }
    private static final String LABEL = "GRESA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file_size);
        Button btn1KB = findViewById(R.id.btn1KB);
        Button btn5KB = findViewById(R.id.btn5KB);
        Button btn10KB = findViewById(R.id.btn10KB);
        Button btn50KB = findViewById(R.id.btn50KB);
        Button btn100KB = findViewById(R.id.btn100KB);
        Button btn1MB = findViewById(R.id.btn1MB);
        Button btnEncrypt = findViewById(R.id.btnEncrypt);
        final TextView txt2 = findViewById(R.id.txt2);
        final TextView txt1 = findViewById(R.id.txtView);
        final String _1KBfile = ReadFile.readFile("_1KBfile.txt", selectFileSize.this);
        final String _5KBfile = ReadFile.readFile("_5KBfile.txt", selectFileSize.this);
        final String _10KBfile = ReadFile.readFile("_10KBfile.txt", selectFileSize.this);
        final String _50KBfile = ReadFile.readFile("_50KBfile.txt", selectFileSize.this);
        final String _100KBfile = ReadFile.readFile("_100KBfile.txt", selectFileSize.this);
        final String _1MBfile = ReadFile.readFile("_1MBfile.txt", selectFileSize.this);
        Bundle var = getIntent().getExtras();
        final String alg = var.getString("ALG");
       // file = new File(Environment.getExternalStorageDirectory(), "_1KBfile.txt");
        txt2.setMovementMethod(new ScrollingMovementMethod());
        btn1KB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_1KBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        long timeLength = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLength + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_1KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength + " milliseconds");
                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _1KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLength + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn5KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_5KBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        long timeLength = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLength + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_5KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength + " milliseconds");


                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")) {
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _5KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLength + " milliseconds");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn10KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_10KBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        long timeLength = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLength + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_10KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength + " milliseconds");
                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }

                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _10KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLength + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn50KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_50KBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        long timeLength = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLength + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_50KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength + " milliseconds");
                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _50KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLength + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_50KBfile.txt",selectFileSize.this);
            }
        });


        btn100KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_100KBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        long timeLength = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLength + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_100KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength + " milliseconds");
                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _100KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLength + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String test123 = ReadFile.readFile("_100KBfile.txt",selectFileSize.this);
            }
        });

        btn1MB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_1MBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        long timeLength = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLength + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_1MBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength + " milliseconds");
                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _1MBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            long timeLength = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLength + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_1MBfile.txt",selectFileSize.this);
            }
        });
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    long time = encryptFile();

                    txt2.setText((int) time+" milliseconds");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_1MBfile.txt",selectFileSize.this);
            }
        });

    }




    public long encryptFile() throws Exception {

        long startTime = System.nanoTime();
        String test1 = AES.encrypt("GRESA");
        long endTime = System.nanoTime();

        //time of the execution in nanoseconds/1000000 = time in milliseconds
        long timeLength = (endTime - startTime)/1000000;

            return timeLength;
            }



}





