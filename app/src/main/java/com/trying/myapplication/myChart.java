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
    static long a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p;

    myChart(long a , long b, long c, long d,long e, long f, long g, long h,long i ,long j, long k, long l,long m, long n, long o, long p){

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;

        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;

        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chart);

        BarChart chart = findViewById(R.id.groupBarChart);


        final BarDataSet[] barDataSet1 = {new BarDataSet(barEntries1((int)a,(int)b,(int)c,(int)d), "AES")};
        final BarDataSet[] barDataSet2 = {new BarDataSet(barEntries2((int)e,(int)f,(int)g,(int)h), "DES")};
        final BarDataSet[] barDataSet3 = {new BarDataSet(barEntries3((int)i,(int)j,(int)k,(int)l), "3DES")};
        final BarDataSet[] barDataSet4 = {new BarDataSet(barEntries4((int)m,(int)n,(int)o,(int)p), "BLOWFISH")};


        final Description description = new Description();
        description.setText("Encryption");
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);

        barDataSet1[0] = new BarDataSet(barEntries1((int)a,(int)b,(int)c,(int)d), "AES");
        barDataSet1[0].setColor(getColor(R.color.firstBar));
        barDataSet2[0] = new BarDataSet(barEntries2((int)e,(int)f,(int)g,(int)h), "DES");
        barDataSet2[0].setColor(getColor(R.color.secondBar));
        barDataSet3[0] = new BarDataSet(barEntries3((int)i,(int)j,(int)k,(int)l), "3DES");
        barDataSet3[0].setColor(getColor(R.color.thirdBar));
        barDataSet4[0] = new BarDataSet(barEntries4((int)m,(int)n,(int)o,(int)p), "BLOWFISH");
        barDataSet4[0].setColor(getColor(R.color.fourthBar));

        BarData data = new BarData(barDataSet1[0], barDataSet2[0],barDataSet3[0], barDataSet4[0]);
        chart.setData(data);
        float barSpace = 0.04f;
        float groupSpace = 0.18f;
        data.setBarWidth(0.17f);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 4); //minimum+ chart data *number of bars
        //chart.getAxisLeft().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace); // me i grupu




        final String[] files = new String[]{"10KB", "100KB", "500KB", "1MB"};
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
    private ArrayList<BarEntry> barEntries4(int y1, int y2, int y3, int y4) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, y1));
        barEntries.add(new BarEntry(1, y2));
        barEntries.add(new BarEntry(2, y3));
        barEntries.add(new BarEntry(3, y4));

        return barEntries;
    }
}
