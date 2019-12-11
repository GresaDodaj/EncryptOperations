package com.trying.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class EncryptionAlgorithm extends AppCompatActivity {
    static int[] counterAES = {0};
    static int[] counter3DES = {0};
    static int[] counterBLOWFISH = {0};

    static long timeAverage = 0;

    static long timeAverageAES=0;

/*    static long timeAverageAESPRE = 0;
    static long timeAverage3DESPRE = 0;
    static long timeAverageBLOWFISHPRE = 0;

    static long timeAverageAESPOST = 0;
    static long timeAverage3DESPOST = 0;
    static long timeAverageBLOWFISHPOST = 0;*/

    static long timeAES5kb = 0;
    static long timeAES100kb = 0;
    static long timeAES500kb = 0;
    static long timeAES1mb = 0;

    static long time3DES5kb = 0;
    static long time3DES100kb = 0;
    static long time3DES500kb = 0;
    static long time3DES1mb = 0;

    static long timeBLOWFISH5kb = 0;
    static long timeBLOWFISH100kb = 0;
    static long timeBLOWFISH500kb = 0;
    static long timeBLOWFISH1mb = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_algorithm);

        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        //DocumentReference _5kbRef = database.collection("myPhone").document("_5KB");
        final CollectionReference dbAES = database.collection("overallAverage").document("avg").collection("AES");
        final CollectionReference db3DES = database.collection("overallAverage").document("avg").collection("3DES");
        final CollectionReference dbBLOWFISH = database.collection("overallAverage").document("avg").collection("BLOWFISH");
        final CollectionReference db_5KB_AES = database.collection("myPhone").document("_5KB").collection("AES");
        final CollectionReference db_5KB_3DES = database.collection("myPhone").document("_5KB").collection("3DES");
        final CollectionReference db_5KB_BLOWFISH = database.collection("myPhone").document("_5KB").collection("BLOWFISH");
        final DocumentReference db_average_aes = database.collection("Average_Collection").document("AES");
        final DocumentReference db_average_3des = database.collection("Average_Collection").document("3DES");
        final DocumentReference db_average_blowfish = database.collection("Average_Collection").document("BLOWFISH");


        Button btn5KB = findViewById(R.id.btn5kb);
        Button btn100KB = findViewById(R.id.btn100kb);
        Button btn500KB = findViewById(R.id.btn500kb);
        Button btn1MB = findViewById(R.id.btn1mb);
        Button btnchart = findViewById(R.id.btnChart);
        final TextView avgResult = findViewById(R.id.avgResult);
        final TextView avgPreCache = findViewById(R.id.avgPreCache);

        final TextView time5KB = findViewById(R.id.timeAES5kb); //TODO: boj pa algoritem varesisht cili caktohet me intent qata me qit te e njejta view time5kb
        final TextView time100KB = findViewById(R.id.timeAES100kb);
        final TextView time500KB = findViewById(R.id.timeAES500kb);
        final TextView time1MB = findViewById(R.id.timeAES1mb);


        Bundle var = getIntent().getExtras();
        final String alg = var.getString("ALG");
        final TextView algorithmTxt = findViewById(R.id.algorithmTxt);
        algorithmTxt.setText(alg);

        final String _5KBfile = ReadFile.readFile("_5KBfile.txt", EncryptionAlgorithm.this);
        final String _100KBfile = ReadFile.readFile("_100KBfile.txt", EncryptionAlgorithm.this);
        final String _500KBfile = ReadFile.readFile("_500KBfile.txt", EncryptionAlgorithm.this);
        final String _1MBfile = ReadFile.readFile("_1MBfile.txt", EncryptionAlgorithm.this);

        final long[] timeLength3DES = new long[1];
        final long[] timeLengthBLOWFISH = new long[1];
        final long[] timeLengthAES = new long[1];




        btn5KB.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "--";
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        //String text3 = AES.encrypt(_5KBfile);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        time5KB.setText((int) timeLengthAES[0] + " milliseconds");
                        timeAES5kb = timeLengthAES[0];
                        myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            //String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_5KBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                           //String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                           //byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                           // String data = (LABEL + "3DES: " + new String(decryptResult));
                           //txt1.setText(encrypted);
                            time5KB.setText((int) timeLength3DES[0] + " milliseconds");
                            time3DES5kb = timeLength3DES[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                        } catch (Exception e) { Log.e(TAG, "3DES: " + e.getMessage()); }
                    }
                    else if (alg.equals("BLOWFISH")){
                        try {
                            blowfish.generate_symetric_key();
                            byte[] text2Bytes = _5KBfile.getBytes();
                            long startTime = System.nanoTime();
                            byte[] encryptedBytes = blowfish.encrypt(text2Bytes); //todo: mos e assign hiq direkt veq thirre funksionin/
                            long endTime = System.nanoTime();
                            timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;
                            //String encryptedData = new String(encryptedBytes);
                            //time5KB.setText(encryptedData);
                            //byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            //String decryptedData = new String(decryptedBytes );
                            time5KB.setText((int) timeLengthBLOWFISH[0] + " milliseconds");
                            timeBLOWFISH5kb = timeLengthBLOWFISH[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                        }
                        catch(Exception e) { System.out.println(e); } //todo: ma mire formulo errorin
                    }

                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBLOWFISH[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                    {
                        myPhone.put("AES", timeLength1);
                        db_5KB_AES.add(myPhone);
                    }
                    if(timeLength2!=0) {
                        myPhone.put("3DES", timeLength2);
                        db_5KB_3DES.add(myPhone);
                    }
                    if(timeLength3!=0){
                        myPhone.put("BLOWFISH", timeLength3);
                        db_5KB_BLOWFISH.add(myPhone);
                    }

                    timeAverage = average(time5KB.getText().toString(),time100KB.getText().toString(),
                            time500KB.getText().toString(),time1MB.getText().toString(),algorithmTxt.getText().toString());

                    if(timeAverage!=0){

                        if(counter3DES[0] == 1 || counterAES[0] == 1 || counterBLOWFISH[0] == 1)
                        { avgPreCache.setText("PRE-CACHE AVERAGE: "+ (int)timeAverage + " milliseconds\n");
                        }
                        else
                        { avgResult.setText("POST-CACHE AVERAGE: " + (int) timeAverage + " milliseconds"); }

                        Map<String, Object> averageDB = new HashMap<>();
                        final Map<String, Object> average_collection = new HashMap<>();
                        if(alg.equals("AES"))
                        {

                            averageDB.put("AES",timeAverage);
                            dbAES.add(averageDB);
                            dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_aes = 0;
                                        int  num_of_docs_aes = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_aes; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                            sum_aes += Integer.parseInt(a);
                                        }
                                        int avg_aes_db = sum_aes / num_of_docs_aes;
                                        average_collection.put("AES",avg_aes_db);
                                        db_average_aes.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }
                        else if(alg.equals("3DES")){
                            averageDB.put("3DES",timeAverage);
                            db3DES.add(averageDB);

                            db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_3des = 0;
                                        int  num_of_docs_3des = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_3des; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                            sum_3des += Integer.parseInt(a);
                                        }
                                        int avg_3des_db = sum_3des / num_of_docs_3des;

                                        average_collection.put("3DES",avg_3des_db);
                                        db_average_3des.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }
                        else if(alg.equals("BLOWFISH")){
                            averageDB.put("BLOWFISH",timeAverage);
                            dbBLOWFISH.add(averageDB);

                            dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_blowfish = 0;
                                        int  num_of_docs_blowfish = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_blowfish; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                            sum_blowfish += Integer.parseInt(a);
                                        }
                                        int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                        average_collection.put("BLOWFISH",avg_blowfish_db);
                                        db_average_blowfish.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }



                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn100KB.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "--";
            private static final String LABEL = "Algoritmi: " ;

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_100KBfile);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/1000000 = time in milliseconds
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        //txt1.setText(text3);
                        time100KB.setText((int) timeLengthAES[0] + " milliseconds");
                        timeAES100kb = timeLengthAES[0];
                        myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

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
                            //  txt1.setText(encrypted);
                            time100KB.setText((int) timeLength3DES[0] + " milliseconds");

                            time3DES100kb = timeLength3DES[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

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
                            timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            time100KB.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            time100KB.setText((int) timeLengthBLOWFISH[0] + " milliseconds");

                            timeBLOWFISH100kb = timeLengthBLOWFISH[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);


                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }


                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBLOWFISH[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                    {
                        myPhone.put("AES", timeLength1);
                        CollectionReference _100KbRef = database.collection("myPhone").document("_100KB").collection("AES");
                        _100KbRef.add(myPhone);
                    }
                    if(timeLength2!=0) {
                        myPhone.put("3DES", timeLength2);
                        CollectionReference _100KbRef = database.collection("myPhone").document("_100KB").collection("3DES");
                        _100KbRef.add(myPhone);
                    }
                    if(timeLength3!=0){
                        myPhone.put("BLOWFISH", timeLength3);
                        CollectionReference _100KbRef = database.collection("myPhone").document("_100KB").collection("BLOWFISH");
                        _100KbRef.add(myPhone);
                    }


                    timeAverage = average(time5KB.getText().toString(),time100KB.getText().toString(),
                            time500KB.getText().toString(),time1MB.getText().toString(),algorithmTxt.getText().toString());

                    if(timeAverage!=0){

                        if(counter3DES[0] == 1 || counterAES[0] == 1 || counterBLOWFISH[0] == 1)
                        { avgPreCache.setText("PRE-CACHE AVERAGE: "+ (int)timeAverage + " milliseconds\n"); }
                        else
                        { avgResult.setText("POST-CACHE AVERAGE: " + (int) timeAverage + " milliseconds"); }

                        Map<String, Object> averageDB = new HashMap<>();
                        final Map<String, Object> average_collection = new HashMap<>();
                        if(alg.equals("AES"))
                        {
                            averageDB.put("AES",timeAverage);
                            dbAES.add(averageDB);
                            dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_aes = 0;
                                        int  num_of_docs_aes = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_aes; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                            sum_aes += Integer.parseInt(a);
                                        }
                                        int avg_aes_db = sum_aes / num_of_docs_aes;
                                        average_collection.put("AES",avg_aes_db);
                                        db_average_aes.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }
                        else if(alg.equals("3DES")){
                            averageDB.put("3DES",timeAverage);
                            db3DES.add(averageDB);

                            db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_3des = 0;
                                        int  num_of_docs_3des = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_3des; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                            sum_3des += Integer.parseInt(a);
                                        }
                                        int avg_3des_db = sum_3des / num_of_docs_3des;

                                        average_collection.put("3DES",avg_3des_db);
                                        db_average_3des.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }
                        else if(alg.equals("BLOWFISH")){
                            averageDB.put("BLOWFISH",timeAverage);
                            dbBLOWFISH.add(averageDB);

                            dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_blowfish = 0;
                                        int  num_of_docs_blowfish = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_blowfish; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                            sum_blowfish += Integer.parseInt(a);
                                        }
                                        int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                        average_collection.put("BLOWFISH",avg_blowfish_db);
                                        db_average_blowfish.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }



                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn500KB.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "--";
            private static final String LABEL = "Algoritmi: " ;

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {



                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_500KBfile);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/5000000 = time in milliseconds
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        //txt1.setText(text3);
                        time500KB.setText((int) timeLengthAES[0] + " milliseconds");

                        timeAES500kb = timeLengthAES[0];

                        myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
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
                            //  txt1.setText(encrypted);
                            time500KB.setText((int) timeLength3DES[0] + " milliseconds");

                            time3DES500kb = timeLength3DES[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
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
                            timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            time500KB.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            time500KB.setText((int) timeLengthBLOWFISH[0] + " milliseconds");

                            timeBLOWFISH500kb = timeLengthBLOWFISH[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }


                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBLOWFISH[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                    {
                        myPhone.put("AES", timeLength1);
                        CollectionReference _500KbRef = database.collection("myPhone").document("_500KB").collection("AES");
                        _500KbRef.add(myPhone);
                    }
                    if(timeLength2!=0) {
                        myPhone.put("3DES", timeLength2);
                        CollectionReference _500KbRef = database.collection("myPhone").document("_500KB").collection("3DES");
                        _500KbRef.add(myPhone);
                    }
                    if(timeLength3!=0){
                        myPhone.put("BLOWFISH", timeLength3);
                        CollectionReference _500KbRef = database.collection("myPhone").document("_500KB").collection("BLOWFISH");
                        _500KbRef.add(myPhone);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                timeAverage = average(time5KB.getText().toString(),time100KB.getText().toString(),
                        time500KB.getText().toString(),time1MB.getText().toString(),algorithmTxt.getText().toString());

                if(timeAverage!=0){

                    if(counter3DES[0] == 1 || counterAES[0] == 1 || counterBLOWFISH[0] == 1)
                    { avgPreCache.setText("PRE-CACHE AVERAGE: "+ (int)timeAverage + " milliseconds\n"); }
                    else
                    { avgResult.setText("POST-CACHE AVERAGE: " + (int) timeAverage + " milliseconds"); }

                    Map<String, Object> averageDB = new HashMap<>();
                    final Map<String, Object> average_collection = new HashMap<>();
                    if(alg.equals("AES"))
                    {
                        averageDB.put("AES",timeAverage);
                        dbAES.add(averageDB);
                        dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_aes = 0;
                                    int  num_of_docs_aes = task.getResult().size();

                                    for(int i = 0; i < num_of_docs_aes; i++){
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                        sum_aes += Integer.parseInt(a);
                                    }
                                    int avg_aes_db = sum_aes / num_of_docs_aes;
                                    average_collection.put("AES",avg_aes_db);
                                    db_average_aes.set(average_collection);

                                } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                            }
                        });
                    }
                    else if(alg.equals("3DES")){
                        averageDB.put("3DES",timeAverage);
                        db3DES.add(averageDB);

                        db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_3des = 0;
                                    int  num_of_docs_3des = task.getResult().size();

                                    for(int i = 0; i < num_of_docs_3des; i++){
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                        sum_3des += Integer.parseInt(a);
                                    }
                                    int avg_3des_db = sum_3des / num_of_docs_3des;

                                    average_collection.put("3DES",avg_3des_db);
                                    db_average_3des.set(average_collection);

                                } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                            }
                        });
                    }
                    else if(alg.equals("BLOWFISH")){
                        averageDB.put("BLOWFISH",timeAverage);
                        dbBLOWFISH.add(averageDB);

                        dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_blowfish = 0;
                                    int  num_of_docs_blowfish = task.getResult().size();

                                    for(int i = 0; i < num_of_docs_blowfish; i++){
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                        sum_blowfish += Integer.parseInt(a);
                                    }
                                    int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                    average_collection.put("BLOWFISH",avg_blowfish_db);
                                    db_average_blowfish.set(average_collection);

                                } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                            }
                        });
                    }



                }


            }
        });

        btn1MB.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "--";
            private static final String LABEL = "Algoritmi: " ;

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if(alg .equals("AES")) {
                        long startTime = System.nanoTime();
                        String text3 = AES.encrypt(_1MBfile);
                        long endTime = System.nanoTime();
                        //time of the execution in nanoseconds/5000000 = time in milliseconds
                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                        //txt1.setText(text3);
                        time1MB.setText((int) timeLengthAES[0] + " milliseconds");

                        timeAES1mb = timeLengthAES[0];
                        myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                    }
                    else if (alg.equals("3DES")){
                        try {
                            byte[] tripleKey = tripleDES.initKey();
                            String key = (LABEL + "3DES key: " + tripleDES.byte_to_string(tripleKey));
                            long startTime = System.nanoTime();
                            byte[] encryptResult = tripleDES.encrypt(_1MBfile.getBytes(), tripleKey);
                            long endTime = System.nanoTime();
                            timeLength3DES[0] = (endTime - startTime) / 1000000;
                            String encrypted = (LABEL + "3DES: " + tripleDES.byte_to_string(encryptResult));
                            byte[] decryptResult = tripleDES.decrypt(encryptResult, tripleKey);
                            String data = (LABEL + "3DES: " + new String(decryptResult));
                            //  txt1.setText(encrypted);
                            time1MB.setText((int) timeLength3DES[0] + " milliseconds");

                            time3DES1mb = timeLength3DES[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
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
                            timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;
                            String encryptedData = new String(encryptedBytes);
                            time1MB.setText(encryptedData);
                            byte[] decryptedBytes = blowfish.decrypt(encryptedBytes);
                            String decryptedData = new String(decryptedBytes );
                            time1MB.setText((int) timeLengthBLOWFISH[0] + " milliseconds");


                            timeBLOWFISH1mb = timeLengthBLOWFISH[0];
                            myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                                    time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
                        }
                        catch(Exception e) {
                            System.out.println(e);
                        }
                    }


                    int timeLength1 = (int)timeLengthAES[0];
                    int timeLength2 = (int)timeLength3DES[0];
                    int timeLength3 = (int)timeLengthBLOWFISH[0];
                    Map<String, Object> myPhone = new HashMap<>();
                    if(timeLength1!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                    {
                        myPhone.put("AES", timeLength1);
                        CollectionReference _1MBRef = database.collection("myPhone").document("_1MB").collection("AES");
                        _1MBRef.add(myPhone);
                    }
                    if(timeLength2!=0) {
                        myPhone.put("3DES", timeLength2);
                        CollectionReference _1MBRef = database.collection("myPhone").document("_1MB").collection("3DES");
                        _1MBRef.add(myPhone);
                    }
                    if(timeLength3!=0){
                        myPhone.put("BLOWFISH", timeLength3);
                        CollectionReference _1MBRef = database.collection("myPhone").document("_1MB").collection("BLOWFISH");
                        _1MBRef.add(myPhone);
                    }


                    timeAverage = average(time5KB.getText().toString(),time100KB.getText().toString(),
                            time500KB.getText().toString(),time1MB.getText().toString(),algorithmTxt.getText().toString());

                    if(timeAverage!=0){
                        Map<String, Object> averageDB = new HashMap<>();
                        final Map<String, Object> average_collection = new HashMap<>();
                        if(alg.equals("AES"))
                        {
                            averageDB.put("AES",timeAverage);
                            dbAES.add(averageDB);
                            dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_aes = 0;
                                        int  num_of_docs_aes = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_aes; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                            sum_aes += Integer.parseInt(a);
                                        }
                                        int avg_aes_db = sum_aes / num_of_docs_aes;
                                        average_collection.put("AES",avg_aes_db);
                                        db_average_aes.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }
                        else if(alg.equals("3DES")){
                            averageDB.put("3DES",timeAverage);
                            db3DES.add(averageDB);

                            db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_3des = 0;
                                        int  num_of_docs_3des = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_3des; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                            sum_3des += Integer.parseInt(a);
                                        }
                                        int avg_3des_db = sum_3des / num_of_docs_3des;

                                        average_collection.put("3DES",avg_3des_db);
                                        db_average_3des.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }
                        else if(alg.equals("BLOWFISH")){
                            averageDB.put("BLOWFISH",timeAverage);
                            dbBLOWFISH.add(averageDB);

                            dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_blowfish = 0;
                                        int  num_of_docs_blowfish = task.getResult().size();

                                        for(int i = 0; i < num_of_docs_blowfish; i++){
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                            sum_blowfish += Integer.parseInt(a);
                                        }
                                        int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                        average_collection.put("BLOWFISH",avg_blowfish_db);
                                        db_average_blowfish.set(average_collection);

                                    } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
                                }
                            });
                        }


                        if(counter3DES[0] == 1 || counterAES[0] == 1 || counterBLOWFISH[0] == 1)
                        { avgPreCache.setText("PRE-CACHE AVERAGE: "+ (int)timeAverage + " milliseconds\n"); }
                        else
                        { avgResult.setText("POST-CACHE AVERAGE: " + (int) timeAverage + " milliseconds"); }
                    }


                    //so that the the averages are shown even from the show averages button from main
                    long avgAes= avg(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb);
                    long avg3DES= avg(time3DES5kb,time3DES100kb,time3DES500kb, time3DES1mb);
                    long avgBLOWFISH= avg(timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
                    Averages avgclass = new Averages(avgAes,avg3DES,avgBLOWFISH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


        btnchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myChart myChart = new myChart(timeAES5kb,timeAES100kb,timeAES500kb,timeAES1mb,time3DES5kb,time3DES100kb,time3DES500kb,
                        time3DES1mb,timeBLOWFISH5kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);



                Intent intent = new Intent(EncryptionAlgorithm.this, Averages.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private   long  average(String txt5kb, String txt100kb, String txt500kb, String txt1mb, String alg){


        if(!txt5kb.equals("") && !txt100kb.equals("")&& !txt500kb.equals("") && !txt1mb.equals("")){    //TODO: pse equals e jo !=


            if(alg.equals("AES")){ counterAES[0] = counterAES[0] +1;  }
            else if(alg.equals("3DES")){ counter3DES[0] = counter3DES[0] +1; }
            else{ if(alg.equals("BLOWFISH")){ counterBLOWFISH[0] = counterBLOWFISH[0] +1;}}

            long  average ;
            String time5kb = txt5kb;
            int end1 = time5kb.indexOf(" ");
            time5kb = time5kb.substring(0,end1);

            String time100kb = txt100kb;
            int end100 = time100kb.indexOf(" ");
            time100kb = time100kb.substring(0,end100);

            String time500kb = txt500kb;
            int end500 = time500kb.indexOf(" ");
            time500kb = time500kb.substring(0,end500);

            String time1mb = txt1mb;
            int end1m = time1mb.indexOf(" ");
            time1mb = time1mb.substring(0,end1m);

            average = Integer.parseInt(time5kb)
                    + Integer.parseInt(time100kb)
                    + Integer.parseInt(time500kb)+ Integer.parseInt(time1mb);

            average = average/4;



            return average;
        }
        else
            return 0;

    }


    private  long avg (long a, long b, long c, long d){

        return (a+b+c+d)/4;
    }
}
