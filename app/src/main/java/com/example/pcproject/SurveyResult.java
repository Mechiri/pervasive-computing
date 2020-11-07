package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class SurveyResult extends AppCompatActivity {

    private static final String TAG = "SurveyResult";

    private surveyResultsAdapter SurveyResultsAdapter;
    private LinearLayout layoutSurveyResultsIndicators;
    private Button dashboardB;

    private String affirmationDesc = "This love language expresses love with words that build up your partner. Verbal compliments don't have to be complicated. Compliments and an \"I love you\" can go a long way. On the other hand, negative or insulting comments can hurt you and it could take them longer to forgive than others.";
    private String serviceDesc = "Actions speak louder than words\". Cooking a meal, doing the laundry, and picking up a prescription are all acts of service. They require some thought, time, and effort. All of these things should be done with positivity to be considered an expression of love. Actions out of obligation or with a negative tone are something else entirely.";
    private String giftsDesc = "The receiver of gifts thrives on the love, thoughtfulness, and effort behind the gift. If you speak this language, the perfect gift or gesture shows that you are known, you are cared for, and you are prized above whatever was sacrificed to bring the gift to you. Gifts are heartfelt symbols to you of someone else's love and affection for you.";
    private String timeDesc = "This love language is all about undivided attention. You don't just want to be included during this period of time, you want to be the center of your partner's attention. You appreciate your partner if he or she dedicates time together without all of the distractions. That will help them feel comforted in the relationship.";
    private String touchDesc = "Nothing is more impactful than the physical touch of your partner." +
            " You do feel more connected and safe in a relationship by holding" +
            " hands, kissing, hugging, etc. On the other hand, you will feel" +
            " unloved without physical contact.";

    //Pie Chart
    private PieChart piechart;
    private PieDataSet pieDataSet;
    private PieData pieData;

    private ArrayList<Integer> result = new ArrayList<>();
    private String stringTagWoA;
    private String stringDesWoA;
    private String stringTagQT;
    private String stringDesQT;
    private String stringTagRG;
    private String stringDesRG;
    private String stringTagAoS;
    private String stringDesAoS;
    private String stringTagPT;
    private String stringDesPT;
    int largest = 0, largestValue = 0;

    ArrayList<PieEntry> visitors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        layoutSurveyResultsIndicators = findViewById(R.id.layoutSurveyIndicator2);
        dashboardB = findViewById(R.id.toDashboardB);
        piechart = findViewById(R.id.loveLanguagesDisplay1);

        Intent iCurrentIntent = getIntent();
        result.add(iCurrentIntent.getIntExtra("wordsOfAffirmation", 0));
        result.add(iCurrentIntent.getIntExtra("qualityTime",0));
        result.add(iCurrentIntent.getIntExtra("receivingGifts", 0));
        result.add(iCurrentIntent.getIntExtra("actsOfService", 0));
        result.add(iCurrentIntent.getIntExtra("physicalTouch", 0));

        for ( int i = 0; i < result.size(); i++ )
        {
            if ( result.get(i) > result.get(largest))
                largest = i;
        }
        largestValue = result.get(largest);

        if(result.get(0) == largestValue)
        {
            stringTagWoA = "Words Of Affirmation\n";
            stringDesWoA = affirmationDesc +"\n";
        }
        if(result.get(1) == largestValue)
        {
                stringTagQT = "Quality Time\n";
                stringDesQT = timeDesc +"\n";
        }
        if(result.get(2) == largestValue)
        {
                stringTagRG = "Receiving Gifts\n";
                stringDesRG = giftsDesc +"\n";
        }
        if(result.get(3) == largestValue)
        {
                stringTagAoS = "Acts Of Service\n";
                stringDesAoS = serviceDesc +"\n";
        }
        if(result.get(4) == largestValue)
        {
                stringTagPT = "Physical Touch\n";
                stringDesPT = touchDesc + "\n";
        }

        setSurveyResultsItems();
        final ViewPager2 surveyResultsViewPager = findViewById(R.id.viewPagerSurveyResults);
        surveyResultsViewPager.setAdapter(SurveyResultsAdapter);

        surveyResultsIndicators();
        setCurrentSurveyResultIndicator(0);

        surveyResultsViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSurveyResultIndicator(position);
            }
        });

        Log.d(TAG, "Coming Here .............. 4");
        pieChartDisplay();

        dashboardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //next activity
            }
        });
    }
    private void setSurveyResultsItems()
    {
        List<onBoardingItem> onBoardingItems = new ArrayList<>();
        if(stringTagWoA != null)
        {
            onBoardingItem layout1 = new onBoardingItem();
            layout1.setInitial(stringTagWoA);
            layout1.setSecond(stringDesWoA);
            onBoardingItems.add(layout1);
        }
        if(stringTagQT != null)
        {
            onBoardingItem layout2 = new onBoardingItem();
            layout2.setInitial(stringTagQT);
            layout2.setSecond(stringDesQT);
            onBoardingItems.add(layout2);
        }
        if(stringTagRG != null)
        {
            onBoardingItem layout3 = new onBoardingItem();
            layout3.setInitial(stringTagRG);
            layout3.setSecond(stringDesRG);
            onBoardingItems.add(layout3);
        }
        if(stringTagAoS != null)
        {
            onBoardingItem layout4 = new onBoardingItem();
            layout4.setInitial(stringTagAoS);
            layout4.setSecond(stringDesAoS);
            onBoardingItems.add(layout4);
        }
        if(stringTagPT != null)
        {
            onBoardingItem layout5 = new onBoardingItem();
            layout5.setInitial(stringTagPT);
            layout5.setSecond(stringDesPT);
            onBoardingItems.add(layout5);
        }
        SurveyResultsAdapter = new surveyResultsAdapter(onBoardingItems);
    }
    void pieChartDisplay()
    {
        Log.d(TAG, "Coming Here .............. 5");
        visitors.add(new PieEntry(result.get(0), "Words Of Affirmation"));
        visitors.add(new PieEntry(result.get(1), "Quality Time"));
        visitors.add(new PieEntry(result.get(2), "Receiving Gifts"));
        visitors.add(new PieEntry(result.get(3), "Acts Of Service"));
        visitors.add(new PieEntry(result.get(4), "Physical Touch"));

        pieDataSet = new PieDataSet(visitors, "Visit");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        pieData = new PieData(pieDataSet);

        piechart.setData(pieData);
        piechart.setCenterText("Love Languages");
        piechart.getDescription().setEnabled(false);
        piechart.animate();
    }

    private void surveyResultsIndicators(){
        ImageView[] indicators = new ImageView[SurveyResultsAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for(int i = 0; i < indicators.length; i++)
        {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.inactive_dot
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutSurveyResultsIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentSurveyResultIndicator(int index){
        int childCount = layoutSurveyResultsIndicators.getChildCount();
        for (int i = 0; i < childCount; i ++)
        {
            ImageView imageView = (ImageView)layoutSurveyResultsIndicators.getChildAt(i);
            if(i == index)
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.selected_dot
                ));
            }
            else
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.inactive_dot
                ));
            }
        }
    }
}