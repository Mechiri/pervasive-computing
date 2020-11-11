package com.example.pcproject;

public class DateEvent {
    //Attributes
    private String whereDidYouGo;
    private String whatDidYouDo;
    private String howLongDate;
    private Integer dateRate;
    private Integer conversationRate;
    private String otherNotes;

    public DateEvent() {
        this.whereDidYouGo = null;
        this.whatDidYouDo = null;
        this.howLongDate = null;
        this.dateRate = null;
        this.conversationRate = null;
        this.otherNotes = null;
    }

    public String getWhereDidYouGo() {
        return whereDidYouGo;
    }

    public void setWhereDidYouGo(String whereDidYouGo) {
        this.whereDidYouGo = whereDidYouGo;
    }

    public String getWhatDidYouDo() {
        return whatDidYouDo;
    }

    public void setWhatDidYouDo(String whatDidYouDo) {
        this.whatDidYouDo = whatDidYouDo;
    }

    public String getHowLongDate() {
        return howLongDate;
    }

    public void setHowLongDate(String howLongDate) {
        this.howLongDate = howLongDate;
    }

    public Integer getDateRate() {
        return dateRate;
    }

    public void setDateRate(Integer dateRate) {
        this.dateRate = dateRate;
    }

    public Integer getConversationRate() {
        return conversationRate;
    }

    public void setConversationRate(Integer conversationRate) {
        this.conversationRate = conversationRate;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }
}
