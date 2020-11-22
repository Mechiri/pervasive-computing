package com.example.pcproject;

/*
    Serves as accessor and mutator methods for other Event
 */

public class OtherEvent {
    //Attributes
    private String eventStatus;
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

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
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
