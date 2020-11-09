package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class fightEventFragment extends Fragment {

    private Button nextB;
    private Button addFightTopicB;
    private Button fightResYes;
    private Button fightResNo;
    private Button fightResUndecided;
    private SeekBar fightPersonalBar;
    private SeekBar fightHurtfulBar;
    private SeekBar fightPartnerHandleBar;
    private SeekBar fightYouHandleBar;
    private EditText fightOtherNotes;

    public fightEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fight_event, container, false);
        nextB = v.findViewById(R.id.nextEventB);
        addFightTopicB = v.findViewById(R.id.addFightTopicB);
        fightResYes = v.findViewById(R.id.fightResYes);
        fightResNo = v.findViewById(R.id.fightResNo);
        fightResUndecided = v.findViewById(R.id.fightResUndecided);
        fightPersonalBar = v.findViewById(R.id.fightPersonalBar);
        fightHurtfulBar = v.findViewById(R.id.fightHurtfulBar);
        fightPartnerHandleBar = v.findViewById(R.id.fightPartnerHandleBar);
        fightYouHandleBar = v.findViewById(R.id.fightYouHandleBar);
        fightOtherNotes = v.findViewById(R.id.fightOtherNotes);

        return v;
    }
}