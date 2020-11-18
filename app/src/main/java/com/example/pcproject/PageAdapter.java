package com.example.pcproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private eventTab eventTabs;
    private String partnerProfileName;
    private Context context;

    public <numTabs> PageAdapter(@NonNull FragmentManager fm, int numTabs, String partnerProfileName, Context context) {
        super(fm);
        this.context = context;
        this.partnerProfileName = partnerProfileName;
        eventTabs = new eventTab(context, partnerProfileName);
        this.numOfTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return eventTabs;
            case 1:
                return new partnerProfileTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
