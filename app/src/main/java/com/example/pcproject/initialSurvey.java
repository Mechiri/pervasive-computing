package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class initialSurvey extends AppCompatActivity implements surveyPage1Fragment.surveyPage1FragmentListener{

    private static final String TAG = "initialSurvey";

    private surveyPage1Fragment page1Fragment;
    private surveypage2fragment page2Fragment;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        fragmentManager=getSupportFragmentManager();
        page1Fragment = new surveyPage1Fragment();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d(TAG, "Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());
            }
        });
        getSupportFragmentManager().beginTransaction()
            .add(R.id.mainLayout, page1Fragment).commit();


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
    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onInputPage1Sent() {
        Log.d(TAG,"onInputPage1Sent coming.....1");
        page2Fragment = new surveypage2fragment();
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.mainLayout, page2Fragment)
            .commit();
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