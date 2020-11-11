package com.example.pcproject;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    //Attributes
    private String eventName;
    private String partnerName;
    private Date eventDate;
    private String eventType;

    private Integer wordsOfAffirmation;
    private Integer qualityTime;
    private Integer receivingGifts;
    private Integer actsOfService;
    private Integer physicalTouch;

    private ArrayList<String> newTraitsLearned;
    private ArrayList<String> pictures;

    private String talkAbout;
    private String youReallyLiked;
    private String youDidNotLiked;
    private String notable;

    private DateEvent dateEvent;
    private FightEvent fightEvent;
    private OtherEvent otherEvent;

    public Event() {
        this.eventName = null;
        this.partnerName = null;
        this.eventDate = null;
        this.eventType = null;
        this.wordsOfAffirmation = null;
        this.qualityTime = null;
        this.receivingGifts = null;
        this.actsOfService = null;
        this.physicalTouch = null;
        this.newTraitsLearned = null;
        this.pictures = null;
        this.talkAbout = null;
        this.youReallyLiked = null;
        this.youDidNotLiked = null;
        this.notable = null;

        this.dateEvent =  new DateEvent();
        this.fightEvent = new FightEvent();
        this.otherEvent = new OtherEvent();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getWordsOfAffirmation() {
        return wordsOfAffirmation;
    }

    public void setWordsOfAffirmation(Integer wordsOfAffirmation) {
        this.wordsOfAffirmation = wordsOfAffirmation;
    }

    public Integer getQualityTime() {
        return qualityTime;
    }

    public void setQualityTime(Integer qualityTime) {
        this.qualityTime = qualityTime;
    }

    public Integer getReceivingGifts() {
        return receivingGifts;
    }

    public void setReceivingGifts(Integer receivingGifts) {
        this.receivingGifts = receivingGifts;
    }

    public Integer getActsOfService() {
        return actsOfService;
    }

    public void setActsOfService(Integer actsOfService) {
        this.actsOfService = actsOfService;
    }

    public Integer getPhysicalTouch() {
        return physicalTouch;
    }

    public void setPhysicalTouch(Integer physicalTouch) {
        this.physicalTouch = physicalTouch;
    }

    public ArrayList<String> getNewTraitsLearned() {
        return newTraitsLearned;
    }

    public void setNewTraitsLearned(ArrayList<String> newTraitsLearned) {
        this.newTraitsLearned = newTraitsLearned;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public String getTalkAbout() {
        return talkAbout;
    }

    public void setTalkAbout(String talkAbout) {
        this.talkAbout = talkAbout;
    }

    public String getYouReallyLiked() {
        return youReallyLiked;
    }

    public void setYouReallyLiked(String youReallyLiked) {
        this.youReallyLiked = youReallyLiked;
    }

    public String getYouDidNotLiked() {
        return youDidNotLiked;
    }

    public void setYouDidNotLiked(String youDidNotLiked) {
        this.youDidNotLiked = youDidNotLiked;
    }

    public String getNotable() {
        return notable;
    }

    public void setNotable(String notable) {
        this.notable = notable;
    }

    public DateEvent getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(DateEvent dateEvent) {
        this.dateEvent = dateEvent;
    }

    public FightEvent getFightEvent() {
        return fightEvent;
    }

    public void setFightEvent(FightEvent fightEvent) {
        this.fightEvent = fightEvent;
    }

    public OtherEvent getOtherEvent() {
        return otherEvent;
    }

    public void setOtherEvent(OtherEvent otherEvent) {
        this.otherEvent = otherEvent;
    }
}
