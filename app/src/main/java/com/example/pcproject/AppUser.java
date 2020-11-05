package com.example.pcproject;

public class AppUser {

    //TAG
    private static final String TAG = "AppUser";

    //Attributes
    private String userName;
    private String appIntention;
    private String idealRelationshipFeel;
    private String relationshipDesire;

    public String getRelationshipDesire() {
        return relationshipDesire;
    }

    public void setRelationshipDesire(String relationshipDesire) {
        this.relationshipDesire = relationshipDesire;
    }

    public AppUser() {
        userName = null;
        appIntention = null;
        idealRelationshipFeel = null;
        relationshipDesire = null;
    }

    public String getIdealRelationshipFeel() {
        return idealRelationshipFeel;
    }

    public void setIdealRelationshipFeel(String idealRelationshipFeel) {
        this.idealRelationshipFeel = idealRelationshipFeel;
    }

    public String getUserName() {
        return userName;
    }

    public String getAppIntention() {
        return appIntention;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAppIntention(String appIntention) {
        this.appIntention = appIntention;
    }
}
