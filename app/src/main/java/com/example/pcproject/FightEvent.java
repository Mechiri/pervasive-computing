package com.example.pcproject;

import java.util.ArrayList;

/*

Fight Event Data Class

*/

public class FightEvent {
    //Attributes
    private String fightAbout;
    private String resolution;
    private Integer madePersonal;
    private Integer fightHurtful;
    private Integer theyHandleFight;
    private Integer youHandledFight;
    private String otherNotes;

    public FightEvent() {
        this.fightAbout = null;
        this.resolution = null;
        this.madePersonal = null;
        this.fightHurtful = null;
        this.theyHandleFight = null;
        this.youHandledFight = null;
        this.otherNotes = null;
    }

    public String getFightAbout() {
        return fightAbout;
    }

    public void setFightAbout(String fightAbout) {
        this.fightAbout = fightAbout;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getMadePersonal() {
        return madePersonal;
    }

    public void setMadePersonal(Integer madePersonal) {
        this.madePersonal = madePersonal;
    }

    public Integer getFightHurtful() {
        return fightHurtful;
    }

    public void setFightHurtful(Integer fightHurtful) {
        this.fightHurtful = fightHurtful;
    }

    public Integer getTheyHandleFight() {
        return theyHandleFight;
    }

    public void setTheyHandleFight(Integer theyHandleFight) {
        this.theyHandleFight = theyHandleFight;
    }

    public Integer getYouHandledFight() {
        return youHandledFight;
    }

    public void setYouHandledFight(Integer youHandledFight) {
        this.youHandledFight = youHandledFight;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }
}
