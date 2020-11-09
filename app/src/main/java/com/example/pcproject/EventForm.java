package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventForm extends AppCompatActivity {
    private static final String TAG = "DATE";
    private TextView eventDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private Button nextEventB;
    private Button addTraitB;
    private Button addPictureB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        eventDate = findViewById(R.id.eventDate);
        nextEventB = findViewById(R.id.nextEventB);
        addTraitB = findViewById(R.id.addTraitB);
        addPictureB = findViewById(R.id.addPictureB);

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        EventForm.this,
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
                eventDate.setText(date);
            }
        };

        List<String> events = new ArrayList<String>();
        events.add("Reflection");
        events.add("Date");
        events.add("Fight");
        events.add("Other");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, events);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEvent);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if(item != null)
                {
                    Toast.makeText(EventForm.this, item.toString(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(EventForm.this, "Selected", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}