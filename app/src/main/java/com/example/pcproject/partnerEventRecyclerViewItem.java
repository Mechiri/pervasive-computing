package com.example.pcproject;

public class partnerEventRecyclerViewItem {
    private String eventTitle;
    private String eventDate;
    private int eventImg;
    private Integer physicalTouch;
    private Integer wordsOfAff;
    private Integer gifts;
    private Integer actOfService;
    private Integer qualityTime;

    public partnerEventRecyclerViewItem(String eventTitle,
                                        String eventDate,
                                        int eventImg,
                                        Integer physicalTouch,
                                        Integer wordsOfAff,
                                        Integer gifts,
                                        Integer actOfService,
                                        Integer qualityTime) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventImg = eventImg;
        this.physicalTouch = physicalTouch;
        this.wordsOfAff = wordsOfAff;
        this.gifts = gifts;
        this.actOfService = actOfService;
        this.qualityTime = qualityTime;
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
