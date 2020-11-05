package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class initialSurvey extends AppCompatActivity implements surveyPage1Fragment.surveyPage1FragmentListener, surveypage2fragment.surveyPage2FragmentListener, surveypage3fragment.surveyPage3FragmentListener, surveypage4fragment.surveyPage4FragmentListener, surveypage5fragment.surveyPage5FragmentListener, surveypage6fragment.surveyPage6FragmentListener{

    private static final String TAG = "initialSurvey";

    private surveyPage1Fragment page1Fragment;
    private surveypage2fragment page2Fragment;
    private surveypage3fragment page3Fragment;
    private surveypage4fragment page4Fragment;
    private surveypage5fragment page5Fragment;
    private surveypage6fragment page6Fragment;
    private surveyQ1fragment surveyQ1Fragment;

    FragmentManager fragmentManager;

    //User Variables(AppIntention, IdeaRelationshipFeel, RelationshipDesire)
    private AppUser appUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        fragmentManager=getSupportFragmentManager();
        page1Fragment = new surveyPage1Fragment();
        appUser = new AppUser();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d(TAG, "Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());
            }
        });
        getSupportFragmentManager().beginTransaction()
            .add(R.id.mainLayout, page1Fragment).commit();
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


    @Override
    public void onInputPage2Sent(String mString) {
        Log.d(TAG,"onInputPage2Sent coming.....1");

        if(!mString.isEmpty())
        {
            appUser.setAppIntention(mString);
        }
        else
        {
            throw new RuntimeException(this.toString()
                    + "The AppIntention should not be empty");
        }
    }

    @Override
    public void onInputPage2Next() {
        if(appUser.getAppIntention() != null)
        {
            Log.d(TAG,"User Intention for this App:" + appUser.getAppIntention());
            page3Fragment = new surveypage3fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, page3Fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please select above option", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInputPage3Next(String mString) {
        Log.d(TAG,"onInputPage3Sent coming.....1");
        if(!mString.isEmpty())
        {
            appUser.setIdealRelationshipFeel(mString);
            Log.d(TAG,"User IdeaRelationshipFeel:" + appUser.getIdealRelationshipFeel());
            page4Fragment = new surveypage4fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, page4Fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please fill above option", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInputPage4Next(String mString) {
        Log.d(TAG,"onInputPage4Sent coming.....1");
        if(!mString.isEmpty())
        {
            appUser.setRelationshipDesire(mString);
            Log.d(TAG,"User RelationshipDesire:" + appUser.getRelationshipDesire());
            page5Fragment = new surveypage5fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, page5Fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please fill above option", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInputPage5Next() {
        Log.d(TAG,"onInputPage5Sent coming.....1");
        page6Fragment = new surveypage6fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, page6Fragment)
                .commit();
    }

    @Override
    public void onInputPage6Next() {
        Log.d(TAG,"onInputPage6Sent coming.....1");
        surveyQ1Fragment = new surveyQ1fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, surveyQ1Fragment)
                .commit();
    }
}