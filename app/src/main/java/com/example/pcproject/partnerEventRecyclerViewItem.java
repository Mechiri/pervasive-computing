package com.example.pcproject;

import android.graphics.Bitmap;

/*
    Serves as accessor and mutator methods each partner Event
 */

public class partnerEventRecyclerViewItem {
    private String eventTitle;
    private String eventDate;
    private Bitmap eventImg;
    private Integer physicalTouch;
    private Integer wordsOfAff;
    private Integer gifts;
    private Integer actOfService;
    private Integer qualityTime;
    private String eventType;
    private String traitsLearned;
    private String talkAbout;
    private String youReallyLiked;
    private String youDidNotLiked;
    private String partnerProfileName;
    private String parentName;

    public partnerEventRecyclerViewItem(String eventTitle,
                                        String eventDate,
                                        Bitmap eventImg,
                                        Integer physicalTouch,
                                        Integer wordsOfAff,
                                        Integer gifts,
                                        Integer actOfService,
                                        Integer qualityTime,
                                        String eventType,
                                        String traitsLearned,
                                        String talkAbout,
                                        String youReallyLiked,
                                        String youDidNotLiked,
                                        String partnerProfileName,
                                        String parentName) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventImg = eventImg;
        this.physicalTouch = physicalTouch;
        this.wordsOfAff = wordsOfAff;
        this.gifts = gifts;
        this.actOfService = actOfService;
        this.qualityTime = qualityTime;
        this.eventType = eventType;
        this.traitsLearned = traitsLearned;
        this.talkAbout = talkAbout;
        this.youReallyLiked = youReallyLiked;
        this.youDidNotLiked = youDidNotLiked;
        this.partnerProfileName = partnerProfileName;
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPartnerProfileName() {
        return partnerProfileName;
    }

    public void setPartnerProfileName(String partnerProfileName) {
        this.partnerProfileName = partnerProfileName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTraitsLearned() {
        return traitsLearned;
    }

    public void setTraitsLearned(String traitsLearned) {
        this.traitsLearned = traitsLearned;
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

    public Bitmap getEventImg() {
        return eventImg;
    }

    public void setEventImg(Bitmap eventImg) {
        this.eventImg = eventImg;
    }

    public Integer getPhysicalTouch() {
        return physicalTouch;
    }

    public void setPhysicalTouch(Integer physicalTouch) {
        this.physicalTouch = physicalTouch;
    }

    public Integer getWordsOfAff() {
        return wordsOfAff;
    }

    public void setWordsOfAff(Integer wordsOfAff) {
        this.wordsOfAff = wordsOfAff;
    }

    public Integer getGifts() {
        return gifts;
    }

    public void setGifts(Integer gifts) {
        this.gifts = gifts;
    }

    public Integer getActOfService() {
        return actOfService;
    }

    public void setActOfService(Integer actOfService) {
        this.actOfService = actOfService;
    }

    public Integer getQualityTime() {
        return qualityTime;
    }

    public void setQualityTime(Integer qualityTime) {
        this.qualityTime = qualityTime;
    }
}
