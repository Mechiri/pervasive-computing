package com.example.pcproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class partnerProfileTab extends Fragment {

    private static final String TAG = "partnerProfileTab";

    private String partnerProfileName;
    Context context;
    private Partner partner;
    private Bitmap bitmap;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;

    private ImageView partnerProfileImage;
    private TextView partnerName;
    private TextView partnerBirthday;
    private TextView partnerOccupation;

    private TextView partnerIndicator;
    private Integer numIndicator; //testing the average experience color changing

    private ProgressBar PT;
    private ProgressBar WOA;
    private ProgressBar G;
    private ProgressBar AOS;
    private ProgressBar QT;
    private Bitmap profileImg; //This should be for partner profile image

    public partnerProfileTab(String partnerProfileName, Context context) {
        // Required empty public constructor
        this.partnerProfileName = partnerProfileName;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_partner_profile_tab, container, false);

        partnerName = v.findViewById(R.id.partnerNameTab);
        partnerBirthday = v.findViewById(R.id.partnerBirthday);
        partnerOccupation = v.findViewById(R.id.partnerOccupation);
        partnerIndicator = v.findViewById(R.id.partnerIndicator);
        partnerProfileImage = v.findViewById(R.id.partnerTabPic);

        PT = v.findViewById(R.id.PTprogressBar);
        WOA = v.findViewById(R.id.WOAprogressBar);
        G = v.findViewById(R.id.GprogressBar);
        AOS = v.findViewById(R.id.AOSprogressBar);
        QT = v.findViewById(R.id.QTprogressBar);

        Log.d(TAG, "onCreateView .......................Coming");

        getPartnerProfilePic(partnerProfileName);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        partner = new Partner();

        //Initialize Database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        String userId = mAuth.getCurrentUser().getEmail();
        db.collection(userId)
                .document("PartnerProfiles")
                .collection(partnerProfileName)
                .document(partnerProfileName+"Data")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        partner = documentSnapshot.toObject(Partner.class);

                        Log.d(TAG, "Successfully retrieved Partner Profile Data");
                        Log.d(TAG, "Partner Name: "+ partner.getFirstName()+partner.getLastName());
                        Log.d(TAG, "Partner DOB: "+ partner.getBirthDate());
                        Log.d(TAG, "Partner WoA: "+ partner.getWordsOfAffirmation());
                        Log.d(TAG, "Partner Total Events: "+ partner.getTotalEvents());
                        Log.d(TAG, "Partner PT: "+ partner.getPhysicalTouch());
                        Log.d(TAG, "Partner RG: "+ partner.getReceivingGifts());

                        getPartnerProfilePic(partnerProfileName);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Failure to retrieved Partner Profile Data");
                    }
                });
    }

    void getPartnerProfilePic(final String partnerProfileName)
    {
        Log.d(TAG, "getPartnerProfilePic.................coming..............1");
        String userId = mAuth.getCurrentUser().getEmail();
        StorageReference storageReference1;

        storageReference1 = storageReference.child(userId).child(partnerProfileName).child("IMG_"+"Profile");
        try {
            final File localFile = File.createTempFile(partnerProfileName+"Image",".jpg" );
            storageReference1.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.d(TAG, "getPartnerProfilePic.................coming..............2");
                            Log.d(TAG,"Partner Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                            Log.d(TAG,"Partner Picture Retrieved:");
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            displayPartnerProfileTab(bitmap);
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
                                            Log.d(TAG, "getPartnerProfilePic.................coming..............3");
                                            Log.d(TAG,"Partner empty Picture Retrieved and saved in: "+localFile.getAbsolutePath());
                                            Log.d(TAG,"Empty Picture Retrieved: "+partnerProfileName+"-"+"IMG_Profile");
                                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            displayPartnerProfileTab(bitmap);
                                        }
                                    });

                            Log.d(TAG,"Picture Not Retrieved: "+partnerProfileName+"-"+"IMG_Profile");
                        }
                    });
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void displayPartnerProfileTab(Bitmap bitmap)
    {
        partnerProfileImage.setImageBitmap(bitmap);
        partnerName.setText(partner.getFirstName()+partner.getLastName());
        partnerBirthday.setText(partner.getBirthDate());
        partnerOccupation.setText(partner.getOccupation());

        EvaluateRelationship evaluateRelationship = new EvaluateRelationship();
        numIndicator = evaluateRelationship.getOverallExperience(partner.getOverallDateRate(), partner.getOtherOverallExperience(), partner.getTotalEvents(), partner.getTotalFights());
        //testing
        //numIndicator = 20;
        if(numIndicator <= 33)
        {
            partnerIndicator.setBackgroundResource(R.drawable.partner_bad_indicator);
        }
        else if(numIndicator >= 34 && numIndicator <= 66)
        {
            partnerIndicator.setBackgroundResource(R.drawable.partner_ok_indicator);
        }
        else if(numIndicator >= 67 && numIndicator <= 100)
        {
            partnerIndicator.setBackgroundResource(R.drawable.partner_good_indicator);
        }

        Integer getWordsOfAffirmation = evaluateRelationship.getLoveLanguagesRatio(partner.getWordsOfAffirmation(), partner.getTotalEvents());
        Integer getQualityTime = evaluateRelationship.getLoveLanguagesRatio(partner.getQualityTime(), partner.getTotalEvents());
        Integer getReceivingGifts = evaluateRelationship.getLoveLanguagesRatio(partner.getReceivingGifts(), partner.getTotalEvents());
        Integer getActsOfService = evaluateRelationship.getLoveLanguagesRatio(partner.getActsOfService(), partner.getTotalEvents());
        Integer getPhysicalTouch = evaluateRelationship.getLoveLanguagesRatio(partner.getPhysicalTouch(), partner.getTotalEvents());

        //how to set the progressBars
        PT.setProgress(getPhysicalTouch);
        WOA.setProgress(getWordsOfAffirmation);
        G.setProgress(getReceivingGifts);
        AOS.setProgress(getActsOfService);
        QT.setProgress(getQualityTime);

    }
}