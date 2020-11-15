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

import java.util.HashMap;
import java.util.Map;

public class Partner {

    private static final String TAG = "Partner";

    //Attributes
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
            db.collection(userId).document(profileId)
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
}
