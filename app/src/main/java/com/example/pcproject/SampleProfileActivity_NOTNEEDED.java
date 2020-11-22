package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SampleProfileActivity_NOTNEEDED extends AppCompatActivity {

    private static final String TAG = "SampleProfileActivity";

    private ImageView mProfile1Image;
    private Button bProfile1Capture;
    private static final int CAMERA_REQUEST_CODE1 = 11, SELECT_FILE_CODE1 = 12;
    private StorageReference storageReference;
    private File photoFile = null;
    private ProgressDialog mProgress;

    private ArrayList<SeekBar> seekBars = new ArrayList<SeekBar>();
    private EditText editText;
    Button button11;
    Button bOutput;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_profile);


        mProfile1Image = findViewById(R.id.imageView1);
        bProfile1Capture = findViewById(R.id.buttonC1);
        mProgress = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        seekBars.add(((SeekBar) findViewById(R.id.seekBar11)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar12)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar13)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar14)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar15)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar16)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar17)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar18)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar19)));
        seekBars.add(((SeekBar) findViewById(R.id.seekBar20)));
        editText = findViewById(R.id.editTextTextPersonName1);
        button11 = findViewById(R.id.button11);
        bOutput = findViewById(R.id.button12);

        bOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleProfileActivity_NOTNEEDED.this, ProfileOutput_NOTNEEDED.class));
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadAllData("profile1");
            }
        });

        bProfile1Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Set on Click Listener...........................................");
                SelectImage();
            }
        });
    }

    private void upLoadAllData(String sProfile)
    {
        ArrayList<Integer> seekBarValues = new ArrayList<Integer>();
        for (SeekBar seekbar : seekBars) {
            seekBarValues.add(seekbar.getProgress());
        }
        String logs = editText.getText().toString();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Map<String, Object> userData = new HashMap<>();
        userData.put("SeekBarValues", seekBarValues);
        userData.put("Logs", logs);
        userData.put("timestamp", FieldValue.serverTimestamp());
        Log.d(TAG,"upLoadAllData: coming!");
        db.collection(currentUser.getEmail()).document(sProfile).set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SampleProfileActivity_NOTNEEDED.this, "Uploaded Data", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SampleProfileActivity_NOTNEEDED.this, "Error in Uploading Data", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Database: Upload Failure");
                    }
                });
                /*
        db.collection(currentUser.getEmail()).add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Previous Location Added: "+documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error Occurred: " + e.getMessage());
            }
        });

                 */
    }

    private void SelectImage()
    {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SampleProfileActivity_NOTNEEDED.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == CAMERA_REQUEST_CODE1) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mProfile1Image.setImageBitmap(imageBitmap);
                uploadPicture(imageBitmap, null);
            }else if( requestCode == SELECT_FILE_CODE1 )
            {
                Uri selectedImageUri = data.getData();
                mProfile1Image.setImageURI(selectedImageUri);
                uploadPicture(null, selectedImageUri);
            }
        }
    }


    private void uploadPicture(Bitmap imageBitmap, Uri uploadImage) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final UUID randomKey = UUID.randomUUID();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        StorageReference riversRef = storageReference.child(currentUser.getEmail()).child("Profile" + 1 + "").child("IMG_" + randomKey.toString());
        if(imageBitmap != null)
        {
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
                            Toast.makeText(SampleProfileActivity_NOTNEEDED.this, "Failed to Upload", Toast.LENGTH_LONG).show();   // ...
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
        if(uploadImage != null)
        {
            riversRef.putFile(uploadImage)
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
                            Toast.makeText(SampleProfileActivity_NOTNEEDED.this, "Failed to Upload", Toast.LENGTH_LONG).show();   // ...
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