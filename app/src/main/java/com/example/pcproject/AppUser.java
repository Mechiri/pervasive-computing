package com.example.pcproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AppUser {

    //TAG
    private static final String TAG = "AppUser";

    //Attributes
    private String userName;
    private String appIntention;
    private String idealRelationshipFeel;
    private String relationshipDesire;

    //Attributes - Love Languages Labels
    private Integer wordsOfAffirmation;
    private Integer qualityTime;
    private Integer receivingGifts;
    private Integer actsOfService;
    private Integer physicalTouch;

    private Integer totalCountPartnerProfiles;

    //Evaluate
    EvaluateRelationship evaluateRelationship;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;



    public AppUser() {
        userName = null;
        appIntention = null;
        idealRelationshipFeel = null;
        relationshipDesire = null;

        wordsOfAffirmation = 0;
        qualityTime = 0;
        receivingGifts = 0;
        actsOfService = 0;
        physicalTouch = 0;

        totalCountPartnerProfiles = 0;

        //Initialize Database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public Integer getTotalCountPartnerProfiles() {
        return totalCountPartnerProfiles;
    }

    public void setTotalCountPartnerProfiles(Integer totalCountPartnerProfiles) {
        this.totalCountPartnerProfiles = totalCountPartnerProfiles;
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

    public String getRelationshipDesire() {
        return relationshipDesire;
    }

    public void setRelationshipDesire(String relationshipDesire) {
        this.relationshipDesire = relationshipDesire;
    }

    public void uploadAllData(final Context context)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        Map<String, Object> userData = new HashMap<>();

        if(userName != null && !userName.isEmpty())
            userData.put("userName", userName);
        if(appIntention != null && !appIntention.isEmpty())
            userData.put("appIntention", appIntention);
        if(idealRelationshipFeel != null && !idealRelationshipFeel.isEmpty())
            userData.put("idealRelationshipFeel", idealRelationshipFeel);
        if(relationshipDesire != null && !relationshipDesire.isEmpty())
            userData.put("relationshipDesire", relationshipDesire);
        if(wordsOfAffirmation != 0)
            userData.put("wordsOfAffirmation", wordsOfAffirmation);
        if(qualityTime != 0)
            userData.put("qualityTime", qualityTime);
        if(receivingGifts != 0)
            userData.put("receivingGifts",receivingGifts);
        if(actsOfService != 0)
            userData.put("actsOfService", actsOfService);
        if(physicalTouch != 0)
            userData.put("physicalTouch", physicalTouch);
        if(totalCountPartnerProfiles != null)
            userData.put("totalCountPartnerProfiles", totalCountPartnerProfiles);

        userData.put("timestamp", FieldValue.serverTimestamp());

        db.collection(userId).document("myData").update(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Uploaded Data", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Upload all Data");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error in Uploading Data", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Upload Failure");
                    }
                });
    }

    public void calculateLoveLanguagesRatio()
    {
        Map<String, Integer> result;
        evaluateRelationship = new EvaluateRelationship();
        result = evaluateRelationship.calculateLoveLanguagesRatio(wordsOfAffirmation, qualityTime, receivingGifts, actsOfService, physicalTouch);

        wordsOfAffirmation = result.get("wordsOfAffirmation");
        qualityTime = result.get("qualityTime");
        receivingGifts = result.get("receivingGifts");
        actsOfService = result.get("actsOfService");
        physicalTouch = result.get("physicalTouch");
    }

    void retrieveTotalCountPartnerProfiles()
    {
        String userId = mAuth.getCurrentUser().getEmail();
        db.collection(userId).document("myData").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {

                            //totalCountPartnerProfiles = (Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue();
                            setTotalCountPartnerProfiles((Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue());
                            Log.d(TAG, "Total Partner Counts AppUser: "+(Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue());
                        }
                        else
                        {
                            Log.d(TAG, "AppUser: retrieveTotalCountPartnerProfiles Document Not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "AppUser: retrieveTotalCountPartnerProfiles  Failed");
                    }
                });
    }

    void updateTotalCountPartnerProfiles(final Context context)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        Map<String, Object> userData = new HashMap<>();
        userData.put("totalCountPartnerProfiles", totalCountPartnerProfiles);

        db.collection(userId).document("myData").update(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Updated User Count", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Updated User Count");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error in Updated User Count", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Updated User Count");
                    }
                });
    }
}
