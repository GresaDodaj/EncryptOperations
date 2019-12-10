package com.trying.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.trying.myapplication.myChart.a;
import static com.trying.myapplication.myChart.b;

public class Averages extends AppCompatActivity {

    public  Averages(){

    }

    static long a;
    static long b;
    static long c;
    Averages(long a, long b, long c){

        this.a = a;
        this.b = b;
        this.c = c;


    }


    private static final String TAG = "TAG" ;


    static String aes ;
    static int db_aes;
    static int db_3des;
    static int db_blowfish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_average);

        final BarChart chart = findViewById(R.id.groupBarChart);

        final FirebaseFirestore database = FirebaseFirestore.getInstance();

        final CollectionReference db_collection = database.collection("Average_Collection");


        db_collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("TAG", task.getResult().size() + "");


                        String aes = String.valueOf(task.getResult().getDocuments().get(1).get("AES"));
                        String _3des = String.valueOf(task.getResult().getDocuments().get(0).get("3DES"));
                        String blowfish = String.valueOf(task.getResult().getDocuments().get(2).get("BLOWFISH"));
                        db_aes = Integer.parseInt(aes);
                        db_3des = Integer.parseInt(_3des);
                        db_blowfish = Integer.parseInt(blowfish);


                    final BarDataSet[] barDataSet1 = {new BarDataSet(barEntries1((int) db_aes,(int)db_3des,(int)db_blowfish), "Average")};
                    final BarDataSet[] barDataSet2 = {new BarDataSet(barEntries2((int)a,(int)b,(int)c), "My Phone")};



                    final Description description = new Description();
                    description.setText("Encryption");
                    description.setTextColor(getColor(R.color.firstBar));
                    description.setTextSize(10);
                    chart.setDescription(description);

                    barDataSet1[0] = new BarDataSet(barEntries1((int) db_aes,(int)db_3des,(int)db_blowfish), "Average");
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0] = new BarDataSet(barEntries2((int)a,(int)b,(int)c), "My Phone");
                    barDataSet2[0].setColor(getColor(R.color.secondBar));


                    BarData data = new BarData(barDataSet1[0], barDataSet2[0]);



                    chart.setData(data);
                    float barSpace = 0.07f;
                    float groupSpace = 0.31f;
                    data.setBarWidth(0.27f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 3); //minimum+ chart data *number of bars
                    //chart.getAxisLeft().setAxisMinimum(0);
                    chart.groupBars(0, groupSpace, barSpace); // me i grupu

                    final String[] files = new String[]{"AES", "3DES", "BLOWFISH"};
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);
                    chart.setDrawGridBackground(false); ///nuk po heket gridi
                    chart.setDrawBorders(true);

                    chart.setDragEnabled(false);
                    chart.setVisibleXRangeMaximum(3);
                    chart.setDoubleTapToZoomEnabled(false);
                    //chart.setScaleXEnabled(false);
                    chart.setScaleEnabled(false);

                    Legend legend = chart.getLegend();
                    legend.setXEntrySpace(15);
                    legend.setFormToTextSpace(10);

                    chart.invalidate();


                } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
            }
        });





    }

    private ArrayList<BarEntry> barEntries1(int y1, int y2, int y3) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));


        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2(int y1, int y2, int y3) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));

        return barEntries;
    }


}
