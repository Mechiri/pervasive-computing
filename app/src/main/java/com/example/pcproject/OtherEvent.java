package com.example.pcproject;

public class OtherEvent {
    //Attributes
    private Integer eventStatus;
    private String title;
    private String describeEvent;
    private Integer rateOverallExperience;
    private String otherNotes;

    public OtherEvent() {
        this.eventStatus = null;
        this.title = null;
        this.describeEvent = null;
        this.rateOverallExperience = null;
        this.otherNotes = null;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribeEvent() {
        return describeEvent;
    }

    public void setDescribeEvent(String describeEvent) {
        this.describeEvent = describeEvent;
    }

    public Integer getRateOverallExperience() {
        return rateOverallExperience;
    }

    public void setRateOverallExperience(Integer rateOverallExperience) {
        this.rateOverallExperience = rateOverallExperience;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }
}
