package com.example.pcproject;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Map;

/*
    Page adapter for event page tab
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private eventTab eventTabs;
    private partnerProfileTab partnerProfileTabs;
    private String partnerProfileName;
    private Context context;
    Map<String, Event> eventMap;
    private Map<String, Bitmap> eventsPictures;

    //Instantiate objects for partner event tab
    public <numTabs> PageAdapter(@NonNull FragmentManager fm, int numTabs, String partnerProfileName, Context context, Map<String, Event> eventMap, Map<String, Bitmap> eventsPictures) {
        super(fm);
        this.context = context;
        this.partnerProfileName = partnerProfileName;
        this.eventMap = eventMap;
        this.eventsPictures = eventsPictures;
        eventTabs = new eventTab(context, partnerProfileName, eventMap, eventsPictures);
        partnerProfileTabs = new partnerProfileTab(partnerProfileName, context);
        this.numOfTabs = numTabs;
    }

    //Returns the position of the current tab the user is on
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return eventTabs;
            case 1:
                return partnerProfileTabs;
            default:
                return null;
        }
    }

    //Returns the total number of tabs
    @Override
    public int getCount() {
        return numOfTabs;
    }

    //Returns the current item position
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    //Returns the eventTab
    public eventTab getEventTabs() {
        return eventTabs;
    }

    //Instantiates eventTabs
    public void setEventTabs(eventTab eventTabs) {
        this.eventTabs = eventTabs;
    }
}
