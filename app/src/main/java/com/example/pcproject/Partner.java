package com.example.pcproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Partner {

    private static final String TAG = "Partner";

    //Attributes
    private String parentName;
    private String firstName;

    private String lastName;
    private String occupation;
    private String birthDate;
    private String traits;
    private Integer totalEvents;

    //Attributes - Love Languages Labels
    private Integer wordsOfAffirmation;
    private Integer qualityTime;
    private Integer receivingGifts;
    private Integer actsOfService;
    private Integer physicalTouch;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    public Partner() {
        this.parentName = null;
        this.firstName = null;
        this.lastName = null;
        this.occupation = null;
        this.birthDate = null;
        this.traits = null;
        this.totalEvents = 0;

        this.wordsOfAffirmation = 0;
        this.qualityTime = 0;
        this.receivingGifts = 0;
        this.actsOfService = 0;
        this.physicalTouch = 0;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public Integer getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(Integer totalEvents) {
        this.totalEvents = totalEvents;
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

    void uploadAllDataToDatabase(final Context context)
    {
        if( (firstName != null && !firstName.isEmpty()) && (lastName != null && !lastName.isEmpty()) && (birthDate != null && !birthDate.isEmpty()) )
        {
            String userId = mAuth.getCurrentUser().getEmail();
            Map<String, Object> partnerProfileData = new HashMap<>();

            partnerProfileData.put("firstName",firstName);
            partnerProfileData.put("lastName", lastName);
            partnerProfileData.put("birthDate", birthDate);
            partnerProfileData.put("parentName", parentName);

            if(occupation != null && !occupation.isEmpty())
            {
                partnerProfileData.put("occupation",occupation);
            }
            if(traits != null && !traits.isEmpty())
            {
                partnerProfileData.put("traits",traits);
            }
            if(totalEvents != null)
            {
                partnerProfileData.put("totalEvents",totalEvents);
            }
            if(wordsOfAffirmation != null)
            {
                partnerProfileData.put("wordsOfAffirmation",wordsOfAffirmation);
            }
            if(qualityTime != null)
            {
                partnerProfileData.put("qualityTime",qualityTime);
            }
            if(receivingGifts != null)
            {
                partnerProfileData.put("receivingGifts",receivingGifts);
            }
            if(actsOfService != null)
            {
                partnerProfileData.put("actsOfService",actsOfService);
            }
            if(physicalTouch != null)
            {
                partnerProfileData.put("physicalTouch",physicalTouch);
            }
            partnerProfileData.put("timestamp", FieldValue.serverTimestamp());

            String profileId = firstName+lastName+birthDate.replace('/','-');
            db.collection(userId)
                    .document("PartnerProfiles")
                    .collection(parentName)
                    .document(parentName+"Data")
                    .set(partnerProfileData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "Uploaded Partner Data", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Database: Uploaded Partner Data");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error in Uploading Partner Data", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Database: Uploaded Event Partner Failure");
                        }
                    });
        }
        else
        {
            Toast.makeText(context, "Partner First, Last Name and BirthDate not be empty", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Partner First, Last Name and BirthDate not be empty");
        }
    }

    void retrieveTotalEventCounts(String partnerProfileName)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        db.collection(userId)
                .document("PartnerProfiles")
                .collection(partnerProfileName)
                .document(partnerProfileName+"Data")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {

                            //totalCountPartnerProfiles = (Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue();
                            setTotalEvents((Integer) ((Long)documentSnapshot.get("totalEvents")).intValue());
                            Log.d(TAG, "Total Partner's Event Counts: "+(Integer) ((Long)documentSnapshot.get("totalEvents")).intValue());
                        }
                        else
                        {
                            Log.d(TAG, "Partner: retrieveTotalEventCounts Document Not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Partner: retrieveTotalEventCounts Document Failed");
                    }
                });
    }

    void updateTotalEventCounts(final Context context, String partnerProfileName)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        Map<String, Object> partnerProfileData = new HashMap<>();
        partnerProfileData.put("totalEvents",totalEvents);

        db.collection(userId)
                .document("PartnerProfiles")
                .collection(partnerProfileName)
                .document(partnerProfileName+"Data")
                .update(partnerProfileData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Uploaded TotalEventCounts", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Uploaded TotalEventCounts");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Uploaded TotalEventCounts Failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Uploaded TotalEventCounts Failed");
                    }
                });
    }

    void retrieveLoveLanguages(String partnerProfileName)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        db.collection(userId)
                .document("PartnerProfiles")
                .collection(partnerProfileName)
                .document(partnerProfileName+"Data")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            //totalCountPartnerProfiles = (Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue();
                            setWordsOfAffirmation((Integer) ((Long)documentSnapshot.get("wordsOfAffirmation")).intValue());
                            setQualityTime((Integer) ((Long)documentSnapshot.get("qualityTime")).intValue());
                            setReceivingGifts((Integer) ((Long)documentSnapshot.get("receivingGifts")).intValue());
                            setActsOfService((Integer) ((Long)documentSnapshot.get("actsOfService")).intValue());
                            setPhysicalTouch((Integer) ((Long)documentSnapshot.get("physicalTouch")).intValue());

                            Log.d(TAG, "Fetched Love Languages");
                        }
                        else
                        {
                            Log.d(TAG, "Partner: Love Languages in Document Not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Failed fetching Love Languages");
                    }
                });
    }

    void updateLoveLanguages(final Context context, String partnerProfileName)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        Map<String, Object> partnerProfileData = new HashMap<>();
        partnerProfileData.put("wordsOfAffirmation",wordsOfAffirmation);
        partnerProfileData.put("qualityTime",qualityTime);
        partnerProfileData.put("receivingGifts",receivingGifts);
        partnerProfileData.put("actsOfService",actsOfService);
        partnerProfileData.put("physicalTouch",physicalTouch);

        db.collection(userId)
                .document("PartnerProfiles")
                .collection(partnerProfileName)
                .document(partnerProfileName+"Data")
                .update(partnerProfileData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Uploaded LoveLanguages", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Uploaded LoveLanguages");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Uploaded LoveLanguages Failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Uploaded LoveLanguages Failed");
                    }
                });
    }

}
