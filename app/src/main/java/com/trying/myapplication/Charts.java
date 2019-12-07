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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;


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
    String _aes1mb;
    String _aes1kb;

    String _3des1kb;
    int _3des5kb=0;
    int _3des10kb=0;
    int _3des50kb=0;
    int _3des100kb=0;
    String _3des1mb;

    int blowfish1kb=0;
    int blowfish5kb=0;
    int blowfish10kb=0;
    int blowfish50kb=0;
    int blowfish100kb=0;
    String blowfish1mb;

    BarChart chart;
    globalVariables go = new globalVariables();
    BarDataSet barDataSet1;

    String aes="500";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        getApplicationContext();
        chart = findViewById(R.id.groupBarChart);


        final BarDataSet[] barDataSet1 = {new BarDataSet(barEntries1(1, 1, 1, 1, 1, 1), "AES")};
        final BarDataSet[] barDataSet2 = {new BarDataSet(barEntries2(1,1,1,1,1,1), "3DES")};
        final BarDataSet[] barDataSet3 = {new BarDataSet(barEntries3(1,1,1,1,1,1), "BLOWFISH")};


        final int[] aes1kb = {2};
        final int[] aes5kb = {2};
        final int[] aes10kb = {2};
        final int[] aes50kb = {2};
        final int[] aes100kb = {2};
        final int[] aes1MB = {2};


        final int[] __3des1kb = {2};
        final int[] _3des5kb = {2};
        final int[] _3des10kb = {2};
        final int[] _3des50kb = {2};
        final int[] _3des100kb = {2};
        final int[] _3des1MB = {2};


        final int[] blowfish1kb = {2};
        final int[] blowfish5kb = {2};
        final int[] blowfish10kb = {2};
        final int[] blowfish50kb = {2};
        final int[] blowfish100kb = {2};
        final int[] blowfish1MB = {2};


        final Description description = new Description();
        description.setText("");
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);

       /* docref1MB.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@NonNull DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()) {
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


        chart.setNoDataText("NO DATA AVAILABLE");
        chart.setNoDataTextColor(Color.BLUE);
        final String[] files = new String[]{"1KB", "5KB", "10KB", "50KB", "100KB", "1MB"};



        docref1KB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    _aes1kb = snapshot.get("AES").toString();
                    int aes1kb1 = Integer.parseInt(_aes1kb);

                    aes1kb[0] = aes1kb1;

                    _3des1kb = snapshot.get("3DES").toString();
                    int _3des1mb1 = Integer.parseInt(_3des1kb);

                    blowfish1mb = snapshot.get("BLOWFISH").toString();
                    int blowfish1mb1 = Integer.parseInt(blowfish1mb);



                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0], aes1kb1,aes1kb1,aes1kb1,aes1kb1,aes1kb1), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1(blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

        docref5KB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    _aes1mb = snapshot.get("AES").toString();
                    int aes1mb1 = Integer.parseInt(_aes1mb);

                    _3des1mb = snapshot.get("3DES").toString();
                    int _3des1mb1 = Integer.parseInt(_3des1mb);

                    blowfish1mb = snapshot.get("BLOWFISH").toString();
                    int blowfish1mb1 = Integer.parseInt(blowfish1mb);

                    //gresa[0] = aes1mb1+5;

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0], 2,aes1mb1,aes1mb1,aes1mb1,aes1mb1), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1(blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

        docref10KB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    _aes1mb = snapshot.get("AES").toString();
                    int aes1mb1 = Integer.parseInt(_aes1mb);

                    _3des1mb = snapshot.get("3DES").toString();
                    int _3des1mb1 = Integer.parseInt(_3des1mb);

                    blowfish1mb = snapshot.get("BLOWFISH").toString();
                    int blowfish1mb1 = Integer.parseInt(blowfish1mb);

                    //gresa[0] = aes1mb1+5;

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0], 3,aes1mb1,aes1mb1,aes1mb1,aes1mb1), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1(blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });


        docref50KB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    _aes1mb = snapshot.get("AES").toString();
                    int aes1mb1 = Integer.parseInt(_aes1mb);

                    _3des1mb = snapshot.get("3DES").toString();
                    int _3des1mb1 = Integer.parseInt(_3des1mb);

                    blowfish1mb = snapshot.get("BLOWFISH").toString();
                    int blowfish1mb1 = Integer.parseInt(blowfish1mb);

                    //gresa[0] = aes1mb1+5;

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0], 4,aes1mb1,aes1mb1,aes1mb1,aes1mb1), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1(blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

        docref100KB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    _aes1mb = snapshot.get("AES").toString();
                    int aes1mb1 = Integer.parseInt(_aes1mb);

                    _3des1mb = snapshot.get("3DES").toString();
                    int _3des1mb1 = Integer.parseInt(_3des1mb);

                    blowfish1mb = snapshot.get("BLOWFISH").toString();
                    int blowfish1mb1 = Integer.parseInt(blowfish1mb);

                    //gresa[0] = aes1mb1+5;

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0], 2,aes1mb1,aes1mb1,aes1mb1,aes1mb1), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1(blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });


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

                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    _aes1mb = snapshot.get("AES").toString();
                    int aes1mb1 = Integer.parseInt(_aes1mb);

                    _3des1mb = snapshot.get("3DES").toString();
                    int _3des1mb1 = Integer.parseInt(_3des1mb);

                    blowfish1mb = snapshot.get("BLOWFISH").toString();
                    int blowfish1mb1 = Integer.parseInt(blowfish1mb);

                    //gresa[0] = aes1mb1+5;

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0],3,aes1mb1,aes1mb1,aes1mb1,aes1mb1), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1,_3des1mb1), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1(blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1,blowfish1mb1), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 6); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });


        database.collection("myPhone").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

            }
        });


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






        chart.invalidate();
    }

    private ArrayList<BarEntry> barEntries1(int y1, int y2, int y3, int y4, int y5,int y6) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));
        barEntries.add(new BarEntry(4, y5));
        barEntries.add(new BarEntry(5, y6));


        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2(int y1, int y2, int y3, int y4, int y5,int y6) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));
        barEntries.add(new BarEntry(4, y5));
        barEntries.add(new BarEntry(5, y6));

        return barEntries;
    }

    private ArrayList<BarEntry> barEntries3(int y1, int y2, int y3, int y4, int y5,int y6) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));
        barEntries.add(new BarEntry(4, y5));
        barEntries.add(new BarEntry(5, y6));

        return barEntries;
    }
}

