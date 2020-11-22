package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    Partner Page that conains access to user profile, event tab, and partner profile tab
 */

public class partnerPage extends AppCompatActivity {

    private static final String TAG = "partnerPage";

    private TabLayout tabLayout;
    private ViewPager partnerViewPager;
    private TabItem eventTab, partnerProfileTab;
    public PageAdapter pagerAdapter;
    private Button userProfileB;
    private String partnerProfileName;
    Map<String, Event> eventMap;
    private Map<String, Bitmap> eventsPictures;
    private Partner partner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_page);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("ProfileName");
        partnerProfileName = name;

        eventMap =new HashMap<String, Event>();
        eventsPictures =new HashMap<String, Bitmap>();



        //getEventPic(partnerProfileName, partner.getTotalEvents());

        Log.d(TAG, "onCreate: PartnerProfileName = "+partnerProfileName);
        userProfileB = findViewById(R.id.userProfileB);

        //Starts user profile class
        userProfileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(partnerPage.this, userProfile.class));
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.PartnerTabLayout);
        eventTab = (TabItem) findViewById(R.id.eventTab);
        partnerProfileTab = (TabItem) findViewById(R.id.partnerProfileTab);
        partnerViewPager =findViewById(R.id.partnerViewPager);

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), partnerProfileName, this, eventMap, eventsPictures);
        partnerViewPager.setAdapter(pagerAdapter);

        Log.d(TAG,"Map Size: "+eventMap.size()+" coming......................1");

        //Recognizes which tab the user selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                partnerViewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if (tab.getPosition() == 1)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        partnerViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}