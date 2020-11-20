package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class EventDetail extends AppCompatActivity {
    private SeekBar PT;
    private SeekBar WOA;
    private SeekBar G;
    private SeekBar AOS;
    private SeekBar QT;

    private TextView traitsLearned;
    private ImageView eventImg;
    private TextView talkAbout;
    private TextView youReallyLiked;
    private TextView youDidNotLiked;

    private Button backB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        PT = findViewById(R.id.PTSeekBarDetail);
        WOA = findViewById(R.id.WOASeekBarDetail);
        G = findViewById(R.id.GSeekBarDetail);
        AOS = findViewById(R.id.AOSSeekBarDetail);
        QT = findViewById(R.id.QTSeekBarDetail);

        PT.setProgress(20);
        WOA.setProgress(60);
        G.setProgress(10);
        AOS.setProgress(40);
        QT.setProgress(80);

        traitsLearned = findViewById(R.id.eventTraitsDetail);
        eventImg = findViewById(R.id.eventImgDetail);
        talkAbout = findViewById(R.id.talkAboutDetails);
        youReallyLiked = findViewById(R.id.youReallyLikedDetails);
        youDidNotLiked = findViewById(R.id.youDidNotLikedDetails);
        backB = findViewById(R.id.backDetailsB);

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Should take user back to all events page
            }
        });

    }
}