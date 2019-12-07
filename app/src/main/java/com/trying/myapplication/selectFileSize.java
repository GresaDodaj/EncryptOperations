package com.trying.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.google.firebase.firestore.SetOptions.*;

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
        final long[] timeLength3DES = new long[1];
        final long[] timeLengthBfish = new long[1];
        final long[] timeLengthAES = new long[1];
        final FirebaseFirestore database = FirebaseFirestore.getInstance();


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
                         timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLengthAES[0] + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_1KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength3DES[0] + " milliseconds");

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
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");

                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }


                } catch (Exception e) { int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBfish[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        myPhone.put("AES",timeLength1);
                    if(timeLength2!=0)
                        myPhone.put("3DES", timeLength2);
                    if(timeLength3!=0)
                        myPhone.put("BLOWFISH", timeLength3);
                    DocumentReference _1kbRef = database.collection("myPhone").document("_1KB");
                    _1kbRef.update(myPhone);

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
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLengthAES[0] + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_5KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength3DES[0] + " milliseconds");


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
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }


                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBfish[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        myPhone.put("AES",timeLength1);
                    if(timeLength2!=0)
                        myPhone.put("3DES", timeLength2);
                    if(timeLength3!=0)
                        myPhone.put("BLOWFISH", timeLength3);
                    DocumentReference _5kbRef = database.collection("myPhone").document("_5KB");
                    _5kbRef.update(myPhone);  //update TODO: handle the failing case
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
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLengthAES[0] + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_10KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength3DES[0] + " milliseconds");
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
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }

                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBfish[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        myPhone.put("AES",timeLength1);
                    if(timeLength2!=0)
                        myPhone.put("3DES", timeLength2);
                    if(timeLength3!=0)
                        myPhone.put("BLOWFISH", timeLength3);
                    DocumentReference _10kbRef = database.collection("myPhone").document("_10KB");
                    _10kbRef.update(myPhone);
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
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLengthAES[0] + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_50KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int)timeLength3DES[0] + " milliseconds");
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
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }

                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBfish[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        myPhone.put("AES",timeLength1);
                    if(timeLength2!=0)
                        myPhone.put("3DES", timeLength2);
                    if(timeLength3!=0)
                        myPhone.put("BLOWFISH", timeLength3);
                    DocumentReference _50kbRef = database.collection("myPhone").document("_50KB");
                    _50kbRef.update(myPhone);
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
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLengthAES[0] + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_100KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int) timeLength3DES[0]+ " milliseconds");
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
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }

                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBfish[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        myPhone.put("AES",timeLength1);
                    if(timeLength2!=0)
                        myPhone.put("3DES", timeLength2);
                    if(timeLength3!=0)
                        myPhone.put("BLOWFISH", timeLength3);
                    DocumentReference _100kbRef = database.collection("myPhone").document("_100KB");
                    _100kbRef.update(myPhone);
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
                        String text3 = "";
                        long startTime = System.nanoTime();

                        for (int i = 0; i < 10; i++) {
                             text3 += AES.encrypt(_10KBfile);
                        }
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int)timeLengthAES[0] + " milliseconds");
                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            byte[] encryptResult = new byte[0];
                            String enc ="";
                            long startTime = System.nanoTime();

                            for(int i = 0; i < 10 ; i++) {
                                 encryptResult = tripleDES.encrypt(_10KBfile.getBytes(), tripleKey);
                                 enc += encryptResult.toString();
                            }
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int)timeLength3DES[0]+ " milliseconds");
                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _10KBfile.getBytes();
                            byte[] encryptedBytes= new byte[0];
                            String encryptedData = "";

                            long startTime = System.nanoTime();
                            for(int i = 0; i < 10 ; i++) {
                               encryptedBytes =  blowfish.encrypt(text2Bytes);
                               encryptedData += encryptedBytes ;
                            }
                            long endTime = System.nanoTime();
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                             encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }
                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBfish[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        myPhone.put("AES",timeLength1);
                    if(timeLength2!=0)
                        myPhone.put("3DES", timeLength2);
                    if(timeLength3!=0)
                        myPhone.put("BLOWFISH", timeLength3);
                    DocumentReference _1mbRef = database.collection("myPhone").document("_1MB");
                    _1mbRef.update(myPhone);
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





