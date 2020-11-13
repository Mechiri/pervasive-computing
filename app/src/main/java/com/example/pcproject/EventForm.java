package com.example.pcproject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class EventForm extends AppCompatActivity implements
        mainEventFragment.mainEventFragmentListener,
        reflectionEventFragment.reflectionEventFragmentListener,
        dateEventFragment.dateEventFragmentListener,
        fightEventFragment.fightEventFragmentListener,
        otherEventFragment.otherEventFragmentListener
{
    private static final String TAG = "EVENT FORM";
    private mainEventFragment mainEvent;
    private reflectionEventFragment reflectionFragment;
    private dateEventFragment dateFragment;
    private fightEventFragment fightFragment;
    private otherEventFragment otherFragment;
    private eventAddedFragment addedFragment;

    private static final int CAMERA_REQUEST_CODE1 = 11, SELECT_FILE_CODE1 = 12;
    private ImageView mEventPicture;

    private FirebaseAuth mAuth;
    private StorageReference storageReference;

    Event event;

    private String itemSelection;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        event = new Event();
        setContentView(R.layout.activity_event_form);
        mainEvent = new mainEventFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d(TAG, "Fragment count in back stack " + fragmentManager.getBackStackEntryCount());
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.eventMainLayout, mainEvent).commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onInputPictures(ImageView mEventPictures) {
        mEventPicture = mEventPictures;
        SelectImage();
    }

    private void SelectImage()
    {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EventForm.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(intent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivityForResult(intent, CAMERA_REQUEST_CODE1);
                    }
                } else if (items[which].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    if(intent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE_CODE1);
                    }
                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == CAMERA_REQUEST_CODE1) {
                Bundle extras = data.getExtras();
                Log.d(TAG, "onActivityResult ........... camera coming");
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mEventPicture.setImageBitmap(imageBitmap);
                //uploadPicture(imageBitmap, null);
            }else if( requestCode == SELECT_FILE_CODE1 )
            {
                Uri selectedImageUri = data.getData();
                Log.d(TAG, "onActivityResult ........... gallery coming");
                mEventPicture.setImageURI(selectedImageUri);
                //uploadPicture(null, selectedImageUri);
            }
        }

    }

    private void uploadPicture(String partnerName, String eventName, String Date1) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");


        FirebaseUser currentUser = mAuth.getCurrentUser();
        String Date2 = Date1.replace('/','-');
        Log.d(TAG, "uploadPicture ........... Date: "+Date2);
        StorageReference riversRef = storageReference.child(currentUser.getEmail()).child(partnerName).child("IMG_" + eventName + Date1.replace('/','-'));
        if(mEventPicture != null)
        {
            Bitmap imageBitmap = ((BitmapDrawable)mEventPicture.getDrawable()).getBitmap();
            if(imageBitmap != null)
            {
                pd.show();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                byte[] b = stream.toByteArray();
                riversRef.putBytes(b)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                pd.dismiss();
                                Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                Toast.makeText(EventForm.this, "Failed to Upload", Toast.LENGTH_LONG).show();   // ...
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                                pd.setMessage("Percentage: "+ (int) progressPercent + "%");
                            }
                        });
            }
        }
    }

    @Override
    public void onInputMainEventSent(String eventName, String partnerName, String Date1, Integer wordsOfAffirmation,Integer qualityTime,Integer receivingGifts, Integer actsOfService,Integer physicalTouch, String newTraits) {
        Log.d(TAG, "onInputMainEventSent...............coming1");
        if( ((eventName!=null) && !eventName.isEmpty()) && ((partnerName!=null) && !partnerName.isEmpty()) && ((Date1!=null) && !Date1.isEmpty()) )
        {
            event.setEventName(eventName);
            event.setPartnerName(partnerName);
            event.setEventDate(Date1);

            event.setWordsOfAffirmation(wordsOfAffirmation);
            event.setQualityTime(qualityTime);
            event.setActsOfService(actsOfService);
            event.setReceivingGifts(receivingGifts);
            event.setPhysicalTouch(physicalTouch);
            event.setNewTraitsLearned(newTraits);

            uploadPicture(partnerName, eventName, Date1);
            Log.d(TAG, "onInputMainEventSent:"+" EventName: "+ eventName +" partnerName: "+ partnerName+ " Date: "+ Date1);
            Log.d(TAG, "onInputMainEventSent:"+" wordsOfAffirmation: "+ wordsOfAffirmation +" qualityTime: "+ qualityTime+ " actsOfService: "+ actsOfService+" receivingGifts: "+ receivingGifts+ " physicalTouch: "+ physicalTouch);
            Log.d(TAG, "setNewTraitsLearned:"+" newTraits: "+ newTraits);

            if(itemSelection.equals("Date")) {
                dateFragment = new dateEventFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.eventMainLayout, dateFragment)
                        .commit();
            }
            else if(itemSelection.equals("Fight")) {
                fightFragment = new fightEventFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.eventMainLayout, fightFragment)
                        .commit();
            }
            else if(itemSelection.equals("Other")) {
                otherFragment = new otherEventFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.eventMainLayout, otherFragment)
                        .commit();
            }
        }
    }


    @Override
    public void onInputItemSelected(String itemSelected) {
        itemSelection = itemSelected;
    }


    @Override
    public void onInputReflectionEventSent(String talkAbout, String youReallyLiked, String youDidNotLiked, String notable) {
        Log.d(TAG,"onReflectionEventSent coming.....3");

        event.setTalkAbout(talkAbout);
        event.setYouReallyLiked(youReallyLiked);
        event.setYouDidNotLiked(youDidNotLiked);
        event.setNotable(notable);

        Log.d(TAG, "event.setTalkAbout(talkAbout): " + event.getTalkAbout());
        Log.d(TAG, "event.setYouReallyLiked(youReallyLiked): " + event.getYouReallyLiked());
        Log.d(TAG, "event.setYouDidNotLiked(youDidNotLiked): " + event.getYouDidNotLiked());
        Log.d(TAG, "event.setNotable(notable): " + event.getNotable());

        //Upload all event to database
        event.uploadDataToDatabase(this);

        addedFragment = new eventAddedFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, addedFragment)
                .commit();

    }

    @Override
    public void onInputDateEventSent( String whereDidYouGo, String whatDidYouDo, String howLongDate, Integer dateRate, Integer conversationRate, String otherNotes ) {

        event.getDateEvent().setWhereDidYouGo(whereDidYouGo);
        event.getDateEvent().setWhatDidYouDo(whatDidYouDo);
        event.getDateEvent().setHowLongDate(howLongDate);
        event.getDateEvent().setDateRate(dateRate);
        event.getDateEvent().setConversationRate(conversationRate);
        event.getDateEvent().setOtherNotes(otherNotes);

        Log.d(TAG, "onInputDateEventSent coming....2");

        Log.d(TAG, "event.getDateEvent().setWhereDidYouGo(whereDidYouGo): " + event.getDateEvent().getWhereDidYouGo());
        Log.d(TAG, "event.getDateEvent().setWhatDidYouDo(whatDidYouDo): "+ event.getDateEvent().getWhatDidYouDo());
        Log.d(TAG, "event.getDateEvent().setHowLongDate(howLongDate): "+event.getDateEvent().getHowLongDate());
        Log.d(TAG, "event.getDateEvent().setDateRate(dateRate): "+event.getDateEvent().getDateRate());
        Log.d(TAG, "event.getDateEvent().setConversationRate(conversationRate): "+event.getDateEvent().getConversationRate());
        Log.d(TAG, "event.getDateEvent().setOtherNotes(otherNotes): "+event.getDateEvent().getOtherNotes());


        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, reflectionFragment)
                .commit();

    }

    @Override
    public void onInputFightEventSent(String fightAbout, String resolution, Integer madePersonal, Integer fightHurtful, Integer theyHandleFight, Integer youHandledFight, String otherNotes) {

        event.getFightEvent().setFightAbout(fightAbout);
        event.getFightEvent().setResolution(resolution);
        event.getFightEvent().setMadePersonal(madePersonal);
        event.getFightEvent().setFightHurtful(fightHurtful);
        event.getFightEvent().setTheyHandleFight(theyHandleFight);
        event.getFightEvent().setYouHandledFight(youHandledFight);
        event.getFightEvent().setOtherNotes(otherNotes);

        Log.d(TAG,"event.getFightEvent().setFightAbout(fightAbout): "+ event.getFightEvent().getFightAbout());
        Log.d(TAG,"event.getFightEvent().setResolution(resolution): "+ event.getFightEvent().getResolution());
        Log.d(TAG,"event.getFightEvent().setMadePersonal(madePersonal): "+ event.getFightEvent().getMadePersonal());
        Log.d(TAG,"event.getFightEvent().setFightHurtful(fightHurtful): "+ event.getFightEvent().getFightHurtful());
        Log.d(TAG,"event.getFightEvent().setTheyHandleFight(theyHandleFight): "+ event.getFightEvent().getTheyHandleFight());
        Log.d(TAG,"event.getFightEvent().setYouHandledFight(youHandledFight): "+ event.getFightEvent().getYouHandledFight());
        Log.d(TAG,"event.getFightEvent().setOtherNotes(otherNotes): "+ event.getFightEvent().getOtherNotes());


        Log.d(TAG, "onInputDateEventSent coming....2");


        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, reflectionFragment)
                .commit();
    }

    @Override
    public void onInputOtherEventSent(String eventStatus, String title, String describeEvent, Integer rateOverallExperience, String otherNotes) {
        Log.d(TAG, "onInputDateEventSent coming....2");

        event.getOtherEvent().setEventStatus(eventStatus);
        event.getOtherEvent().setTitle(title);
        event.getOtherEvent().setDescribeEvent(describeEvent);
        event.getOtherEvent().setRateOverallExperience(rateOverallExperience);
        event.getOtherEvent().setOtherNotes(otherNotes);

        Log.d(TAG, "event.getOtherEvent().setEventStatus(eventStatus): "+ event.getOtherEvent().getEventStatus());
        Log.d(TAG, "event.getOtherEvent().setTitle(title): "+ event.getOtherEvent().getTitle());
        Log.d(TAG, "event.getOtherEvent().setDescribeEvent(describeEvent): "+ event.getOtherEvent().getDescribeEvent());
        Log.d(TAG, "event.getOtherEvent().setRateOverallExperience(rateOverallExperience): "+ event.getOtherEvent().getRateOverallExperience());
        Log.d(TAG, "event.getOtherEvent().setOtherNotes(otherNotes): "+ event.getOtherEvent().getOtherNotes());


        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, reflectionFragment)
                .commit();


    }
}