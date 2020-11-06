package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SurveyResult extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        dashboardB = findViewById(R.id.toDashboardB);
        loveLanguage = findViewById(R.id.loveLangTextView);
        loveLanguageDescription = findViewById(R.id.loveLangDescription);



    }
}