package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class ProfileOutput_NOTNEEDED extends AppCompatActivity {

    //TAG
    private static final String TAG = "ProfileOutput";

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    //User Data
    private ArrayList<Integer> seekBarValues = new ArrayList<Integer>();
    private String logs;

    //Bar Chart
    private BarChart barChart1;
    private BarDataSet barDataSet;
    private BarData barData;

    //Pie Chart
    private PieChart piechart;
    private PieDataSet pieDataSet;
    private PieData pieData;

    //Radar Chart
    private RadarChart radarChart;
    private RadarDataSet radarDataSet;
    private RadarData radarData;

    private Long number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_output);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        getUserData("profile1");

        barChart1 = findViewById(R.id.barChart1);
        piechart = findViewById(R.id.pieChart);
        radarChart = findViewById(R.id.radarChart);

    }

    void getUserData(String sProfile)
    {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DocumentReference profileDataRef = db.collection(currentUser.getEmail()).document(sProfile);

        profileDataRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            Log.d(TAG, "Document sanpshot exists........-------");
                            Map<String, Object> profileData = documentSnapshot.getData();
                            ProfileOutput_NOTNEEDED.this.seekBarValues = (ArrayList<Integer>) profileData.get("SeekBarValues");
                            Log.d(TAG, "Document sanpshot exists........-------2");
                            for(int i = 0; i < seekBarValues.size(); i++)
                            {
                                Object var = seekBarValues.get(i);
                                Log.d(TAG, "Document sanpshot exists........-------3");
                                Log.d(TAG, "Bar Chart Values:-> " +var.toString()+ ";");
                            }
                            ProfileOutput_NOTNEEDED.this.logs = (String) profileData.get("Logs");
                            Log.d(TAG, "Log Data: " +logs+ ";");
                            barChartDisplay();
                            pieChartDisplay();
                            radarChartDisplay();
                            Toast.makeText(ProfileOutput_NOTNEEDED.this, "Profile Data Fetched", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ProfileOutput_NOTNEEDED.this, "Profile Data Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileOutput_NOTNEEDED.this, "Loading Data Base Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    void barChartDisplay()
    {
        ArrayList<BarEntry> entriesBarChart1 = new ArrayList<>();
        //int count = 0;

        Log.d(TAG, "barChartDisplay--------1: " +0+ ";");

        for(int i = 0; i < seekBarValues.size(); i++)
        {
            Object var = seekBarValues.get(i);
            entriesBarChart1.add(new BarEntry(2000 + i, Integer.parseInt(var.toString())));
            Log.d(TAG, "Bar Chart Values: " +seekBarValues.get(i)+ ";");
        }


        /*
        entriesBarChart1.add(new BarEntry(2017, (long)423));
        entriesBarChart1.add(new BarEntry(2016, 624));
        entriesBarChart1.add(new BarEntry(2015, 234));
        entriesBarChart1.add(new BarEntry(2014, 800));
        entriesBarChart1.add(new BarEntry(2013, 100));
        entriesBarChart1.add(new BarEntry(2012, 323));
        entriesBarChart1.add(new BarEntry(2011, 643));
        entriesBarChart1.add(new BarEntry(2010, 763));
        */

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
    void pieChartDisplay()
    {
        ArrayList<PieEntry> visitors = new ArrayList<>();
        for(int i = 0; i < seekBarValues.size(); i++) {
            Object var = seekBarValues.get(i);
            visitors.add(new PieEntry(Integer.parseInt(var.toString()), 2000 + i));
            //Log.d(TAG, "Bar Chart Values: " + seekBarValues.get(i) + ";");
        }

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
    void radarChartDisplay()
    {
        ArrayList<RadarEntry> visitors = new ArrayList<>();
        for(int i = 0; i < seekBarValues.size(); i++)
        {
            Object var = seekBarValues.get(i);
            visitors.add(new RadarEntry(Integer.parseInt(var.toString())));
            //Log.d(TAG, "Bar Chart Values: " +seekBarValues.get(i)+ ";");
        }

        radarDataSet = new RadarDataSet(visitors, "Visitors");
        radarDataSet.setColor(Color.RED);
        radarDataSet.setLineWidth(2f);
        radarDataSet.setValueTextColor(Color.RED);
        radarDataSet.setValueTextSize(14f);

        radarData = new RadarData();
        radarData.addDataSet(radarDataSet);

        String[] labels = {"2000", "2001" , "2002" , "2003" , "2004" , "2005" , "2006" , "2007" , "2008", "2009"};

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.setData(radarData);
    }
}