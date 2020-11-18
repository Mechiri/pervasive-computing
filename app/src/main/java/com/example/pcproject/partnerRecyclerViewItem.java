package com.example.pcproject;

import android.graphics.Bitmap;

public class partnerRecyclerViewItem {

    private Bitmap img;
    private String partnerName;
    private String numOfEvents;
    private String partnerStatus;
    private String profileName;

    public partnerRecyclerViewItem(Bitmap img, String name, String numEvents, String status, String profileName)
    {
        this.img = img;
        this.partnerName = name;
        this.numOfEvents = numEvents;
        this.partnerStatus = status;
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getNumOfEvents() {
        return numOfEvents;
    }

    public void setNumOfEvents(String numOfEvents) {
        this.numOfEvents = numOfEvents;
    }

    public String getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(String partnerStatus) {
        this.partnerStatus = partnerStatus;
    }
}
