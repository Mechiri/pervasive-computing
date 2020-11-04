package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class initialSurvey extends AppCompatActivity {

    //private surveyAdapter surveyAdapter;
    //private Button buttonSurveyAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        surveypage1fragment surveypage1fragment = new surveypage1fragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.mainLayout, surveypage1fragment).commit();


        //buttonSurveyAction = findViewById(R.id.surveyButton);

        /*setupOnboardingItems();

        final ViewPager2 surveyViewPager = findViewById(R.id.surveyViewPager);
        surveyViewPager.setAdapter(surveyAdapter);

        buttonSurveyAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(surveyViewPager.getCurrentItem() + 1 < surveyAdapter.getItemCount())
                {
                    surveyViewPager.setCurrentItem(surveyViewPager.getCurrentItem()+1);
                }
                else {
                    Toast.makeText(initialSurvey.this, "DONE", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }


    /*
    private void setupOnboardingItems() {
        List<surveyItem> surveyItems = new ArrayList<>();
        surveyItem q1 = new surveyItem();
        q1.setQuestion("Please pick the opinion that best reflects your preferences");
        q1.setChoice1("I like to receive notes of affirmation from you.");
        q1.setChoice2("I like it when you hug me.");

        surveyItem q2 = new surveyItem();
        q2.setQuestion("Please pick the opinion that best reflects your preferences");
        q2.setChoice1("I like to spend one-on-one time with you.");
        q2.setChoice2("I feel loved when you give me practical help.");

        surveyItem q3 = new surveyItem();
        q3.setQuestion("Please pick the opinion that best reflects your preferences");
        q3.setChoice1("I like it when you give me gifts.");
        q3.setChoice2("I like taking long walks with you.");

        surveyItems.add(q1);
        surveyItems.add(q2);
        surveyItems.add(q3);

        surveyAdapter = new surveyAdapter(surveyItems);
    }*/

}