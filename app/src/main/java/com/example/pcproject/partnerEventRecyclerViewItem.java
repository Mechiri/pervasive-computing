package com.example.pcproject;

public class partnerEventRecyclerViewItem {
    private String eventTitle;
    private String eventDate;
    private int eventImg;
    private Integer physicalTouch;

    public partnerEventRecyclerViewItem(String eventTitle, String eventDate, int eventImg, Integer physicalTouch) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventImg = eventImg;
        this.physicalTouch = physicalTouch;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String partnerName) {
        this.eventTitle = partnerName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventImg() {
        return eventImg;
    }

    public void setEventImg(int eventImg) {
        this.eventImg = eventImg;
    }

    public Integer getPhysicalTouch() {
        return physicalTouch;
    }

    public void setPhysicalTouch(Integer physicalTouch) {
        this.physicalTouch = physicalTouch;
    }
}
