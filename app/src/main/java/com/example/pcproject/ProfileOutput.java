package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProfileOutput extends AppCompatActivity {

    //TAG
    private static final String TAG = "ProfileOutput";
    private BarChart barChart1;
    ArrayList<BarEntry> entriesBarChart1;
    BarDataSet barDataSet;
    BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_output);

        barChart1 = findViewById(R.id.barChart1);
        entriesBarChart1 = new ArrayList<>();
        entriesBarChart1.add(new BarEntry(2018, 420));
        entriesBarChart1.add(new BarEntry(2017, 423));
        entriesBarChart1.add(new BarEntry(2016, 624));
        entriesBarChart1.add(new BarEntry(2015, 234));
        entriesBarChart1.add(new BarEntry(2014, 800));
        entriesBarChart1.add(new BarEntry(2013, 100));
        entriesBarChart1.add(new BarEntry(2012, 323));
        entriesBarChart1.add(new BarEntry(2011, 643));
        entriesBarChart1.add(new BarEntry(2010, 763));

        barDataSet = new BarDataSet(entriesBarChart1, "BarChart1");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barData = new BarData(barDataSet);

        barChart1.setFitBars(true);
        barChart1.setData(barData);
        barChart1.getDescription().setText("Bar Chart Example 1");
        barChart1.animateY(2000);
    }
}