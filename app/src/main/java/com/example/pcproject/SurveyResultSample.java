package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/*
    Initial sample page to test out user love language survey results
 */

public class SurveyResultSample extends AppCompatActivity {

    //Pie Chart
    private PieChart piechart;
    private PieDataSet pieDataSet;
    private PieData pieData;
    ArrayList<PieEntry> visitors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result_sample);

        piechart = findViewById(R.id.loveLanguagesDisplay1);
        pieChartDisplay();
    }

    //Adding entries to Pie Chart
    void pieChartDisplay()
    {

        Intent iCurrentIntent = getIntent();
        visitors.add(new PieEntry(iCurrentIntent.getIntExtra("wordsOfAffirmation", 0), "wordsOfAffirmation"));
        visitors.add(new PieEntry(iCurrentIntent.getIntExtra("qualityTime",0), "qualityTime"));
        visitors.add(new PieEntry(iCurrentIntent.getIntExtra("receivingGifts", 0), "receivingGifts"));
        visitors.add(new PieEntry(iCurrentIntent.getIntExtra("actsOfService", 0), "actsOfService"));
        visitors.add(new PieEntry(iCurrentIntent.getIntExtra("physicalTouch", 0), "physicalTouch"));
/*
            for(int i = 0; i < seekBarValues.size(); i++) {
                Object var = seekBarValues.get(i);
                visitors.add(new PieEntry(Integer.parseInt(var.toString()), 2000 + i));
                //Log.d(TAG, "Bar Chart Values: " + seekBarValues.get(i) + ";");
            }
*/
        pieDataSet = new PieDataSet(visitors, "Visit");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        pieData = new PieData(pieDataSet);

        piechart.setData(pieData);
        piechart.setCenterText("Visitors");
        piechart.getDescription().setEnabled(false);
        piechart.animate();
    }
}