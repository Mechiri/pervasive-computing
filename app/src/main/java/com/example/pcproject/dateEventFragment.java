package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class dateEventFragment extends Fragment {
    private Button nextEventB;
    private EditText datePlace;
    private EditText dateActions;
    private EditText dateLength;
    private SeekBar overall;
    private SeekBar conversation;
    private EditText otherNotes;


    public dateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_date_event, container, false);
        nextEventB = v.findViewById(R.id.nextEventB);
        datePlace = v.findViewById(R.id.datePlace);
        dateActions = v.findViewById(R.id.dateActions);
        dateLength = v.findViewById(R.id.dateLength);
        overall = v.findViewById(R.id.dateOverallBar);
        conversation = v.findViewById(R.id.dateConversationBar);
        otherNotes = v.findViewById(R.id.dateOtherNotes);

        return v;
    }
}