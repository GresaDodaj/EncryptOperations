package com.trying.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    static int[] counterDES = {0};
    static int[] counterBLOWFISH = {0};

    static long timeAverage = 0;

    static long timeAES10kb = 0;
    static long timeAES100kb = 0;
    static long timeAES500kb = 0;
    static long timeAES1mb = 0;

    static long timeDES10kb = 0;
    static long timeDES100kb = 0;
    static long timeDES500kb = 0;
    static long timeDES1mb = 0;

    static long time3DES10kb = 0;
    static long time3DES100kb = 0;
    static long time3DES500kb = 0;
    static long time3DES1mb = 0;

    static long timeBLOWFISH10kb = 0;
    static long timeBLOWFISH100kb = 0;
    static long timeBLOWFISH500kb = 0;
    static long timeBLOWFISH1mb = 0;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_algorithm);
        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        final CollectionReference dbAES = database.collection("overallAverage").document("avg").collection("AES");
        final CollectionReference dbDES = database.collection("overallAverage").document("avg").collection("DES");
        final CollectionReference db3DES = database.collection("overallAverage").document("avg").collection("3DES");
        final CollectionReference dbBLOWFISH = database.collection("overallAverage").document("avg").collection("BLOWFISH");
        final DocumentReference db_average_aes = database.collection("Average_Collection").document("AES");
        final DocumentReference db_average_des = database.collection("Average_Collection").document("DES");
        final DocumentReference db_average_3des = database.collection("Average_Collection").document("3DES");
        final DocumentReference db_average_blowfish = database.collection("Average_Collection").document("BLOWFISH");


        Button btn10KB = findViewById(R.id.btn10kb);
        Button btn100KB = findViewById(R.id.btn100kb);
        Button btn500KB = findViewById(R.id.btn500kb);
        Button btn1MB = findViewById(R.id.btn1mb);
        Button btnRunAll = findViewById(R.id.btnRunAll);

        LinearLayout btnchart = findViewById(R.id.showChart);

        final TextView avgResult = findViewById(R.id.avgResult);

        final TextView time10KB = findViewById(R.id.time10kb);
        final TextView time100KB = findViewById(R.id.time100kb);
        final TextView time500KB = findViewById(R.id.time500kb);
        final TextView time1MB = findViewById(R.id.time1mb);


        Bundle var = getIntent().getExtras();
        final String alg = var.getString("ALG");
        final TextView algorithmTxt = findViewById(R.id.algorithmTxt);
        algorithmTxt.setText(alg);

        final String _10KBfile = ReadFile.readFile("_10KBfile.txt", EncryptionAlgorithm.this);
        final String _100KBfile = ReadFile.readFile("_100KBfile.txt", EncryptionAlgorithm.this);
        final String _500KBfile = ReadFile.readFile("_500KBfile.txt", EncryptionAlgorithm.this);
        final String _1MBfile = ReadFile.readFile("_1MBfile.txt", EncryptionAlgorithm.this);


        final long[] timeLength3DES = new long[1];
        final long[] timeLengthDES = new long[1];
        final long[] timeLengthBLOWFISH = new long[1];
        final long[] timeLengthAES = new long[1];


        try {
            AES.encrypt(_10KBfile);
            AES.encrypt(_100KBfile);
            AES.encrypt(_500KBfile);
            AES.encrypt(_1MBfile);
            DES.encrypt(_10KBfile.getBytes());
            DES.encrypt(_100KBfile.getBytes());
            DES.encrypt(_500KBfile.getBytes());
            DES.encrypt(_1MBfile.getBytes());
            tripleDES.encrypt(_10KBfile);
            tripleDES.encrypt(_100KBfile);
            tripleDES.encrypt(_500KBfile);
            tripleDES.encrypt(_1MBfile);


        } catch (Exception e) {
            e.printStackTrace();
        }


        btn10KB.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "Error:";

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    if (alg.equals("AES")) {

                        long average = 0;
                        AES.encrypt(_10KBfile);
                        for (int i = 0; i < 50; i++) {
                            long startTime = System.nanoTime();
                            AES.encrypt(_10KBfile);
                            long endTime = System.nanoTime();
                            timeLengthAES[0] = (endTime - startTime) / 1000000;
                            if (i > 3)
                                average = average + timeLengthAES[0];
                        }
                        average = average / 46;

                        time10KB.setText(average + " milliseconds");
                        timeAES10kb = average;
                        myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                    } else if (alg.equals("DES")) {
                        try {
                            long average = 0;
                            DES.encrypt(_10KBfile.getBytes());
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                DES.encrypt(_10KBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthDES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthDES[0];

                            }

                            average = average / 46;
                            time10KB.setText(average + " milliseconds");
                            timeDES10kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            Log.e(TAG, "DES: " + e.getMessage());
                        }
                    } else if (alg.equals("BLOWFISH")) {
                        try {
                            //byte[] text2Bytes = _10KBfile.getBytes();
                            long average = 0;
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                blowfish.generate_symetric_key();
                                blowfish.encrypt(_10KBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthBLOWFISH[0];
                            }
                            average = average / 46;
                            time10KB.setText(average + " milliseconds");
                            timeBLOWFISH10kb = average;

                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            System.out.println(e);
                        } //todo: ma mire formulo errorin
                    } else if (alg.equals("3DES")) {
                        try {
                            long average = 0;
                            tripleDES.encrypt(_10KBfile);
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                tripleDES.encrypt(_10KBfile);
                                long endTime = System.nanoTime();
                                timeLength3DES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLength3DES[0];

                            }

                            average = average / 46;
                            time10KB.setText(average + " milliseconds");
                            time3DES10kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            Log.e(TAG, "DES: " + e.getMessage());
                        }
                    }

                    timeAverage = average(time10KB.getText().toString(), time100KB.getText().toString(),
                            time500KB.getText().toString(), time1MB.getText().toString(), algorithmTxt.getText().toString());

                    if (timeAverage != 0) {

                        avgResult.setText("AVERAGE: " + (int) timeAverage + " milliseconds");

                        Map<String, Object> averageDB = new HashMap<>();
                        final Map<String, Object> average_collection = new HashMap<>();
                        if (alg.equals("AES")) {

                            averageDB.put("AES", timeAverage);
                            dbAES.add(averageDB);
                            dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_aes = 0;
                                        int num_of_docs_aes = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_aes; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                            sum_aes += Integer.parseInt(a);
                                        }
                                        int avg_aes_db = sum_aes / num_of_docs_aes;
                                        average_collection.put("AES", avg_aes_db);
                                        db_average_aes.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("DES")) {
                            averageDB.put("DES", timeAverage);
                            dbDES.add(averageDB);

                            dbDES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_des = 0;
                                        int num_of_docs_des = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_des; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("DES"));
                                            sum_des += Integer.parseInt(a);
                                        }
                                        int avg_des_db = sum_des / num_of_docs_des;

                                        average_collection.put("DES", avg_des_db);
                                        db_average_des.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("BLOWFISH")) {
                            averageDB.put("BLOWFISH", timeAverage);
                            dbBLOWFISH.add(averageDB);

                            dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_blowfish = 0;
                                        int num_of_docs_blowfish = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_blowfish; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                            sum_blowfish += Integer.parseInt(a);
                                        }
                                        int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                        average_collection.put("BLOWFISH", avg_blowfish_db);
                                        db_average_blowfish.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("3DES")) {
                            averageDB.put("3DES", timeAverage);
                            db3DES.add(averageDB);

                            db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_3des = 0;
                                        int num_of_docs_3des = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_3des; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                            sum_3des += Integer.parseInt(a);
                                        }
                                        int avg_3des_db = sum_3des / num_of_docs_3des;
                                        average_collection.put("3DES", avg_3des_db);
                                        db_average_3des.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
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
            private static final String LABEL = "Algoritmi: ";

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if (alg.equals("AES")) {
                        long average = 0;
                        AES.encrypt(_100KBfile);

                        for (int i = 0; i < 50; i++) {
                            long startTime = System.nanoTime();
                            AES.encrypt(_100KBfile);
                            long endTime = System.nanoTime();
                            timeLengthAES[0] = (endTime - startTime) / 1000000;
                            if (i > 3)
                                average = average + timeLengthAES[0];
                        }
                        average = average / 46;
                        time100KB.setText(average + " milliseconds");
                        timeAES100kb = average;

                        myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                    } else if (alg.equals("DES")) {
                        try {
                            long average = 0;
                            DES.encrypt(_100KBfile.getBytes());
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                DES.encrypt(_100KBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthDES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthDES[0];

                            }
                            average = average / 46;
                            time100KB.setText(average + " milliseconds");

                            timeDES100kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            Log.e(TAG, "DES: " + e.getMessage());
                        }
                    } else if (alg.equals("BLOWFISH")) {
                        try {

                            long average = 0;
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                blowfish.generate_symetric_key();
                                blowfish.encrypt(_100KBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthBLOWFISH[0];
                            }

                            average = average / 46;
                            time100KB.setText(average + " milliseconds");
                            timeBLOWFISH100kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);


                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else if (alg.equals("3DES")) {
                        try {
                            long average = 0;
                            tripleDES.encrypt(_100KBfile);
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                tripleDES.encrypt(_100KBfile);
                                long endTime = System.nanoTime();
                                timeLength3DES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLength3DES[0];

                            }

                            average = average / 46;
                            time100KB.setText(average + " milliseconds");
                            time3DES100kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }

                    timeAverage = average(time10KB.getText().toString(), time100KB.getText().toString(),
                            time500KB.getText().toString(), time1MB.getText().toString(), algorithmTxt.getText().toString());

                    if (timeAverage != 0) {

                        avgResult.setText("AVERAGE: " + (int) timeAverage + " milliseconds");

                        Map<String, Object> averageDB = new HashMap<>();
                        final Map<String, Object> average_collection = new HashMap<>();
                        if (alg.equals("AES")) {
                            averageDB.put("AES", timeAverage);
                            dbAES.add(averageDB);
                            dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_aes = 0;
                                        int num_of_docs_aes = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_aes; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                            sum_aes += Integer.parseInt(a);
                                        }
                                        int avg_aes_db = sum_aes / num_of_docs_aes;
                                        average_collection.put("AES", avg_aes_db);
                                        db_average_aes.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("DES")) {
                            averageDB.put("DES", timeAverage);
                            dbDES.add(averageDB);

                            dbDES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_des = 0;
                                        int num_of_docs_des = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_des; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("DES"));
                                            sum_des += Integer.parseInt(a);
                                        }
                                        int avg_des_db = sum_des / num_of_docs_des;

                                        average_collection.put("DES", avg_des_db);
                                        db_average_des.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("BLOWFISH")) {
                            averageDB.put("BLOWFISH", timeAverage);
                            dbBLOWFISH.add(averageDB);

                            dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_blowfish = 0;
                                        int num_of_docs_blowfish = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_blowfish; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                            sum_blowfish += Integer.parseInt(a);
                                        }
                                        int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                        average_collection.put("BLOWFISH", avg_blowfish_db);
                                        db_average_blowfish.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("3DES")) {
                            averageDB.put("3DES", timeAverage);
                            db3DES.add(averageDB);

                            db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_3des = 0;
                                        int num_of_docs_3des = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_3des; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                            sum_3des += Integer.parseInt(a);
                                        }
                                        int avg_3des_db = sum_3des / num_of_docs_3des;
                                        average_collection.put("3DES", avg_3des_db);
                                        db_average_3des.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
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

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if (alg.equals("AES")) {
                        long average = 0;
                        AES.encrypt(_500KBfile);
                        for (int i = 0; i < 50; i++) {
                            long startTime = System.nanoTime();
                            AES.encrypt(_500KBfile);
                            long endTime = System.nanoTime();
                            timeLengthAES[0] = (endTime - startTime) / 1000000;
                            if (i > 3)
                                average = average + timeLengthAES[0];
                        }
                        average = average / 46;

                        time500KB.setText(average + " milliseconds");
                        timeAES500kb = average;

                        myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);
                    } else if (alg.equals("DES")) {
                        try {
                            long average = 0;
                            DES.encrypt(_500KBfile.getBytes());
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                DES.encrypt(_500KBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthDES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthDES[0];

                            }
                            average = average / 46;
                            time500KB.setText(average + " milliseconds");
                            timeDES500kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);
                        } catch (Exception e) {
                            Log.e(TAG, "DES: " + e.getMessage());
                        }
                    } else if (alg.equals("BLOWFISH")) {
                        try {
                            long average = 0;
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                blowfish.generate_symetric_key();
                                blowfish.encrypt(_500KBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthBLOWFISH[0];
                            }
                            average = average / 46;
                            time500KB.setText(average + " milliseconds");
                            timeBLOWFISH500kb = timeLengthBLOWFISH[0];
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else if (alg.equals("3DES")) {
                        try {
                            long average = 0;
                            tripleDES.encrypt(_500KBfile);
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                tripleDES.encrypt(_500KBfile);
                                long endTime = System.nanoTime();
                                timeLength3DES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLength3DES[0];

                            }

                            average = average / 46;
                            time500KB.setText(average + " milliseconds");
                            time3DES500kb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                timeAverage = average(time10KB.getText().toString(), time100KB.getText().toString(),
                        time500KB.getText().toString(), time1MB.getText().toString(), algorithmTxt.getText().toString());

                if (timeAverage != 0) {

                    avgResult.setText("AVERAGE: " + (int) timeAverage + " milliseconds");

                    Map<String, Object> averageDB = new HashMap<>();
                    final Map<String, Object> average_collection = new HashMap<>();
                    if (alg.equals("AES")) {
                        averageDB.put("AES", timeAverage);
                        dbAES.add(averageDB);
                        dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_aes = 0;
                                    int num_of_docs_aes = task.getResult().size();

                                    for (int i = 0; i < num_of_docs_aes; i++) {
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                        sum_aes += Integer.parseInt(a);
                                    }
                                    int avg_aes_db = sum_aes / num_of_docs_aes;
                                    average_collection.put("AES", avg_aes_db);
                                    db_average_aes.set(average_collection);

                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                    } else if (alg.equals("DES")) {
                        averageDB.put("DES", timeAverage);
                        dbDES.add(averageDB);

                        dbDES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_des = 0;
                                    int num_of_docs_des = task.getResult().size();

                                    for (int i = 0; i < num_of_docs_des; i++) {
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("DES"));
                                        sum_des += Integer.parseInt(a);
                                    }
                                    int avg_des_db = sum_des / num_of_docs_des;

                                    average_collection.put("DES", avg_des_db);
                                    db_average_des.set(average_collection);

                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                    } else if (alg.equals("BLOWFISH")) {
                        averageDB.put("BLOWFISH", timeAverage);
                        dbBLOWFISH.add(averageDB);

                        dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_blowfish = 0;
                                    int num_of_docs_blowfish = task.getResult().size();

                                    for (int i = 0; i < num_of_docs_blowfish; i++) {
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                        sum_blowfish += Integer.parseInt(a);
                                    }
                                    int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                    average_collection.put("BLOWFISH", avg_blowfish_db);
                                    db_average_blowfish.set(average_collection);

                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                    } else if (alg.equals("3DES")) {
                        averageDB.put("3DES", timeAverage);
                        db3DES.add(averageDB);

                        db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", task.getResult().size() + "");

                                    int sum_3des = 0;
                                    int num_of_docs_3des = task.getResult().size();

                                    for (int i = 0; i < num_of_docs_3des; i++) {
                                        String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                        sum_3des += Integer.parseInt(a);
                                    }
                                    int avg_3des_db = sum_3des / num_of_docs_3des;
                                    average_collection.put("3DES", avg_3des_db);
                                    db_average_3des.set(average_collection);

                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                    }


                }


            }
        });

        btn1MB.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "--";

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if (alg.equals("AES")) {
                        long average = 0;
                        AES.encrypt(_1MBfile);
                        for (int i = 0; i < 50; i++) {
                            long startTime = System.nanoTime();
                            AES.encrypt(_1MBfile);
                            long endTime = System.nanoTime();
                            timeLengthAES[0] = (endTime - startTime) / 1000000;
                            if (i > 3)
                                average = average + timeLengthAES[0];
                        }
                        average = average / 46;
                        time1MB.setText(average + " milliseconds");
                        timeAES1mb = average;

                        myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                    } else if (alg.equals("DES")) {
                        try {
                            long average = 0;
                            DES.encrypt(_1MBfile.getBytes());
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                DES.encrypt(_1MBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthDES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthDES[0];

                            }

                            average = average / 46;
                            time1MB.setText(average + " milliseconds");
                            timeDES1mb = timeLengthDES[0];

                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);
                        } catch (Exception e) {
                            Log.e(TAG, "DES: " + e.getMessage());
                        }
                    } else if (alg.equals("BLOWFISH")) {
                        try {

                            long average = 0;
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                blowfish.generate_symetric_key();
                                blowfish.encrypt(_1MBfile.getBytes());
                                long endTime = System.nanoTime();
                                timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLengthBLOWFISH[0];
                            }
                            average = average / 46;
                            time1MB.setText(average + " milliseconds");
                            timeBLOWFISH1mb = timeLengthBLOWFISH[0];

                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else if (alg.equals("3DES")) {
                        try {
                            long average = 0;
                            tripleDES.encrypt(_1MBfile);
                            for (int i = 0; i < 50; i++) {
                                long startTime = System.nanoTime();
                                tripleDES.encrypt(_1MBfile);
                                long endTime = System.nanoTime();
                                timeLength3DES[0] = (endTime - startTime) / 1000000;

                                if (i > 3)
                                    average = average + timeLength3DES[0];

                            }

                            average = average / 46;
                            time1MB.setText(average + " milliseconds");
                            time3DES1mb = average;
                            myChart myChart = new myChart(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb, timeDES10kb, timeDES100kb, timeDES500kb,
                                    timeDES1mb, time3DES10kb, time3DES100kb, time3DES500kb,
                                    time3DES1mb, timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);

                        } catch (Exception e) {
                            Log.e(TAG, "3DES: " + e.getMessage());
                        }
                    }

                    timeAverage = average(time10KB.getText().toString(), time100KB.getText().toString(),
                            time500KB.getText().toString(), time1MB.getText().toString(), algorithmTxt.getText().toString());

                    if (timeAverage != 0) {
                        Map<String, Object> averageDB = new HashMap<>();
                        final Map<String, Object> average_collection = new HashMap<>();
                        if (alg.equals("AES")) {
                            averageDB.put("AES", timeAverage);
                            dbAES.add(averageDB);
                            dbAES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_aes = 0;
                                        int num_of_docs_aes = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_aes; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("AES"));
                                            sum_aes += Integer.parseInt(a);
                                        }
                                        int avg_aes_db = sum_aes / num_of_docs_aes;
                                        average_collection.put("AES", avg_aes_db);
                                        db_average_aes.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("DES")) {
                            averageDB.put("DES", timeAverage);
                            dbDES.add(averageDB);

                            dbDES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_des = 0;
                                        int num_of_docs_des = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_des; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("DES"));
                                            sum_des += Integer.parseInt(a);
                                        }
                                        int avg_des_db = sum_des / num_of_docs_des;

                                        average_collection.put("DES", avg_des_db);
                                        db_average_des.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("BLOWFISH")) {
                            averageDB.put("BLOWFISH", timeAverage);
                            dbBLOWFISH.add(averageDB);

                            dbBLOWFISH.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_blowfish = 0;
                                        int num_of_docs_blowfish = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_blowfish; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("BLOWFISH"));
                                            sum_blowfish += Integer.parseInt(a);
                                        }
                                        int avg_blowfish_db = sum_blowfish / num_of_docs_blowfish;

                                        average_collection.put("BLOWFISH", avg_blowfish_db);
                                        db_average_blowfish.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        } else if (alg.equals("3DES")) {
                            averageDB.put("3DES", timeAverage);
                            db3DES.add(averageDB);

                            db3DES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", task.getResult().size() + "");

                                        int sum_3des = 0;
                                        int num_of_docs_3des = task.getResult().size();

                                        for (int i = 0; i < num_of_docs_3des; i++) {
                                            String a = String.valueOf(task.getResult().getDocuments().get(i).get("3DES"));
                                            sum_3des += Integer.parseInt(a);
                                        }
                                        int avg_3des_db = sum_3des / num_of_docs_3des;
                                        average_collection.put("3DES", avg_3des_db);
                                        db_average_3des.set(average_collection);

                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                        }


                        avgResult.setText("AVERAGE: " + (int) timeAverage + " milliseconds");
                    }


                    //so that the the averages are shown even from the show averages button from main
                    long avgAes = avg(timeAES10kb, timeAES100kb, timeAES500kb, timeAES1mb);
                    long avgDES = avg(timeDES10kb, timeDES100kb, timeDES500kb, timeDES1mb);
                    long avgBLOWFISH = avg(timeBLOWFISH10kb, timeBLOWFISH100kb, timeBLOWFISH500kb, timeBLOWFISH1mb);
                    long avg3DES = avg(time3DES10kb, time3DES100kb, time3DES500kb, time3DES1mb);
                    Averages avgclass = new Averages(avgAes, avgDES, avg3DES, avgBLOWFISH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        btnRunAll.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(EncryptionAlgorithm.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setTitle("ProgressDialog"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            if(alg .equals("AES")) {

                                try {
                                    long average=0;
                                    AES.encrypt(_10KBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        AES.encrypt(_10KBfile);
                                        long endTime = System.nanoTime();
                                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthAES[0];
                                    }
                                    average=average/46;
                                    //time of the execution in nanoseconds/1000000 = time in milliseconds

                                    time10KB.setText(average + " milliseconds");
                                    timeAES10kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    AES.encrypt(_100KBfile);

                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        AES.encrypt(_100KBfile);
                                        long endTime = System.nanoTime();
                                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthAES[0];
                                    }
                                    average=average/46;
                                    time100KB.setText(average + " milliseconds");
                                    timeAES100kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    AES.encrypt(_500KBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        AES.encrypt(_500KBfile);
                                        long endTime = System.nanoTime();
                                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthAES[0];
                                    }
                                    average=average/46;
                                    time500KB.setText(average + " milliseconds");
                                    timeAES500kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    AES.encrypt(_1MBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        AES.encrypt(_1MBfile);
                                        long endTime = System.nanoTime();
                                        timeLengthAES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthAES[0];
                                    }
                                    average=average/46;
                                    time1MB.setText(average + " milliseconds");
                                    timeAES1mb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                myChart myChart = new myChart(timeAES10kb,timeAES100kb,timeAES500kb,timeAES1mb,timeDES10kb,timeDES100kb,timeDES500kb,
                                        timeDES1mb,time3DES10kb,time3DES100kb,time3DES500kb,
                                        time3DES1mb,timeBLOWFISH10kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                            }
                            else if (alg.equals("DES")){
                                try {
                                    long average=0;
                                    DES.encrypt(_10KBfile.getBytes());
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        DES.encrypt(_10KBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthDES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthDES[0];
                                    }
                                    average=average/46;
                                    time10KB.setText(average + " milliseconds");
                                    timeDES10kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    DES.encrypt(_100KBfile.getBytes());
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        DES.encrypt(_100KBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthDES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthDES[0];
                                    }
                                    average=average/46;
                                    time100KB.setText(average + " milliseconds");
                                    timeDES100kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    DES.encrypt(_500KBfile.getBytes());
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        DES.encrypt(_500KBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthDES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthDES[0];
                                    }
                                    average=average/46;
                                    time500KB.setText(average + " milliseconds");
                                    timeDES500kb =  average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    DES.encrypt(_1MBfile.getBytes());
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        DES.encrypt(_1MBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthDES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLengthDES[0];
                                    }
                                    average=average/46;
                                    time1MB.setText(average + " milliseconds");
                                    timeDES1mb =  average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                myChart myChart = new myChart(timeAES10kb,timeAES100kb,timeAES500kb,timeAES1mb,timeDES10kb,timeDES100kb,timeDES500kb,
                                        timeDES1mb,time3DES10kb,time3DES100kb,time3DES500kb,
                                        time3DES1mb,timeBLOWFISH10kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
                            }
                            else if (alg.equals("3DES")){
                                try {
                                    long average=0;
                                    tripleDES.encrypt(_10KBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        tripleDES.encrypt(_10KBfile);
                                        long endTime = System.nanoTime();
                                        timeLength3DES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLength3DES[0];
                                    }
                                    average=average/46;
                                    time10KB.setText(average + " milliseconds");
                                    time3DES10kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    tripleDES.encrypt(_100KBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        tripleDES.encrypt(_100KBfile);
                                        long endTime = System.nanoTime();
                                        timeLength3DES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLength3DES[0];
                                    }
                                    average=average/46;
                                    time100KB.setText(average + " milliseconds");
                                    time3DES100kb = average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    tripleDES.encrypt(_500KBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        tripleDES.encrypt(_500KBfile);
                                        long endTime = System.nanoTime();
                                        timeLength3DES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLength3DES[0];
                                    }
                                    average=average/46;
                                    time500KB.setText(average + " milliseconds");
                                    time3DES500kb =  average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    long average=0;
                                    tripleDES.encrypt(_1MBfile);
                                    for(int i = 0; i<50; i++) {
                                        long startTime  = System.nanoTime();
                                        tripleDES.encrypt(_1MBfile);
                                        long endTime = System.nanoTime();
                                        timeLength3DES[0] = (endTime - startTime) / 1000000;
                                        if(i>3)
                                            average = average+timeLength3DES[0];
                                    }
                                    average=average/46;
                                    time1MB.setText(average + " milliseconds");
                                    time3DES1mb =  average;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                myChart myChart = new myChart(timeAES10kb,timeAES100kb,timeAES500kb,timeAES1mb,timeDES10kb,timeDES100kb,timeDES500kb,
                                        timeDES1mb,time3DES10kb,time3DES100kb,time3DES500kb,
                                        time3DES1mb,timeBLOWFISH10kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
                            }
                            else{
                                try {
                                    long average = 0;
                                    for(int i = 0; i<50; i++) {
                                        long startTime = System.nanoTime();
                                        blowfish.generate_symetric_key();
                                        blowfish.encrypt(_10KBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                        if(i>3)
                                            average = average+ timeLengthBLOWFISH[0];
                                    }
                                    average = average/46;
                                    time10KB.setText(average + " milliseconds");
                                    timeBLOWFISH10kb =  average;
                                } catch (Exception e) { e.printStackTrace(); }

                                try {
                                    long average = 0;
                                    for(int i = 0; i<50; i++) {
                                        long startTime = System.nanoTime();
                                        blowfish.generate_symetric_key();
                                        blowfish.encrypt(_100KBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                        if(i>3)
                                            average = average+ timeLengthBLOWFISH[0];
                                    }
                                    average = average/46;
                                    time100KB.setText(average + " milliseconds");
                                    timeBLOWFISH100kb =  average;
                                } catch (Exception e) { e.printStackTrace(); }
                                try {
                                    long average = 0;
                                    for(int i = 0; i<50; i++) {
                                        long startTime = System.nanoTime();
                                        blowfish.generate_symetric_key();
                                        blowfish.encrypt(_500KBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                        if(i>3)
                                            average = average+ timeLengthBLOWFISH[0];
                                    }
                                    average = average/46;
                                    time500KB.setText(average + " milliseconds");
                                    timeBLOWFISH500kb =  average;
                                } catch (Exception e) { e.printStackTrace(); }
                                try {
                                    long average = 0;
                                    for(int i = 0; i<50; i++) {
                                        long startTime = System.nanoTime();
                                        blowfish.generate_symetric_key();
                                        blowfish.encrypt(_1MBfile.getBytes());
                                        long endTime = System.nanoTime();
                                        timeLengthBLOWFISH[0] = (endTime - startTime) / 1000000;

                                        if(i>3)
                                            average = average+ timeLengthBLOWFISH[0];
                                    }
                                    average = average/46;
                                    time1MB.setText(average + " milliseconds");
                                    timeBLOWFISH1mb = average;
                                } catch (Exception e) { e.printStackTrace(); }

                                myChart myChart = new myChart(timeAES10kb,timeAES100kb,timeAES500kb,timeAES1mb,timeDES10kb,timeDES100kb,timeDES500kb,
                                        timeDES1mb,time3DES10kb,time3DES100kb,time3DES500kb,
                                        time3DES1mb,timeBLOWFISH10kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                            }
                            timeAverage = average(time10KB.getText().toString(),time100KB.getText().toString(),
                                    time500KB.getText().toString(),time1MB.getText().toString(),algorithmTxt.getText().toString());

                            long avgAes= avg(timeAES10kb,timeAES100kb,timeAES500kb,timeAES1mb);
                            long avgDES= avg(timeDES10kb,timeDES100kb,timeDES500kb, timeDES1mb);
                            long avg3DES= avg(time3DES10kb,time3DES100kb,time3DES500kb, time3DES1mb);
                            long avgBLOWFISH= avg(timeBLOWFISH10kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);
                            Averages avgclass = new Averages(avgAes,avgDES,avg3DES,avgBLOWFISH);
                            if(timeAverage!=0){

                                avgResult.setText("AVERAGE: " + (int) timeAverage + " milliseconds");

                                Map<String, Object> averageDB = new HashMap<>();
                                final Map<String, Object> average_collection = new HashMap<>();
                                if(alg.equals("AES")) {

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

                                            } else { Log.d("TAG", "Error getting documents: ", task.getException()); }
                                        }
                                    });
                                }
                                else if(alg.equals("DES")){
                                    averageDB.put("DES",timeAverage);
                                    dbDES.add(averageDB);

                                    dbDES.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", task.getResult().size() + "");

                                                int sum_des = 0;
                                                int  num_of_docs_des = task.getResult().size();

                                                for(int i = 0; i < num_of_docs_des; i++){
                                                    String a = String.valueOf(task.getResult().getDocuments().get(i).get("DES"));
                                                    sum_des += Integer.parseInt(a);
                                                }
                                                int avg_des_db = sum_des / num_of_docs_des;

                                                average_collection.put("DES",avg_des_db);
                                                db_average_des.set(average_collection);

                                            } else { Log.d("TAG", "Error getting documents: ", task.getException()); }
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

                                            } else { Log.d("TAG", "Error getting documents: ", task.getException()); }
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

                                            } else { Log.d("TAG", "Error getting documents: ", task.getException()); }
                                        }
                                    });
                                }


                                timeAverage = average(time10KB.getText().toString(),time100KB.getText().toString(),
                                        time500KB.getText().toString(),time1MB.getText().toString(),algorithmTxt.getText().toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();




            }

        });


        btnchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myChart myChart = new myChart(timeAES10kb,timeAES100kb,timeAES500kb,timeAES1mb,timeDES10kb,timeDES100kb,timeDES500kb,
                        timeDES1mb,time3DES10kb,time3DES100kb,time3DES500kb,
                        time3DES1mb,timeBLOWFISH10kb,timeBLOWFISH100kb,timeBLOWFISH500kb,timeBLOWFISH1mb);

                

                Intent intent = new Intent(EncryptionAlgorithm.this, Averages.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private   long  average(String txt10kb, String txt100kb, String txt500kb, String txt1mb, String alg){


        if(!txt10kb.equals("") && !txt100kb.equals("")&& !txt500kb.equals("") && !txt1mb.equals("")){


            if(alg.equals("AES")){ counterAES[0] = counterAES[0] +1;  }
            else if(alg.equals("DES")){ counterDES[0] = counterDES[0] +1; }
            else{ if(alg.equals("BLOWFISH")){ counterBLOWFISH[0] = counterBLOWFISH[0] +1;}}

            long  average ;
            String time10kb = txt10kb;
            int end1 = time10kb.indexOf(" ");
            time10kb = time10kb.substring(0,end1);

            String time100kb = txt100kb;
            int end100 = time100kb.indexOf(" ");
            time100kb = time100kb.substring(0,end100);

            String time500kb = txt500kb;
            int end500 = time500kb.indexOf(" ");
            time500kb = time500kb.substring(0,end500);

            String time1mb = txt1mb;
            int end1m = time1mb.indexOf(" ");
            time1mb = time1mb.substring(0,end1m);

            average = Integer.parseInt(time10kb)
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
