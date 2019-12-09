package com.trying.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class myChart extends AppCompatActivity {


    public  myChart(){

    }

    static long a;
    static long b;
    static long c;
    static long d;
    myChart(long a , long b, long c, long d){

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chart);

        BarChart chart = findViewById(R.id.groupBarChart);

        Encryption en = new Encryption();

        final BarDataSet[] barDataSet1 = {new BarDataSet(barEntries1((int)a,(int)b,(int)c,(int)d), "AES")};
        final BarDataSet[] barDataSet2 = {new BarDataSet(barEntries2((int)a,(int)b,(int)c,(int)d), "3DES")};
        final BarDataSet[] barDataSet3 = {new BarDataSet(barEntries3((int)a,(int)b,(int)c,(int)d), "BLOWFISH")};



        final Description description = new Description();
        description.setText("Encryption");
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);

        barDataSet1[0] = new BarDataSet(barEntries1((int)a,(int)b,(int)c,(int)d), "AES");
        barDataSet1[0].setColor(getColor(R.color.firstBar));
        barDataSet2[0] = new BarDataSet(barEntries2((int)a,(int)b,(int)c,(int)d), "3DES");
        barDataSet2[0].setColor(getColor(R.color.secondBar));
        barDataSet3[0] = new BarDataSet(barEntries1((int)a,(int)b,(int)c,(int)d), "BLOWFISH");
        barDataSet3[0].setColor(getColor(R.color.thirdBar));

        BarData data = new BarData(barDataSet1[0], barDataSet2[0], barDataSet3[0]);
        chart.setData(data);
        float barSpace = 0.08f;
        float groupSpace = 0.25f;
        data.setBarWidth(0.17f);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 4); //minimum+ chart data *number of bars
        //chart.getAxisLeft().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace); // me i grupu




        final String[] files = new String[]{"1KB", "100KB", "500kb", "1MB"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(files));
        xAxis.setCenterAxisLabels(true);//me i vendos labels poshte ne center
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDrawGridBackground(false); ///nuk po heket gridi
        chart.setDrawBorders(true);

     /* YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(8, true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(100);
        //chart.setBorderWidth(2);
        //chart.setBorderColor(Color.BLUE);*/


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

    private ArrayList<BarEntry> barEntries3(int y1, int y2, int y3, int y4) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));

        return barEntries;
    }
}
