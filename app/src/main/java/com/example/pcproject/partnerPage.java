package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class partnerPage extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager partnerViewPager;
    private TabItem eventTab, partnerProfileTab;
    public PageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_page);

        tabLayout = (TabLayout) findViewById(R.id.PartnerTabLayout);
        eventTab = (TabItem) findViewById(R.id.eventTab);
        partnerProfileTab = (TabItem) findViewById(R.id.partnerProfileTab);

        partnerViewPager =findViewById(R.id.partnerViewPager);

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        partnerViewPager.setAdapter(pagerAdapter);

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