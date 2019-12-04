package com.trying.myapplication;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Charts extends AppCompatActivity {

    private static final String TAG = "HELLO";
    final FirebaseFirestore database = FirebaseFirestore.getInstance();
    DocumentReference docref1KB  = database.collection("myPhone").document("_1KB");
    DocumentReference docref5KB  = database.collection("myPhone").document("_5KB");
    DocumentReference docref10KB  = database.collection("myPhone").document("_10KB");
    DocumentReference docref50KB  = database.collection("myPhone").document("_50KB");
    DocumentReference docref100KB  = database.collection("myPhone").document("_10KB");
    DocumentReference docref1MB  = database.collection("myPhone").document("_1MB");


    int aes5kb=0;
    int aes10kb=0;
    int aes50kb=0;
    int aes100kb=0;
    int aes1mb=0;
     Long aes1kb= Long.valueOf(1);

    int _3des1kb=0;
    int _3des5kb=0;
    int _3des10kb=0;
    int _3des50kb=0;
    int _3des100kb=0;
    int __3des1mb=0;

    int blowfish1kb=0;
    int blowfish5kb=0;
    int blowfish10kb=0;
    int blowfish50kb=0;
    int blowfish100kb=0;
    int blowfish1mb=0;


    BarChart chart;

    String aes="500";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        getApplicationContext();



        chart = findViewById(R.id.groupBarChart);
        BarDataSet barDataSet1 = new BarDataSet(barEntries1(), "AES");
        barDataSet1.setColor(getColor(R.color.firstBar));
        BarDataSet barDataSet2 = new BarDataSet(barEntries2(), "3DES");
        barDataSet2.setColor(getColor(R.color.secondBar));
        BarDataSet barDataSet3 = new BarDataSet(barEntries3(), "BLOWFISH");
        barDataSet3.setColor(getColor(R.color.thirdBar));

        final Description description = new Description();
        description.setText("");
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);

       /* docref1MB.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@NonNull DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {*/



               /* if(documentSnapshot.exists()) {

                    Map<Object, String> myPhone = new HashMap<>();

                    myPhone.get("AES");
                    if(aes1kb!=0) // qe mos me u bo update 0 te dhanat qe jon ne databaze kur klikohet najnjo prej tjerave algoritme
                        aes1kb = Integer.parseInt(myPhone.get("AES"));

                    //aes1kb = documentSnapshot.getLong("AES").intValue();
                    //Integer i = (Integer) documentSnapshot.get("age");
                  //  aes1kb = i;
                    //aes1kb[0] = Integer.parseInt((Objects.requireNonNull(documentSnapshot.get("AES")).toString()));

                    //_3DES1kb = Integer.parseInt(Objects.requireNonNull(documentSnapshot.get("3DES").toString()));
                    // aes1kb = Integer.parseInt(Objects.requireNonNull(documentSnapshot.get("BLOWFISH").toString()));

                }
                description.setText(Objects.requireNonNull(documentSnapshot.get("AES")).toString());
                chart.setDescription(description);
            }
        });*/

        docref1MB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    //aes1kb = snapshot.getLong("AES");
                    String arr = snapshot.get("3DES").toString();
                    aes1kb = Long.valueOf(arr);
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });







        BarData data = new BarData(barDataSet1, barDataSet2, barDataSet3);
        chart.setData(data);

        chart.setNoDataText("NO DATA AVAILABLE");
        chart.setNoDataTextColor(Color.BLUE);
        String[] files = new String[]{"1KB", "5KB", "10KB", "50KB", "100KB", "1MB"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
        xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

     /*   Description description = new Description();
        description.setText(aes);
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);*/


        chart.setDrawGridBackground(false); ///nuk po heket gridi
        chart.setDrawBorders(true);
        //chart.setBorderWidth(2);
        //chart.setBorderColor(Color.BLUE);

        chart.setDragEnabled(false);
        chart.setVisibleXRangeMaximum(6);



        Legend legend = chart.getLegend();

        //legend.setTextColor(getColor(R.color.colorPrimaryDark));
        //legend.setTextSize(15);
       // legend.setForm(Legend.LegendForm.CIRCLE); munesh edhe square --by default
        //legend.setFormSize(2);
        legend.setXEntrySpace(15);
        legend.setFormToTextSpace(10);



        float barSpace = 0.08f;
        float groupSpace = 0.25f;
        data.setBarWidth(0.17f);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
        //chart.getAxisLeft().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace); // me i grupu


        chart.invalidate();
    }

    private ArrayList<BarEntry> barEntries1() {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0,aes1kb));
        barEntries.add(new BarEntry(1, 200));
        barEntries.add(new BarEntry(2, 300));
        barEntries.add(new BarEntry(3, 1000));
        barEntries.add(new BarEntry(4, 400));
        barEntries.add(new BarEntry(5, 500));


        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2() {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 100));
        barEntries.add(new BarEntry(1, 400));
        barEntries.add(new BarEntry(2, 500));
        barEntries.add(new BarEntry(3, 200));
        barEntries.add(new BarEntry(4, 400));
        barEntries.add(new BarEntry(5, 500));

        return barEntries;
    }

    private ArrayList<BarEntry> barEntries3() {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 300));
        barEntries.add(new BarEntry(1, 400));
        barEntries.add(new BarEntry(2, 300));
        barEntries.add(new BarEntry(3, 50));
        barEntries.add(new BarEntry(4, 700));
        barEntries.add(new BarEntry(5, 500));

        return barEntries;
    }
}

