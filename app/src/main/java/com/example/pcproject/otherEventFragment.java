package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class otherEventFragment extends Fragment {
    private Button nextB;
    private Button otherEventPositive;
    private Button otherEventNegative;
    private Button otherEventNeutral;
    private EditText otherEventTitle;
    private EditText otherEventDesc;
    private SeekBar otherEventOverallBar;
    private EditText otherEventNotes;

    public otherEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_other_event, container, false);
        nextB = v.findViewById(R.id.nextEventB);
        otherEventPositive = v.findViewById(R.id.otherEventPostive);
        otherEventNegative = v.findViewById(R.id.otherEventNegative);
        otherEventNeutral = v.findViewById(R.id.otherEventNeutral);
        otherEventTitle = v.findViewById(R.id.otherEventTitle);
        otherEventDesc = v.findViewById(R.id.otherEventDesc);
        otherEventOverallBar = v.findViewById(R.id.otherEventOverallBar);
        otherEventNotes = v.findViewById(R.id.otherEventNotes);

        return v;
    }
}