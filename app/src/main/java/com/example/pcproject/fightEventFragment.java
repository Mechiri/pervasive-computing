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

public class fightEventFragment extends Fragment {
    private static final String TAG = "fightEventFragment";
    private fightEventFragment.fightEventFragmentListener fightEventFragmentListener;

    private EditText etFightAbout;
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

    private String resolution;

    public fightEventFragment() {
        // Required empty public constructor
    }

    public interface fightEventFragmentListener
    {
        void onInputFightEventSent(String fightAbout, String resolution, Integer madePersonal, Integer fightHurtful, Integer theyHandleFight, Integer youHandledFight, String otherNotes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fight_event, container, false);
        etFightAbout = v.findViewById(R.id.editTextFightAbout);
        nextB = v.findViewById(R.id.finishEventB);
        addFightTopicB = v.findViewById(R.id.addFightTopicB);
        fightResYes = v.findViewById(R.id.fightResYes);
        fightResNo = v.findViewById(R.id.fightResNo);
        fightResUndecided = v.findViewById(R.id.fightResUndecided);
        fightPersonalBar = v.findViewById(R.id.fightPersonalBar);
        fightHurtfulBar = v.findViewById(R.id.fightHurtfulBar);
        fightPartnerHandleBar = v.findViewById(R.id.fightPartnerHandleBar);
        fightYouHandleBar = v.findViewById(R.id.fightYouHandleBar);
        fightOtherNotes = v.findViewById(R.id.fightOtherNotes);

        addFightTopicB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFightAbout.setVisibility(View.VISIBLE);
            }
        });
        fightResYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolution = "Yes";
            }
        });
        fightResNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolution = "No";
            }
        });
        fightResUndecided.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolution = "UnDecided";
            }
        });

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fightAbout = etFightAbout.getText().toString();
                Integer madePersonal = fightPersonalBar.getProgress();
                Integer fightHurtful = fightHurtfulBar.getProgress();
                Integer theyHandleFight = fightPartnerHandleBar.getProgress();
                Integer youHandledFight = fightYouHandleBar.getProgress();
                String otherNotes = fightOtherNotes.getText().toString();

                fightEventFragmentListener.onInputFightEventSent(fightAbout, resolution, madePersonal, fightHurtful, theyHandleFight, youHandledFight, otherNotes);
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof fightEventFragment.fightEventFragmentListener) {
            fightEventFragmentListener = (fightEventFragment.fightEventFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement fightEventFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}