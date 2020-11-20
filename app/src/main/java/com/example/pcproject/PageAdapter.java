package com.example.pcproject;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Map;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private eventTab eventTabs;
    private partnerProfileTab partnerProfileTabs;
    private String partnerProfileName;
    private Context context;
    Map<String, Event> eventMap;
    private Map<String, Bitmap> eventsPictures;

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

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public eventTab getEventTabs() {
        return eventTabs;
    }

    public void setEventTabs(eventTab eventTabs) {
        this.eventTabs = eventTabs;
    }
}
