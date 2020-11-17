package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.awt.font.TextAttribute;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class addPartnerForm extends AppCompatActivity {
    private static final String TAG = "addPartnerForm";
    private TextView addPartnerBirthday;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private EditText addPartnerFirstName;
    private EditText addPartnerLastName;
    private EditText addPartnerOccupation;
    private Button addPartnerTraits;
    private Button addPartnerPicture;
    private Button addPartnerFormB;
    private EditText editTextPartnerTraits;
    private ImageView partnerImageView;

    private AppUser appUser;
    private Integer partnerProfileCounts;

    private static final int CAMERA_REQUEST_CODE1 = 11, SELECT_FILE_CODE1 = 12;

    private Partner partner;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate.....Coming");
        setContentView(R.layout.activity_add_partner_form);
        appUser = new AppUser();
        partner = new Partner();
        addPartnerBirthday = findViewById(R.id.addPartnerBDate);
        addPartnerFirstName = findViewById(R.id.addPartnerFirstName);
        addPartnerLastName = findViewById(R.id.addPartnerLastName);
        addPartnerOccupation = findViewById(R.id.addPartnerOccupation);
        addPartnerTraits = findViewById(R.id.addPartnerTraitB);
        addPartnerPicture = findViewById(R.id.addPartnerPhotoB);
        addPartnerFormB = findViewById(R.id.addPartnerFormB);
        editTextPartnerTraits = findViewById(R.id.editTextPartnerTraits);
        partnerImageView = findViewById(R.id.partnerImageView7);
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //retrieve partner profile counts
        appUser.retrieveTotalCountPartnerProfiles();

        Log.d(TAG, "onCreate.....Coming....................1");
        addPartnerTraits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPartnerTraits.setVisibility(View.VISIBLE);
            }
        });
        addPartnerPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partnerImageView.setVisibility(View.VISIBLE);
                SelectImage();
            }
        });
        Log.d(TAG, "onCreate.....Coming....................2");
        addPartnerBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        addPartnerForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                addPartnerBirthday.setText(date);
            }
        };
        Log.d(TAG, "onCreate.....Coming....................3");
        addPartnerFormB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = addPartnerFirstName.getText().toString();
                String lastName = addPartnerLastName.getText().toString();
                String birthDate = addPartnerBirthday.getText().toString();

                partner.setFirstName(firstName);
                partner.setLastName(lastName);
                partner.setBirthDate(birthDate);
                partner.setTraits(editTextPartnerTraits.getText().toString());



                if(!firstName.isEmpty() && !lastName.isEmpty() && !birthDate.isEmpty())
                {
                    //update count of profiles
                    Integer integer = appUser.getTotalCountPartnerProfiles();
                    integer = integer+1;
                    //Set Parent Name
                    partner.setParentName("Partner"+integer);
                    partner.uploadAllDataToDatabase(addPartnerForm.this);
                    uploadPicture("Partner", integer.toString());
                    appUser.setTotalCountPartnerProfiles(integer);
                    integer = appUser.getTotalCountPartnerProfiles();
                    appUser.updateTotalCountPartnerProfiles(addPartnerForm.this);
                    Log.d(TAG, "Total Partner Counts: "+integer);
                }
                else
                {
                    if(firstName.isEmpty())
                    {
                        addPartnerFirstName.setError("First Name Should not be empty");
                    }
                    if(lastName.isEmpty())
                    {
                        addPartnerLastName.setError("Last Name Should not be empty");
                    }
                    if(birthDate.isEmpty())
                    {
                        Toast.makeText(addPartnerForm.this, "Date Should not be empty", Toast.LENGTH_SHORT).show();
                        //eventDate.setError("Date Should not be empty");
                    }
                }

                Intent intent = new Intent(addPartnerForm.this, LandingPage.class);
                startActivity(intent);
            }
        });
    }

    private void SelectImage()
    {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(addPartnerForm.this);
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
                partnerImageView.setImageBitmap(imageBitmap);
                //uploadPicture(imageBitmap, null);
            }else if( requestCode == SELECT_FILE_CODE1 )
            {
                Uri selectedImageUri = data.getData();
                Log.d(TAG, "onActivityResult ........... gallery coming");
                partnerImageView.setImageURI(selectedImageUri);
                //uploadPicture(null, selectedImageUri);
            }
        }
    }

    private void uploadPicture(String partnerName, String id) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");


        FirebaseUser currentUser = mAuth.getCurrentUser();
        String Date2 = id.replace('/','-');
        Log.d(TAG, "uploadPicture ........... Date: "+Date2);
        StorageReference riversRef = storageReference.child(currentUser.getEmail()).child(partnerName+Date2).child("IMG_" + "Profile");
        if((partnerImageView != null) && (partnerImageView.getDrawable() != null) )
        {
            Bitmap imageBitmap = ((BitmapDrawable)partnerImageView.getDrawable()).getBitmap();
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
                                Toast.makeText(addPartnerForm.this, "Failed to Upload", Toast.LENGTH_LONG).show();   // ...
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
}