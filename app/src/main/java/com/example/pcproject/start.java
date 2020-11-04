package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class start extends AppCompatActivity {
    private onBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private Button buttonOnboardingAction;
    private Button createAccount;
    private TextView login;

    private Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        layoutOnboardingIndicators = findViewById(R.id.layoutIndicator);
        buttonOnboardingAction = findViewById(R.id.buttonOnboarding);
        createAccount = findViewById(R.id.signupButton);
        login = findViewById(R.id.textViewSignUp1);

        //temp
        temp = findViewById(R.id.tempButton);

        setupOnboardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.viewPager);
        onboardingViewPager.setAdapter(onBoardingAdapter);

        onBoardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount())
                {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), ActiveLogin.class));
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActiveSignUp.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActiveLogin.class));
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), initialSurvey.class));
            }
        });
    }

    private void setupOnboardingItems() {
        List<onBoardingItem> onBoardingItems = new ArrayList<>();
        onBoardingItem layout1 = new onBoardingItem();
        layout1.setInitial("Get clear about your expectations, dealbreakers, and your love language");
        layout1.setSecond("So you can find the root of your relationship");

        onBoardingItem layout2 = new onBoardingItem();
        layout2.setInitial("Recognize what makes the interaction work and what could be improved");
        layout2.setSecond("So you will understand potential partners");

        onBoardingItem layout3 = new onBoardingItem();
        layout3.setInitial("Reflect on your relationships as time goes by");
        layout3.setSecond("So you can make a positive change and grow");

        onBoardingItems.add(layout1);
        onBoardingItems.add(layout2);
        onBoardingItems.add(layout3);

        onBoardingAdapter = new onBoardingAdapter(onBoardingItems);
    }

    private void onBoardingIndicators(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
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
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i ++)
        {
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
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
        if(index == onBoardingAdapter.getItemCount()-1)
        {
            buttonOnboardingAction.setText("Start");
        }
        else
        {
            buttonOnboardingAction.setText("Next");
        }
    }
}