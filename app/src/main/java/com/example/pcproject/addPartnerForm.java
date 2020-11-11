package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.Calendar;

public class addPartnerForm extends AppCompatActivity {
    private static final String TAG = "DATE";
    private TextView addPartnerBirthday;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private EditText addPartnerFirstName;
    private EditText addPartnerLastName;
    private EditText addPartnerOccupation;
    private Button addPartnerTraits;
    private Button addPartnerPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partner_form);

        addPartnerBirthday = findViewById(R.id.addPartnerBDate);
        addPartnerFirstName = findViewById(R.id.addPartnerFirstName);
        addPartnerLastName = findViewById(R.id.addPartnerLastName);
        addPartnerOccupation = findViewById(R.id.addPartnerOccupation);
        addPartnerTraits = findViewById(R.id.addPartnerTraitB);
        addPartnerPicture = findViewById(R.id.addPartnerPhotoB);

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
    }
}