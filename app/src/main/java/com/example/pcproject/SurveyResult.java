package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SurveyResult extends AppCompatActivity {

    private static final String TAG = "SurveyResult";

    private Button dashboardB;
    private TextView loveLanguage;
    private TextView loveLanguageDescription;

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
    private String stringTag;
    private String stringDes;
    int largest = 0, largestValue = 0;

    ArrayList<PieEntry> visitors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        dashboardB = findViewById(R.id.toDashboardB);
        loveLanguage = findViewById(R.id.loveLangTextView);
        loveLanguageDescription = findViewById(R.id.loveLangDescription);
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
            stringTag = "Words Of Affirmation\n";
            stringDes = affirmationDesc +"\n";
        }
        if(result.get(1) == largestValue)
        {
            if(stringTag == null)
            {
                stringTag = "Quality Time\n";
                stringDes = timeDesc +"\n";
            }
            else
            {
                stringTag += "Quality Time\n";
                stringDes += timeDesc +"\n";
            }
        }
        if(result.get(2) == largestValue)
        {
            if(stringTag == null)
            {
                stringTag = "Receiving Gifts\n";
                stringDes = giftsDesc +"\n";
            }
            else
            {
                stringTag += "Receiving Gifts\n";
                stringDes += giftsDesc +"\n";
            }
        }
        if(result.get(3) == largestValue)
        {
            if(stringTag == null)
            {
                stringTag = "Acts Of Service\n";
                stringDes = serviceDesc +"\n";
            }
            else
            {
                stringTag += "Acts Of Service\n";
                stringDes += serviceDesc +"\n";
            }

        }
        if(result.get(4) == largestValue)
        {
            if(stringTag == null)
            {
                stringTag = "Physical Touch\n";
                stringDes = touchDesc +"\n";
            }
            else
            {
                stringTag += "Physical Touch\n";
                stringDes += touchDesc +"\n";
            }

        }
        loveLanguage.setText(stringTag);
        loveLanguageDescription.setText(stringDes);

        Log.d(TAG, "Coming Here .............. 4");
        pieChartDisplay();
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
}