/*
package com.trying.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class selectFileSize extends AppCompatActivity {

    private static final String TAG = "err";



    public selectFileSize()  {
    }
    private static final String LABEL = "GRESA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file_size);
        Button btn10KB = findViewById(R.id.btn10KB);
        Button btn500KB = findViewById(R.id.btn500KB);
        Button btn100KB = findViewById(R.id.btn100KB);
        Button btn1MB = findViewById(R.id.btn1MB);
        Button btnEncrypt = findViewById(R.id.btnEncrypt);
        final TextView txt2 = findViewById(R.id.txt2);
        final TextView txt1 = findViewById(R.id.txtView);
        final String _10KBfile = ReadFile.readFile("_10KBfile.txt", selectFileSize.this);
        final String _500KBfile = ReadFile.readFile("_500KBfile.txt", selectFileSize.this);
        final String _100KBfile = ReadFile.readFile("_100KBfile.txt", selectFileSize.this);
        final String _1MBfile = ReadFile.readFile("_1MBfile.txt", selectFileSize.this);
        Bundle var = getIntent().getExtras();
        final String alg = var.getString("ALG");
        final long[] timeLength3DES = new long[1];
        final long[] timeLengthBfish = new long[1];
        final long[] timeLengthAES = new long[1];
        final long[] averageAES = new long[1];
        final long[] average3DES = new long[1];
        final long[] averageBLOWFISH= new long[1];
        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference ref = database.collection("Averages").document();
        String myId = ref.getId();
        final DocumentReference D = database.collection("Averages").document(myId);
        final DocumentReference overallAvg = database.collection("Averages").document("avg");

       // file = new File(Environment.getExternalStorageDirectory(), "_10KBfile.txt");
        txt2.setMovementMethod(new ScrollingMovementMethod());
        final Map<String, Object> data = new HashMap<>();



        btn10KB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
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

                        averageAES[0] = (averageAES[0] + timeLengthAES[0])/2;


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

                            average3DES[0] = (average3DES[0] + timeLength3DES[0])/2;

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


                            averageBLOWFISH[0] = (averageBLOWFISH[0]+timeLengthBfish[0])/2;

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

                    int average_AES = (int) averageAES[0]/2;
                    int average_3DES = (int)average3DES[0]/2;
                    int average_blowfish = (int)averageBLOWFISH[0]/2;

                    Map<String, Object> overallAverage = new HashMap<>();
                    if(average_AES!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        overallAverage.put("AES",average_AES);
                    if(average_3DES!=0)
                        overallAverage.put("3DES", average_3DES);
                    if(average_blowfish!=0)
                        overallAverage.put("BLOWFISH", average_blowfish);

                    overallAvg.update(overallAverage);

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

                        averageAES[0] = (averageAES[0] + timeLengthAES[0])/2;

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

                            average3DES[0] = (average3DES[0] + timeLength3DES[0])/2;

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

                            averageBLOWFISH[0] = (averageBLOWFISH[0]+timeLengthBfish[0])/2;
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

                    int average_AES = (int) averageAES[0]/2;
                    int average_3DES = (int)average3DES[0]/2;
                    int average_blowfish = (int)averageBLOWFISH[0]/2;
                    Map<String, Object> overallAverage = new HashMap<>();
                    if(average_AES!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        overallAverage.put("AES",average_AES);
                    if(average_3DES!=0)
                        overallAverage.put("3DES", average_3DES);
                    if(average_blowfish!=0)
                        overallAverage.put("BLOWFISH", average_blowfish);

                    overallAvg.update(overallAverage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn500KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_500KBfile);
                        //String text4 = AES.decrypt(text3);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        txt1.setText(text3);
                        txt2.setText((int) timeLengthAES[0] + " milliseconds");

                        averageAES[0] = (averageAES[0] + timeLengthAES[0])/2;


                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_500KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            txt1.setText(encrypted);
                            txt2.setText((int)timeLength3DES[0] + " milliseconds");


                            average3DES[0] = (average3DES[0] + timeLength3DES[0])/2;

                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _500KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes);
                            long endTime = System.nanoTime();
                            timeLengthBfish[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            txt1.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            txt2.setText((int) timeLengthBfish[0] + " milliseconds");

                            averageBLOWFISH[0] = (averageBLOWFISH[0]+timeLengthBfish[0])/2;
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
                    DocumentReference _500kbRef = database.collection("myPhone").document("_500kb");
                    _500kbRef.update(myPhone);



                    int average_AES = (int) averageAES[0]/2;
                    int average_3DES = (int)average3DES[0]/2;
                    int average_blowfish = (int)averageBLOWFISH[0]/2;
                    Map<String, Object> overallAverage = new HashMap<>();
                    if(average_AES!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        overallAverage.put("AES",average_AES);
                    if(average_3DES!=0)
                        overallAverage.put("3DES", average_3DES);
                    if(average_blowfish!=0)
                        overallAverage.put("BLOWFISH", average_blowfish);

                    overallAvg.update(overallAverage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_500KBfile.txt",selectFileSize.this);
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

                        averageAES[0] = (averageAES[0] + timeLengthAES[0])/2;

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


                            average3DES[0] = (average3DES[0] + timeLength3DES[0])/2;
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

                            averageBLOWFISH[0] = (averageBLOWFISH[0]+timeLengthBfish[0])/2;
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

                    int average_AES = (int) averageAES[0]/2;
                    int average_3DES = (int)average3DES[0]/2;
                    int average_blowfish = (int)averageBLOWFISH[0]/2;
                    Map<String, Object> overallAverage = new HashMap<>();
                    if(average_AES!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        overallAverage.put("AES",average_AES);
                    if(average_3DES!=0)
                        overallAverage.put("3DES", average_3DES);
                    if(average_blowfish!=0)
                        overallAverage.put("BLOWFISH", average_blowfish);

                    overallAvg.update(overallAverage);
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

                        averageAES[0] = (averageAES[0] + timeLengthAES[0])/2;

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

                            average3DES[0] = (average3DES[0] + timeLength3DES[0])/2;
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

                            averageBLOWFISH[0] = (averageBLOWFISH[0]+timeLengthBfish[0])/2;
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


                    int average_AES = (int) averageAES[0]/2 ;
                    int average_3DES = (int)average3DES[0]/2;
                    int average_blowfish = (int)averageBLOWFISH[0]/2;
                    Map<String, Object> overallAverage = new HashMap<>();
                    if(average_AES!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        overallAverage.put("AES",average_AES);
                    if(average_3DES!=0)
                        overallAverage.put("3DES", average_3DES);
                    if(average_blowfish!=0)
                        overallAverage.put("BLOWFISH", average_blowfish);

                    overallAvg.update(overallAverage);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });





        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                try {

                    */
/*int count = 0;


                    String id = null;
                    if (count == 0) {

                        id = database.collection("Averages").document().getId();
                    }
                    if (averageAES[0] != 0)
                        data.put("AES", averageAES[0]);
                    if (average3DES[0] != 0)
                        data.put("3DES", average3DES[0]);
                    if (averageBLOWFISH[0] != 0)
                        data.put("BLOWFISH", averageBLOWFISH[0]);

                    database.collection("Averages").document(id).update(data);
                    count += 1;*//*




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

    public long average(long time1)  {


        return time1/6;

    }

}





*/
