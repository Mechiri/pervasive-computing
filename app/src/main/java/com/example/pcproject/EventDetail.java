package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

/*
EventDetail Activity is to show event details in events display page
*/

public class EventDetail extends AppCompatActivity {

    private static final String TAG = "EventDetail";
    private SeekBar PT;
    private SeekBar WOA;
    private SeekBar G;
    private SeekBar AOS;
    private SeekBar QT;

    private TextView traitsLearned;
    private ImageView eventImg;
    private TextView talkAbout;
    private TextView youReallyLiked;
    private TextView youDidNotLiked;
    private TextView eventTitleDetail;
    private TextView eventDateDetail;
    private TextView eventTypeDetail;
    private String partnerProfileName;
    private String eventName;
    private Event eventDetail;

    private Button backB;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        //Initialize Database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        PT = findViewById(R.id.PTSeekBarDetail);
        WOA = findViewById(R.id.WOASeekBarDetail);
        G = findViewById(R.id.GSeekBarDetail);
        AOS = findViewById(R.id.AOSSeekBarDetail);
        QT = findViewById(R.id.QTSeekBarDetail);

        traitsLearned = findViewById(R.id.eventTraitsDetail);
        eventImg = findViewById(R.id.eventImgDetail);
        talkAbout = findViewById(R.id.talkAboutDetails);
        youReallyLiked = findViewById(R.id.youReallyLikedDetails);
        youDidNotLiked = findViewById(R.id.youDidNotLikedDetails);
        backB = findViewById(R.id.backDetailsB);
        eventTitleDetail = findViewById(R.id.eventTitleDetail);
        eventDateDetail = findViewById(R.id.eventDateDetail);
        eventTypeDetail = findViewById(R.id.eventTypeDetail);

        Intent intent = getIntent();
        partnerProfileName = intent.getStringExtra("partnerProfileName");
        eventName = intent.getStringExtra("eventName");
        String eventType = intent.getStringExtra("eventType");
        eventTypeDetail.setText(eventType);
        getEventDetails(partnerProfileName, eventName);

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Should take user back to all events page
                Log.d(TAG, "partnerProfileName at eventdetail: "+partnerProfileName);
                Intent intent = new Intent(EventDetail.this, partnerPage.class);
                intent.putExtra("ProfileName", partnerProfileName);
                startActivity(intent);
            }
        });
    }

    //Fetch Event Details from Database
    protected void getEventDetails(final String partnerProfileName, final String eventName)
    {
        String userId = mAuth.getCurrentUser().getEmail();

        String partnerProfileDoc = partnerProfileName+"Data";
        final String eventDoc = eventName+"Data";

        db.collection(userId)
                .document("PartnerProfiles")
                .collection(partnerProfileName)
                .document(partnerProfileDoc)
                .collection(eventName)
                .document(eventDoc)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Event event = documentSnapshot.toObject(Event.class);
                        
                        eventTitleDetail.setText(event.getEventName());
                        eventDateDetail.setText(event.getEventDate());
                        PT.setProgress(event.getPhysicalTouch());
                        WOA.setProgress(event.getWordsOfAffirmation());
                        G.setProgress(event.getReceivingGifts());
                        AOS.setProgress(event.getActsOfService());
                        QT.setProgress(event.getQualityTime());
                        traitsLearned.setText(event.getNewTraitsLearned());
                        talkAbout.setText(event.getTalkAbout());
                        youReallyLiked.setText(event.getYouReallyLiked());
                        youDidNotLiked.setText(event.getYouDidNotLiked());

                        Log.d(TAG, "Fetching Data");

                        getEventPic(partnerProfileName, eventName);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Unable to Fetch Data");
                    }
                });
    }

    //Fetch Event Picture from Database
    protected void getEventPic(String partnerProfileName, String eventName)
    {
        Log.d(TAG, "partnerProfileName: "+partnerProfileName);
        Log.d(TAG, "eventName: "+eventName);

        String userId = mAuth.getCurrentUser().getEmail();
        StorageReference storageReference1 = storageReference.child(userId).child(partnerProfileName).child(eventName).child("IMG_"+eventName);

        try {
            final File localFile = File.createTempFile(eventName+"pic",".jpg" );
            final String eventName1 = eventName;
            storageReference1.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.d(TAG,"Event Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                            Log.d(TAG,"Event Picture Retrieved: "+eventName1+"-"+"IMG_Profile");
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            eventImg.setImageBitmap(bitmap);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            StorageReference empty;
                            empty = storageReference.child("EmptyEvent.jpg");
                            empty.getFile(localFile)
                                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Log.d(TAG,"Empty Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                                            Log.d(TAG,"Empty Picture Retrieved: "+eventName1+"-"+"IMG_Profile");
                                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            eventImg.setImageBitmap(bitmap);
                                        }
                                    });
                            Log.d(TAG,"Picture Not Retrieved: "+eventName1+"-"+"IMG_Profile");
                        }
                    });
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}