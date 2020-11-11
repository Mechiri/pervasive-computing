package com.example.pcproject;

public class partnerRecyclerViewItem {

    private int img;
    private String partnerName;
    private String numOfEvents;
    private String partnerStatus;

    public partnerRecyclerViewItem(int img, String name, String numEvents, String status)
    {
        this.img = img;
        this.partnerName = name;
        this.numOfEvents = numEvents;
        this.partnerStatus = status;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
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
