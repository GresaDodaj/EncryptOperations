package com.trying.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Objects;


public class Averages extends AppCompatActivity {

    public  Averages(){

    }

    private static long a;
    private static long b;
    private static long c;
    private static long d;
    Averages(long a, long b, long c,long d){

        Averages.a = a;
        Averages.b = b;
        Averages.c = c;
        Averages.d = d;
    }

    private static final String TAG = "Error: " ;

    private static int db_aes;
    private static int db_des;
    private static int db_blowfish;
    private static int db_3des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_average);
        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        final CollectionReference db_collection = database.collection("Average_Collection");

        final BarChart chart = findViewById(R.id.groupBarChart);
        LinearLayout showMyChart = findViewById(R.id.showMyChart);




        db_collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("TAG", Objects.requireNonNull(task.getResult()).size() + "");

                        String _3des = String.valueOf(task.getResult().getDocuments().get(0).get("3DES"));
                        String aes = String.valueOf(task.getResult().getDocuments().get(1).get("AES"));
                        String blowfish = String.valueOf(task.getResult().getDocuments().get(2).get("BLOWFISH"));
                        String _des = String.valueOf(task.getResult().getDocuments().get(3).get("DES"));

                        db_3des = Integer.parseInt(_3des);
                        db_aes = Integer.parseInt(aes);
                        db_des = Integer.parseInt(_des);
                        db_blowfish = Integer.parseInt(blowfish);


                    final BarDataSet[] barDataSet1 = {new BarDataSet(barEntries1(db_aes,db_des,db_3des,db_blowfish), "Average")};
                    final BarDataSet[] barDataSet2 = {new BarDataSet(barEntries2((int)a,(int)b,(int)c,(int)d), "My Phone")};



                    final Description description = new Description();
                    description.setText("");
                    description.setTextColor(getColor(R.color.firstBar));
                    description.setTextSize(10);
                    chart.setDescription(description);
                    barDataSet1[0].setColor(getColor(R.color.firstBar));
                    barDataSet2[0].setColor(getColor(R.color.secondBar));


                    BarData data = new BarData(barDataSet1[0], barDataSet2[0]);


                    chart.setData(data);
                    float barSpace = 0.07f;
                    float groupSpace = 0.3f;
                    data.setBarWidth(0.27f);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 4); //minimum+ chart data *number of bars
                    chart.groupBars(0, groupSpace, barSpace); // to group the bars

                    final String[] files = new String[]{"AES", "DES", "3DES","BLOWFISH"};
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
                    xAxis.setCenterAxisLabels(true);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1);
                    xAxis.setGranularityEnabled(true);
                    chart.setDrawGridBackground(false);
                    chart.setDrawBorders(true);

                    chart.setDragEnabled(false);
                    chart.setVisibleXRangeMaximum(4);
                    chart.setDoubleTapToZoomEnabled(false);
                    chart.setScaleEnabled(false);

                    Legend legend = chart.getLegend();
                    legend.setXEntrySpace(15);
                    legend.setFormToTextSpace(10);

                    //chart.setDrawGridBackground(false);//If enabled, the background rectangle behind the chart drawing-area will be drawn.
                    chart.setDrawValueAboveBar(true); //If set to true, all values are drawn above their bars, instead of below their top.
                    chart.notifyDataSetChanged();
                    chart.invalidate();


                } else { Log.d(TAG, "Error getting documents: ", task.getException()); }
            }
        });


        showMyChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Averages.this, myChart.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



    }

    private ArrayList<BarEntry> barEntries1(int y1, int y2, int y3, int y4) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));


        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2(int y1, int y2, int y3, int y4) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));

        return barEntries;
    }


}
