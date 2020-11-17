package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

public class LandingPage extends AppCompatActivity {


    private static final String TAG = "LandingPage";
    private RecyclerView rView;

    private Button userProfileB;
    private Button addPartnerB;

    private Integer totalNoOfProfileCount;
    private Map<String, Partner> partners;
    private Map<String, Bitmap> partnersPictures;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing_page);
        userProfileB = findViewById(R.id.userProfileB);
        addPartnerB = findViewById(R.id.addPartnerB);
        partners = new HashMap<String, Partner>();
        partnersPictures = new HashMap<String, Bitmap>();

        //Initialize Database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        getPartnerProfileCount();

        userProfileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here
            }
        });

        addPartnerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "addPartnerFormB Clicked...............1");
                startActivity(new Intent(LandingPage.this, addPartnerForm.class));
            }
        });

        rView = findViewById(R.id.recyclerViewLanding);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        rView.setLayoutManager(layout);

        /*
        List<partnerRecyclerViewItem> partnersold = new ArrayList<>();

        partnersold.add(new partnerRecyclerViewItem(R.drawable.loading, "Testing", "2", "ongoing"));
        partnersold.add(new partnerRecyclerViewItem(R.drawable.loading, "Testing2", "5", "ongoing"));

        partnerRecyclerViewAdapter a = new partnerRecyclerViewAdapter(partnersold);
        rView.setAdapter(a);
        a.notifyDataSetChanged();

         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getPartnerProfileCount();
    }

    void getPartnerProfileCount()
    {
        String userId = mAuth.getCurrentUser().getEmail();
        db.collection(userId).document("myData").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            totalNoOfProfileCount = (Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue();
                            //setTotalCountPartnerProfiles((Integer) ((Long)documentSnapshot.get("totalCountPartnerProfiles")).intValue());
                            Log.d(TAG, "Total Partner Counts AppUser....123: "+ totalNoOfProfileCount);
                            if(totalNoOfProfileCount > 0)
                            {
                                getPartnerProfileData(totalNoOfProfileCount);
                                getPartnerProfilePic(totalNoOfProfileCount);
                            }
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

    /*
    void getRemainingProfilePic()
    {
        List<partnerRecyclerViewItem> partnersOld = new ArrayList<>();
        Bitmap bitmap;

        Log.d(TAG,"getRemainingProfilePic...........Coming");

        for (Map.Entry mapElement : partners.entrySet())
        {
            Log.d(TAG,"getRemainingProfilePic...........Coming...1");
            String partnerName = (String)mapElement.getKey();
            Partner partnerDetails = (Partner)mapElement.getValue();
            bitmap = partnersPictures.get(partnerName);
            if(bitmap == null)
            {
                Log.d(TAG,partnerName+" picture is null...........Coming");
            }
        }

        //Call RecyclerView Display
        //displayProfiles();
    }
     */

    void getPartnerProfileData(Integer totalNoOfProfileCount)
    {
        Log.d(TAG, "getPartnerProfileData: "+ totalNoOfProfileCount);
        String userId = mAuth.getCurrentUser().getEmail();

        String partnerName = "Partner";

        for (int i = 1; i <= totalNoOfProfileCount; i++)
        {
            partnerName += i;
            final String partnerName1 = partnerName;
            db.collection(userId).document("PartnerProfiles")
                    .collection(partnerName)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots)
                            {
                                Partner partner = documentSnapshot.toObject(Partner.class);
                                partners.put(partnerName1, partner);
                                Log.d(TAG,"First name: "+partnerName1+":"+partner.getFirstName());
                                Log.d(TAG,"Last name: "+partnerName1+":"+partner.getLastName());
                                Log.d(TAG,"Date of Birth: "+partnerName1+":"+partner.getBirthDate());
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"Cannot able to fetch All Partner's Data");
                        }
                    });

            partnerName = partnerName.substring(0, partnerName.length() - 1);
        }
    }

    void getPartnerProfilePic(Integer totalNoOfProfileCount)
    {
        String userId = mAuth.getCurrentUser().getEmail();
        String partnerName = "Partner";
        StorageReference storageReference1;
        for (int i = 1; i <= totalNoOfProfileCount; i++)
        {
            partnerName += i;
            storageReference1 = storageReference.child(userId).child(partnerName).child("IMG_Profile");
            try {
                final File localFile = File.createTempFile(partnerName,".jpg" );
                final String partnerName1 = partnerName;
                storageReference1.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Log.d(TAG,"Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                                Log.d(TAG,"Picture Retrieved: "+partnerName1+"-"+"IMG_Profile");
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                partnersPictures.put(partnerName1,bitmap);

                                //Call RecyclerView Display
                                displayProfiles();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                StorageReference empty;
                                empty = storageReference.child("Empty.jpg");
                                empty.getFile(localFile)
                                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                Log.d(TAG,"Empty Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                                                Log.d(TAG,"Empty Picture Retrieved: "+partnerName1+"-"+"IMG_Profile");
                                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                                partnersPictures.put(partnerName1,bitmap);

                                                //Call RecyclerView Display
                                                displayProfiles();
                                            }
                                        });

                                Log.d(TAG,"Picture Not Retrieved: "+partnerName1+"-"+"IMG_Profile");
                            }
                        });
            }catch (IOException e)
            {
                e.printStackTrace();
            }


            partnerName = partnerName.substring(0, partnerName.length() - 1);
        }
    }

    void displayProfiles()
    {
        List<partnerRecyclerViewItem> partnersOld = new ArrayList<>();
        Bitmap bitmap;

        for (Map.Entry mapElement : partners.entrySet())
        {
            String partnerName = (String)mapElement.getKey();
            Partner partnerDetails = (Partner)mapElement.getValue();
            bitmap = partnersPictures.get(partnerName);
            if(bitmap != null)
            {
                partnersOld.add(new partnerRecyclerViewItem(bitmap, partnerDetails.getFirstName()+" "+partnerDetails.getLastName(), partnerDetails.getTotalEvents().toString(), "ongoing"));
            }
            else
            {
                //partnersOld.add(new partnerRecyclerViewItem(bitmap, partnerDetails.getFirstName()+" "+partnerDetails.getLastName(), partnerDetails.getTotalEvents().toString(), "ongoing"));
            }
        }

        partnerRecyclerViewAdapter a = new partnerRecyclerViewAdapter(partnersOld);
        rView.setAdapter(a);
        a.notifyDataSetChanged();
    }
}