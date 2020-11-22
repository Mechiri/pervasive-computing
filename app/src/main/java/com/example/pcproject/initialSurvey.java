package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/*

Pages for Initial Survey - Activities which displays all the 15 survey questions

*/

public class initialSurvey extends AppCompatActivity implements
        surveyPage1Fragment.surveyPage1FragmentListener,
        surveypage2fragment.surveyPage2FragmentListener,
        surveypage3fragment.surveyPage3FragmentListener,
        surveypage4fragment.surveyPage4FragmentListener,
        surveypage5fragment.surveyPage5FragmentListener,
        surveypage6fragment.surveyPage6FragmentListener,
        surveyQ1fragment.surveyQ1FragmentListener,
        surveyQ2fragment.surveyQ2FragmentListener,
        surveyQ3fragment.surveyQ3FragmentListener,
        surveyQ4fragment.surveyQ4FragmentListener,
        surveyQ5fragment.surveyQ5FragmentListener,
        surveyQ6fragment.surveyQ6FragmentListener,
        surveyQ7fragment.surveyQ7FragmentListener,
        surveyQ8fragment.surveyQ8FragmentListener,
        surveyQ9fragment.surveyQ9FragmentListener,
        surveyQ10fragment.surveyQ10FragmentListener,
        surveyQ11fragment.surveyQ11FragmentListener,
        surveyQ12fragment.surveyQ12FragmentListener,
        surveyQ13fragment.surveyQ13FragmentListener,
        surveyQ14fragment.surveyQ14FragmentListener,
        surveyQ15fragment.surveyQ15FragmentListener{

    private static final String TAG = "initialSurvey";

    private surveyPage1Fragment page1Fragment;
    private surveypage2fragment page2Fragment;
    private surveypage3fragment page3Fragment;
    private surveypage4fragment page4Fragment;
    private surveypage5fragment page5Fragment;
    private surveypage6fragment page6Fragment;
    private surveyQ1fragment surveyQ1Fragment;
    private surveyQ2fragment surveyQ2fragment;
    private surveyQ3fragment surveyQ3fragment;
    private surveyQ4fragment surveyQ4fragment;
    private surveyQ5fragment surveyQ5fragment;
    private surveyQ6fragment surveyQ6fragment;
    private surveyQ7fragment surveyQ7fragment;
    private surveyQ8fragment surveyQ8fragment;
    private surveyQ9fragment surveyQ9fragment;
    private surveyQ10fragment surveyQ10fragment;
    private surveyQ11fragment surveyQ11fragment;
    private surveyQ12fragment surveyQ12fragment;
    private surveyQ13fragment surveyQ13fragment;
    private surveyQ14fragment surveyQ14fragment;
    private surveyQ15fragment surveyQ15fragment;

    FragmentManager fragmentManager;

    //User Variables(AppIntention, IdeaRelationshipFeel, RelationshipDesire, Love Languages)
    private AppUser appUser;
    private String resultLanguages;

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

    //Fetch Data from Survey Page1
    @Override
    public void onInputPage1Sent() {
        Log.d(TAG,"onInputPage1Sent coming.....1");
        page2Fragment = new surveypage2fragment();
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.mainLayout, page2Fragment)
            .commit();
    }


    //Fetch Data from Survey Page2
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

    //Fetch Data from Survey Page3
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

    //Fetch Data from Survey Page4
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

    //Fetch Data from Survey Page5
    @Override
    public void onInputPage5Next() {
        Log.d(TAG,"onInputPage5Sent coming.....1");
        page6Fragment = new surveypage6fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, page6Fragment)
                .commit();
    }

    //Fetch Data from Survey Page6
    @Override
    public void onInputPage6Next() {
        Log.d(TAG,"onInputPage6Sent coming.....1");
        surveyQ1Fragment = new surveyQ1fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, surveyQ1Fragment)
                .commit();
    }



    protected void calledWordsOfAffirmation()
    {
        appUser.setWordsOfAffirmation( appUser.getWordsOfAffirmation() + 1 );
    }
    protected void calledQualityTime()
    {
        appUser.setQualityTime( appUser.getQualityTime() + 1 );
    }
    protected void calledReceivingGifts()
    {
        appUser.setReceivingGifts( appUser.getReceivingGifts() + 1 );
    }
    protected void calledActsOfService()
    {
        appUser.setActsOfService( appUser.getActsOfService() + 1 );
    }
    protected void calledPhysicalTouch()
    {
        appUser.setPhysicalTouch( appUser.getPhysicalTouch() + 1 );
    }

    //Fetch Data from Survey Question1
    @Override
    public void calledQ1A(String string) {
        Log.d(TAG,"Q1A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ1E(String string) {
        Log.d(TAG,"Q1E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ1Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q1A"))
                calledWordsOfAffirmation();
            if(resultLanguages.equals("Q1E"))
                calledPhysicalTouch();

            resultLanguages = null;
            surveyQ2fragment = new surveyQ2fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ2fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+ appUser.getWordsOfAffirmation() +" QT:"
                        +appUser.getQualityTime() + " RG:"
                        +appUser.getReceivingGifts() + " AS:"
                        +appUser.getActsOfService() + " PT"
                        +appUser.getPhysicalTouch() + " End");
    }

    //Fetch Data from Survey Question2
    @Override
    public void calledQ2B(String string) {
        Log.d(TAG,"Q2B coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ2D(String string) {
        Log.d(TAG,"Q2D coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ2Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q2B"))
                calledQualityTime();
            if(resultLanguages.equals("Q2D"))
                calledActsOfService();

            resultLanguages = null;
            surveyQ3fragment = new surveyQ3fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ3fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }

    //Fetch Data from Survey Question3
    @Override
    public void calledQ3C(String string) {
        Log.d(TAG,"Q3C coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ3B(String string) {
        Log.d(TAG,"Q3B coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ3Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q3C"))
                calledReceivingGifts();
            if(resultLanguages.equals("Q3B"))
                calledQualityTime();

            resultLanguages = null;
            surveyQ4fragment = new surveyQ4fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ4fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question4
    @Override
    public void calledQ4D(String string) {
        Log.d(TAG,"Q4D coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ4E(String string) {
        Log.d(TAG,"Q4E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ4Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q4D"))
                calledActsOfService();
            if(resultLanguages.equals("Q4E"))
                calledPhysicalTouch();

            resultLanguages = null;
            surveyQ5fragment = new surveyQ5fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ5fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+ appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question5
    @Override
    public void calledQ5E(String string) {
        Log.d(TAG,"Q5E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ5C(String string) {
        Log.d(TAG,"Q5C coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ5Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q5E"))
                calledPhysicalTouch();
            if(resultLanguages.equals("Q5C"))
                calledReceivingGifts();

            resultLanguages = null;
            surveyQ6fragment = new surveyQ6fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ6fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question6
    @Override
    public void calledQ6B(String string) {
        Log.d(TAG,"Q6B coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ6E(String string) {
        Log.d(TAG,"Q6E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ6Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q6B"))
                calledQualityTime();
            if(resultLanguages.equals("Q6E"))
                calledPhysicalTouch();

            resultLanguages = null;
            surveyQ7fragment = new surveyQ7fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ7fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+ appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question7
    @Override
    public void calledQ7A(String string) {
        Log.d(TAG,"Q7A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ7C(String string) {
        Log.d(TAG,"Q7C coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ7Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q7A"))
                calledWordsOfAffirmation();
            if(resultLanguages.equals("Q7C"))
                calledReceivingGifts();

            resultLanguages = null;
            surveyQ8fragment = new surveyQ8fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ8fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }

    //Fetch Data from Survey Question8
    @Override
    public void calledQ8E(String string) {
        Log.d(TAG,"Q8E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ8A(String string) {
        Log.d(TAG,"Q8A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ8Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q8E"))
                calledPhysicalTouch();
            if(resultLanguages.equals("Q8A"))
                calledWordsOfAffirmation();

            resultLanguages = null;
            surveyQ9fragment = new surveyQ9fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ9fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");    }

    //Fetch Data from Survey Question9
    @Override
    public void calledQ9B(String string) {
        Log.d(TAG,"Q9B coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ9C(String string) {
        Log.d(TAG,"Q9C coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ9Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q9B"))
                calledQualityTime();
            if(resultLanguages.equals("Q9C"))
                calledReceivingGifts();

            resultLanguages = null;
            surveyQ10fragment = new surveyQ10fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ10fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question10
    @Override
    public void calledQ10D(String string) {
        Log.d(TAG,"Q10D coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ10A(String string) {
        Log.d(TAG,"Q10A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ10Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q10D"))
                calledActsOfService();
            if(resultLanguages.equals("Q10A"))
                calledWordsOfAffirmation();

            resultLanguages = null;
            surveyQ11fragment = new surveyQ11fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ11fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }

    //Fetch Data from Survey Question11
    @Override
    public void calledQ11B(String string) {
        Log.d(TAG,"Q11B coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ11A(String string) {
        Log.d(TAG,"Q11A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ11Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q11B"))
                calledQualityTime();
            if(resultLanguages.equals("Q11A"))
                calledWordsOfAffirmation();

            resultLanguages = null;
            surveyQ12fragment = new surveyQ12fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ12fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question12
    @Override
    public void calledQ12E(String string) {
        Log.d(TAG,"Q12E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ12D(String string) {
        Log.d(TAG,"Q12D coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ12Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q12E"))
                calledPhysicalTouch();
            if(resultLanguages.equals("Q12D"))
                calledActsOfService();

            resultLanguages = null;
            surveyQ13fragment = new surveyQ13fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ13fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }


    //Fetch Data from Survey Question13
    @Override
    public void calledQ13A(String string) {
        Log.d(TAG,"Q13A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ13C(String string) {
        Log.d(TAG,"Q13C coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ13Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q13A"))
                calledWordsOfAffirmation();
            if(resultLanguages.equals("Q13C"))
                calledReceivingGifts();

            resultLanguages = null;
            surveyQ14fragment = new surveyQ14fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ14fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }

    //Fetch Data from Survey Question14
    @Override
    public void calledQ14E(String string) {
        Log.d(TAG,"Q14E coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ14B(String string) {
        Log.d(TAG,"Q14B coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ14Next() {
        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q14E"))
                calledPhysicalTouch();
            if(resultLanguages.equals("Q14B"))
                calledQualityTime();

            resultLanguages = null;
            surveyQ15fragment = new surveyQ15fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, surveyQ15fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages:" + "WA:"+ appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }

    //Fetch Data from Survey Question15
    @Override
    public void calledQ15A(String string) {
        Log.d(TAG,"Q15A coming.....1");
        resultLanguages = string;
    }

    @Override
    public void calledQ15D(String string) {
        Log.d(TAG,"Q15D coming.....1");
        resultLanguages = string;
    }

    @Override
    public void onInputQ15Next() {
        Log.d(TAG, "onInputQ15Next Uploading .............. 1");

        if(resultLanguages != null && !resultLanguages.isEmpty())
        {
            if(resultLanguages.equals("Q15A"))
                calledWordsOfAffirmation();
            if(resultLanguages.equals("Q15D"))
                calledActsOfService();
            Log.d(TAG, "onInputQ15Next Uploading .............. 5");
            resultLanguages = null;
            Log.d(TAG, "onInputQ15Next Uploading .............. 4");
            appUser.calculateLoveLanguagesRatio();
            Log.d(TAG, "Before Uploading .............. 1");
            appUser.uploadAllData(this);
            Log.d(TAG, "After Uploading .............. 1");
            Intent intent = new Intent(initialSurvey.this, SurveyResult.class);
            intent.putExtra("wordsOfAffirmation", appUser.getWordsOfAffirmation());
            intent.putExtra("qualityTime", appUser.getQualityTime());
            intent.putExtra("receivingGifts", appUser.getReceivingGifts());
            intent.putExtra("actsOfService", appUser.getActsOfService());
            intent.putExtra("physicalTouch", appUser.getPhysicalTouch());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please choose above option", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Love Languages --:" + "WA:" + appUser.getWordsOfAffirmation() +" QT:"
                +appUser.getQualityTime() + " RG:"
                +appUser.getReceivingGifts() + " AS:"
                +appUser.getActsOfService() + " PT"
                +appUser.getPhysicalTouch() + " End");
    }
}