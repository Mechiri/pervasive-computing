package com.example.pcproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.sql.DataTruncation;

/*
    Other event type in the add event form. otherEventFragment appears during add event form
    when user select's the event type to be "other"
 */

public class otherEventFragment extends Fragment {
    private static final String TAG = "otherEventFragment";
    private otherEventFragment.otherEventFragmentListener otherEventFragmentListener;

    private Button nextB;
    private Button otherEventPositive;
    private Button otherEventNegative;
    private Button otherEventNeutral;
    private EditText otherEventTitle;
    private EditText otherEventDesc;
    private SeekBar otherEventOverallBar;
    private EditText otherEventNotes;

    private String eventStatus;

    public otherEventFragment() {
        // Required empty public constructor
    }

    public interface otherEventFragmentListener
    {
        void onInputOtherEventSent(String eventStatus, String title, String describeEvent, Integer rateOverallExperience, String otherNotes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_other_event, container, false);
        nextB = v.findViewById(R.id.finishEventB);
        otherEventPositive = v.findViewById(R.id.otherEventPostive);
        otherEventNegative = v.findViewById(R.id.otherEventNegative);
        otherEventNeutral = v.findViewById(R.id.otherEventNeutral);
        otherEventTitle = v.findViewById(R.id.otherEventTitle);
        otherEventDesc = v.findViewById(R.id.otherEventDesc);
        otherEventOverallBar = v.findViewById(R.id.otherEventOverallBar);
        otherEventNotes = v.findViewById(R.id.otherEventNotes);

        otherEventPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventStatus = "Positive";
            }
        });
        otherEventNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventStatus = "Negative";
            }
        });
        otherEventNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventStatus = "Neutral";
            }
        });

        //Records the information inputed by users
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = otherEventTitle.getText().toString();
                String describeEvent = otherEventDesc.getText().toString();
                Integer rateOverallExperience = otherEventOverallBar.getProgress();
                String otherNotes = otherEventNotes.getText().toString();

                otherEventFragmentListener.onInputOtherEventSent(eventStatus, title, describeEvent, rateOverallExperience, otherNotes);
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof otherEventFragment.otherEventFragmentListener) {
            otherEventFragmentListener = (otherEventFragment.otherEventFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement otherEventFragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}