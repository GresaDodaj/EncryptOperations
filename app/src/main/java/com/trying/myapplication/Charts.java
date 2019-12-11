/*
package com.trying.myapplication;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Objects;

public class Charts extends AppCompatActivity {

    private static final String TAG = "HELLO";
    final FirebaseFirestore database = FirebaseFirestore.getInstance();
    DocumentReference docref1KB  = database.collection("myPhone").document("_1KB");
    DocumentReference docref10KB  = database.collection("myPhone").document("_10KB");
    DocumentReference docref500kb  = database.collection("myPhone").document("_500kb");
    DocumentReference docref100KB  = database.collection("myPhone").document("_100KB");
    DocumentReference docref1MB  = database.collection("myPhone").document("_1MB");

   
    String strAES1kb;
    String strAES10kb;
    String strAES500kb;
    String strAES100kb;
    String strAES1mb;
    
    String str3DES1kb;
    String str3DES10kb;
    String str3DES500kb;
    String str3DES100kb;
    String str3DES1mb;

    String strBLOWFISH1kb;
    String strBLOWFISH10kb;
    String strBLOWFISH500kb;
    String strBLOWFISH100kb;
    String strBLOWFISH1mb;

    BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        getApplicationContext();
        chart = findViewById(R.id.groupBarChart);


        final BarDataSet[] barDataSet1 = {new BarDataSet(barEntries1(1, 1, 1, 1, 1), "AES")};
        final BarDataSet[] barDataSet2 = {new BarDataSet(barEntries2(1,1,1,1,1), "3DES")};
        final BarDataSet[] barDataSet3 = {new BarDataSet(barEntries3(1,1,1,1,1), "BLOWFISH")};


        final int[] aes1kb = {2};
        final int[] aes10kb = {2};
        final int[] aes500kb = {2};
        final int[] aes100kb = {2};
        final int[] aes1mb = {2};


        final int[] _3des1kb = {2};
        final int[] _3des10kb = {2};
        final int[] _3des500kb = {2};
        final int[] _3des100kb = {2};
        final int[] _3des1mb = {2};


        final int[] blowfish1kb = {2};
        final int[] blowfish10kb = {2};
        final int[] blowfish500kb = {2};
        final int[] blowfish100kb = {2};
        final int[] blowfish1mb = {2};


        final Description description = new Description();
        description.setText("Encryption");
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);

       

        final String[] files = new String[]{"1KB", "10KB", "500kb", "100KB", "1MB"};
        

        docref1KB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                chart.setNoDataText("NO DATA AVAILABLE");
                chart.setNoDataTextColor(Color.BLUE);

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);

                    
                    strAES1kb = Objects.requireNonNull(snapshot.get("AES")).toString();
                    int aes1kb1 = Integer.parseInt(strAES1kb);
                    aes1kb[0] = aes1kb1;

                    str3DES1kb = Objects.requireNonNull(snapshot.get("3DES")).toString();
                    _3des1kb[0] = Integer.parseInt(str3DES1kb);
                    

                    strBLOWFISH1kb = Objects.requireNonNull(snapshot.get("BLOWFISH")).toString();
                    blowfish1kb[0] = Integer.parseInt(strBLOWFISH1kb);



                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0],0,0,0,0), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1kb[0],0,0,0,0), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1( blowfish1kb[0],0,0,0,0), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 5); //minimum+ chart data *number of bars
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

                    strAES10kb = Objects.requireNonNull(snapshot.get("AES")).toString();
                    aes10kb[0] = Integer.parseInt(strAES10kb);

                    str3DES10kb = Objects.requireNonNull(snapshot.get("3DES")).toString();
                    _3des10kb[0] = Integer.parseInt(str3DES10kb);

                    strBLOWFISH10kb = Objects.requireNonNull(snapshot.get("BLOWFISH")).toString();
                    blowfish10kb[0] = Integer.parseInt(strBLOWFISH10kb);

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0],   aes10kb[0],0,0,0), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1kb[0],_3des10kb[0],0,0,0), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1( blowfish1kb[0], blowfish10kb[0],0,0,0), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));
                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 5); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu



                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });


        docref500kb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
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

                    strAES500kb = Objects.requireNonNull(snapshot.get("AES")).toString();
                    aes10kb[0] = Integer.parseInt(strAES500kb);

                    str3DES500kb = Objects.requireNonNull(snapshot.get("3DES")).toString();
                    _3des500kb[0] = Integer.parseInt(str3DES500kb);

                    strBLOWFISH500kb = Objects.requireNonNull(snapshot.get("BLOWFISH")).toString();
                    blowfish500kb[0] = Integer.parseInt(strBLOWFISH500kb);

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0],  aes10kb[0],aes500kb[0],0,0), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1kb[0],_3des10kb[0],_3des500kb[0],0,0), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1( blowfish1kb[0], blowfish10kb[0],blowfish500kb[0],0,0), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));


                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 5); //minimum+ chart data *number of bars
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

                    strAES100kb = Objects.requireNonNull(snapshot.get("AES")).toString();
                    aes100kb[0] = Integer.parseInt(strAES100kb);

                     str3DES100kb = Objects.requireNonNull(snapshot.get("3DES")).toString();
                    _3des100kb[0] = Integer.parseInt(str3DES100kb);

                    strBLOWFISH100kb = Objects.requireNonNull(snapshot.get("BLOWFISH")).toString();
                    blowfish100kb[0] = Integer.parseInt(strBLOWFISH100kb);

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0], aes10kb[0],aes500kb[0],aes100kb[0],0), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1kb[0],_3des10kb[0],_3des500kb[0],_3des100kb[0],0), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1( blowfish1kb[0],  blowfish10kb[0],blowfish500kb[0], blowfish100kb[0],0), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 5); //minimum+ chart data *number of bars
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

                    strAES1mb = Objects.requireNonNull(snapshot.get("AES")).toString();
                    aes1mb[0] = Integer.parseInt(strAES1mb);

                    str3DES1mb = Objects.requireNonNull(snapshot.get("3DES")).toString();
                    _3des1mb[0] = Integer.parseInt(str3DES1mb);

                    strBLOWFISH1mb = Objects.requireNonNull(snapshot.get("BLOWFISH")).toString();
                    blowfish1mb[0] = Integer.parseInt(strBLOWFISH1mb);

                    barDataSet1[0] = new BarDataSet(barEntries1(aes1kb[0],  aes10kb[0],aes500kb[0],aes100kb[0],aes1mb[0]), "AES");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2(_3des1kb[0], _3des10kb[0],_3des500kb[0],_3des100kb[0], _3des1mb[0]), "3DES");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));
                    barDataSet3[0] = new BarDataSet(barEntries1( blowfish1kb[0], blowfish10kb[0],blowfish500kb[0], blowfish100kb[0],blowfish1mb[0]), "BLOWFISH");
                    barDataSet3[0].setColor(getColor(R.color.thirdBar));

                    BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
                    chart.setData(data);
                    float barSpace = 0.08f;
                    float groupSpace = 0.25f;
                    data.setBarWidth(0.17f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 5); //minimum+ chart data *number of bars
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


     */
/*   Description description = new Description();
        description.setText(aes);
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);*//*

        chart.setVisibleXRange(10,10);

        chart.setDrawGridBackground(false); ///nuk po heket gridi
        chart.setDrawBorders(true);

     */
/* YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(8, true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(100);
        //chart.setBorderWidth(2);
        //chart.setBorderColor(Color.BLUE);*//*



        chart.setDragEnabled(false);
        chart.setVisibleXRangeMaximum(6);
        chart.setDoubleTapToZoomEnabled(false);
        //chart.setScaleXEnabled(false);
        chart.setScaleEnabled(false);


        Legend legend = chart.getLegend();
        //legend.setTextColor(getColor(R.color.colorPrimaryDark));
        //legend.setTextSize(15);
       // legend.setForm(Legend.LegendForm.CIRCLE); munesh edhe square --by default
        //legend.setFormSize(2);
        legend.setXEntrySpace(15);
        legend.setFormToTextSpace(10);




        chart.invalidate();
    }



    private  long averageMyPhone(long a, long b){



        long average = a+b/2;


        return 0;
    }

    private ArrayList<BarEntry> barEntries1(int y1, int y2, int y3, int y4, int y5) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));
        barEntries.add(new BarEntry(4, y5));


        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2(int y1, int y2, int y3, int y4, int y5) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));
        barEntries.add(new BarEntry(4, y5));

        return barEntries;
    }

    private ArrayList<BarEntry> barEntries3(int y1, int y2, int y3, int y4, int y5) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));
        barEntries.add(new BarEntry(4, y5));

        return barEntries;
    }
}

*/
