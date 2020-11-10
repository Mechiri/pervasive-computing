package com.example.pcproject;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class mainEventFragment extends Fragment {
    private mainEventFragmentListener mainEventFragmentListener;
    private static final String TAG = "DATE";
    private TextView eventDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String itemSelected;
    private Button nextEventB;
    private Button addTraitB;
    private Button addPictureB;
    public mainEventFragment() {
        // Required empty public constructor
    }
    public interface mainEventFragmentListener
    {
        //for next button
        void onInputMainEventSent();
        //to get spinner selection
        void onInputItemSelected(String itemSelected);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_event, container, false);
        addTraitB = v.findViewById(R.id.addFightTopicB);
        addPictureB = v.findViewById(R.id.addPictureB);
        nextEventB = v.findViewById(R.id.nextEventB);
        eventDate = v.findViewById(R.id.eventDate);
        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
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
        events.add("Event Type");
        events.add("Reflection");
        events.add("Date");
        events.add("Fight");
        events.add("Other");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, events);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerEvent);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if(item != null)
                {
                    itemSelected = item.toString();
                    mainEventFragmentListener.onInputItemSelected(itemSelected);
                    Toast.makeText(getActivity(), item.toString(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Selected", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        nextEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainEventFragmentListener.onInputMainEventSent();
            }
        });
        return v;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof mainEventFragment.mainEventFragmentListener) {
            mainEventFragmentListener = (mainEventFragment.mainEventFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement mainEventFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}