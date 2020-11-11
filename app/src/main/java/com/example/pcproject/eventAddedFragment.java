package com.example.pcproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


public class eventAddedFragment extends Fragment {
    Timer timer;

    public eventAddedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_event_added, container, false);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), LandingPage.class);
                startActivity(intent);
                getActivity().finish();
            }
        }, 7000);

        return v;
    }
}