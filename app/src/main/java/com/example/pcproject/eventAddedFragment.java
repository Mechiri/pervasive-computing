package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class eventAddedFragment extends Fragment {
    private Button eventDoneB;

    public eventAddedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_event_added, container, false);
        eventDoneB = v.findViewById(R.id.eventDoneB);

        return v;
    }
}