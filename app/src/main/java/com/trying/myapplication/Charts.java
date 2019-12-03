package com.trying.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
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


public class Charts extends AppCompatActivity {


    BarChart chart;

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

        Description description = new Description();
        description.setText("Legend");
        description.setTextColor(getColor(R.color.firstBar));
        description.setTextSize(10);
        chart.setDescription(description);


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
        barEntries.add(new BarEntry(0, 100));
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

