package com.example.pcproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
Event Class Details
*/

public class Event {
    private static final String TAG = "Event";
    //Attribute
    private String eventId;
    private String eventName;
    private String partnerName;
    private String eventDate;
    private String eventType;
    private String parentName;

    private Integer wordsOfAffirmation;
    private Integer qualityTime;
    private Integer receivingGifts;
    private Integer actsOfService;
    private Integer physicalTouch;

    private String newTraitsLearned;
    private ArrayList<String> pictures;

    private String talkAbout;
    private String youReallyLiked;
    private String youDidNotLiked;
    private String notable;

    private DateEvent dateEvent;
    private FightEvent fightEvent;
    private OtherEvent otherEvent;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;



    public Event() {
        this.eventId = null;
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
        this.parentName = null;

        this.dateEvent =  new DateEvent();
        this.fightEvent = new FightEvent();
        this.otherEvent = new OtherEvent();

        //Initialize Database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
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

    public String getNewTraitsLearned() {
        return newTraitsLearned;
    }

    public void setNewTraitsLearned(String newTraitsLearned) {
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

    void fetchDataFromDatabase()
    {

    }

    //upload Event Data To Database
    void uploadDataToDatabase(final Context context,String partnerProfileName, String eventName1)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        Map<String, Object> eventData = new HashMap<>();

        if((eventName != null) && (!eventName.isEmpty()))
        {
            if((eventDate != null) && (!eventDate.isEmpty()))
            {
                eventId = eventName+eventDate.replace('/','-');
                eventData.put("eventId", eventId);
                eventData.put("eventName", eventName);
                eventData.put("eventDate", eventDate);
            }
        }
        if((eventId != null) && (!eventId.isEmpty()) && (partnerName != null) && (!partnerName.isEmpty()))
        {
            eventData.put("partnerName", partnerName);
            if((eventType != null) && (!eventType.isEmpty()))
            {
                eventData.put("eventType", eventType);
            }
            if(wordsOfAffirmation != null)
            {
                eventData.put("wordsOfAffirmation", wordsOfAffirmation);
            }
            if(qualityTime != null)
            {
                eventData.put("qualityTime", qualityTime);
            }
            if(receivingGifts != null)
            {
                eventData.put("receivingGifts", receivingGifts);
            }
            if(actsOfService != null)
            {
                eventData.put("actsOfService", actsOfService);
            }
            if(physicalTouch != null)
            {
                eventData.put("physicalTouch", physicalTouch);
            }
            if((newTraitsLearned != null) && (!newTraitsLearned.isEmpty()))
            {
                eventData.put("newTraitsLearned", newTraitsLearned);
            }
            if((talkAbout != null) && (!talkAbout.isEmpty()))
            {
                eventData.put("talkAbout", talkAbout);
            }
            if((youReallyLiked != null) && (!youReallyLiked.isEmpty()))
            {
                eventData.put("youReallyLiked", youReallyLiked);
            }
            if((youDidNotLiked != null) && (!youDidNotLiked.isEmpty()))
            {
                eventData.put("youDidNotLiked", youDidNotLiked);
            }
            if((notable != null) && (!notable.isEmpty()))
            {
                eventData.put("notable", notable);
            }
            if(dateEvent != null)
            {
                eventData.put("dateEvent", dateEvent);
            }
            if(fightEvent != null)
            {
                eventData.put("fightEvent", fightEvent);
            }
            if(otherEvent != null)
            {
                eventData.put("otherEvent", otherEvent);
            }
            if(parentName != null)
            {
                eventData.put("parentName", parentName);
            }

            eventData.put("timestamp", FieldValue.serverTimestamp());

            db.collection(userId)
                    .document("PartnerProfiles")
                    .collection(partnerProfileName)
                    .document(partnerProfileName+"Data")
                    .collection(eventName1)
                    .document(eventName1+"Data")
                    .set(eventData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "Uploaded Event Data", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Database: Uploaded Event Data");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error in Uploading Data", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Database: Uploaded Event Data Failure");
                        }
                    });
        }
        else {
            Toast.makeText(context, "Partner, EventName, EventDate not be empty", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Partner, EventName, EventDate not be empty");
        }
    }
}
