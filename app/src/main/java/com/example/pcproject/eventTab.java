package com.example.pcproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class eventTab extends Fragment {

    private static final String TAG = "eventTab";

    private Context context;
    private RecyclerView rView;
    List<partnerEventRecyclerViewItem> partnerEvents;
    private Button addPartnerEventB;
    private String partnerProfileName;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;
    private partnerEventRecyclerViewAdapter partnerEventRecyclerViewAdapter1;

    public Map<String, Event> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<String, Event> eventMap) {
        this.eventMap = eventMap;
    }

    public Map<String, Bitmap> getEventsPictures() {
        return eventsPictures;
    }

    public void setEventsPictures(Map<String, Bitmap> eventsPictures) {
        this.eventsPictures = eventsPictures;
    }

    private Partner partner;
    Map<String, Event> eventMap;
    private Map<String, Bitmap> eventsPictures;




    public eventTab(Context context, String partnerProfileName, Map<String, Event> eventMap, Map<String, Bitmap> eventsPictures) {
        // Required empty public constructor
        this.context = context;
        this.partnerProfileName = partnerProfileName;
        this.eventMap =eventMap;
        this.eventsPictures = eventsPictures;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView.........coming");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_tab, container, false);
        rView = v.findViewById(R.id.partnerEventRecyclerView);



        partner = new Partner();

        addPartnerEventB = v.findViewById(R.id.addPartnerEventB);

        addPartnerEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventForm.class);
                intent.putExtra("ProfileName", partnerProfileName);
                Log.d(TAG, "Partner Profile Name: "+partnerProfileName+" coming.........1");
                startActivity(intent);
            }
        });

        partnerEventRecyclerViewAdapter1= new partnerEventRecyclerViewAdapter(partnerEvents);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(layout);
        rView.setAdapter(partnerEventRecyclerViewAdapter1);
        partnerEventRecyclerViewAdapter1.notifyDataSetChanged();
        //a.notifyDataSetChanged();
        /*rView.setAdapter(a);
        a.notifyDataSetChanged();*/

        /*((RecyclerView) v.findViewById(R.id.partnerEventRecyclerView)).setAdapter(a);
        a.notifyDataSetChanged();*/

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate.........coming");
        //Initialize Database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        partnerEvents = new ArrayList<>();
        //partnerEvents = new ArrayList<>();
        Bitmap bitmap;



        Log.d(TAG, "onCreate Coming: eventMapSize: "+eventMap.size()+"..................Coming");
        //partner = new Partner();
        //retrieveTotalEventCounts(partnerProfileName);
        retrieveTotalEventCounts(partnerProfileName);




/*
        partnerEvents.add(new partnerEventRecyclerViewItem(
                "Test Event 1",
                "11/14/2020",
                R.drawable.loading,
                80,
                20,
                50,
                30,
                90));
        partnerEvents.add(new partnerEventRecyclerViewItem(
                "Test Event 2",
                "11/16/2020",
                R.drawable.loading,
                20,
                50,
                5,
                70,
                85));

 */


    }

    void retrieveTotalEventCounts(final String partnerProfileName)
    {
        Log.d(TAG, "Partner: retrieveTotalEventCounts.............Coming!");
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
                            Integer totalEvents = (Integer) ((Long)documentSnapshot.get("totalEvents")).intValue();
                            partner.setTotalEvents((Integer) ((Long)documentSnapshot.get("totalEvents")).intValue());
                            getPartnerEventsData(partnerProfileName, totalEvents);
                            Log.d(TAG, "Checking retrieveTotalEventCounts: "+partner.getTotalEvents()+"...................Coming!!!");
                            Log.d(TAG, "onCreate Coming: eventMapSize: "+eventMap.size()+"..................Coming!!!HURRAY");
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

    void getPartnerEventsData(final String partnerProfileName, final Integer totalEvents)
    {
        Log.d(TAG, "Total no of events: "+ totalEvents);
        String userId = mAuth.getCurrentUser().getEmail();
        String partnerData = partnerProfileName+"Data";

        String partnerEventName = "Event";

        for (int i = 1; i <= totalEvents; i++)
        {
            partnerEventName += i;
            final String partnerEventName1 = partnerEventName;
            db.collection(userId)
                    .document("PartnerProfiles")
                    .collection(partnerProfileName)
                    .document(partnerData)
                    .collection(partnerEventName)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots)
                            {
                                Event event = documentSnapshot.toObject(Event.class);
                                eventMap.put(partnerEventName1,event);
                                Log.d(TAG,"Event Tirle: "+partnerEventName1+":"+event.getEventName());
                                Log.d(TAG,"Event Date: "+partnerEventName1+":"+event.getEventDate());
                                Log.d(TAG,"Physical Touch: "+partnerEventName1+":"+event.getPhysicalTouch());
                            }
                            if(eventMap.size() == totalEvents)
                            {
                                Log.d(TAG, "onCreate Coming: eventMapSize: "+eventMap.size()+"..................Coming!!!HURRAY........3");
                                getEventPic(partnerProfileName, totalEvents);

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"Cannot able to fetch All Events's Data");
                        }
                    });

            partnerEventName = partnerEventName.substring(0, partnerEventName.length() - 1);
        }
    }

    void getEventPic(String partnerProfileName, Integer totalNoOfEventCount)
    {
        Log.d(TAG, "onCreate Coming: getEventPic: "+eventMap.size()+"..................Coming!!!HURRAY........4");
        String userId = mAuth.getCurrentUser().getEmail();
        String eventName = "Event";
        StorageReference storageReference1;
        for (int i = 1; i <= totalNoOfEventCount; i++)
        {
            eventName += i;
            storageReference1 = storageReference.child(userId).child(partnerProfileName).child(eventName).child("IMG_"+eventName);
            try {
                final File localFile = File.createTempFile(eventName,".jpg" );
                final String eventName1 = eventName;
                storageReference1.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Log.d(TAG,"Event Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                                Log.d(TAG,"Event Picture Retrieved: "+eventName1+"-"+"IMG_Profile");
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                eventsPictures.put(eventName1,bitmap);
                                displayEvents(eventName1);
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
                                                eventsPictures.put(eventName1,bitmap);

                                                //Call RecyclerView Display
                                                displayEvents(eventName1);
                                            }
                                        });

                                Log.d(TAG,"Picture Not Retrieved: "+eventName1+"-"+"IMG_Profile");
                            }
                        });
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            eventName = eventName.substring(0, eventName.length() - 1);
        }
    }

    void displayEvents(String Key)
    {
        Log.d(TAG, "onCreate Coming: displayEvents: "+eventMap.size()+"..................Coming!!!HURRAY........5");
        Log.d(TAG, "onCreate Coming: displayEvents: "+eventsPictures.size()+"..................Coming!!!HURRAY........5");
        Bitmap bitmap;

        Event eventDetails = eventMap.get(Key);
        bitmap = eventsPictures.get(Key);
        if(bitmap != null)
        {
            Log.d(TAG, "Coming!!!HURRAY........7");
            partnerEvents.add(new partnerEventRecyclerViewItem(
                    eventDetails.getEventName(),
                    eventDetails.getEventDate(),
                    bitmap,
                    eventDetails.getPhysicalTouch(),
                    eventDetails.getWordsOfAffirmation(),
                    eventDetails.getReceivingGifts(),
                    eventDetails.getActsOfService(),
                    eventDetails.getQualityTime()));
        }

        //partnerEventRecyclerViewAdapter a = new partnerEventRecyclerViewAdapter(partnerEvents);
        //LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        //layout.setOrientation(LinearLayoutManager.VERTICAL);
        //rView.setLayoutManager(layout);
        rView.setAdapter(partnerEventRecyclerViewAdapter1);
        //partnerEventRecyclerViewAdapter1.notifyDataSetChanged();
    }
}