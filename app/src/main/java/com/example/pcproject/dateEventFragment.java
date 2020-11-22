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

/*

Dating Event Fragments

*/

public class dateEventFragment extends Fragment {
    private static final String TAG = "dataEventFragment";
    private dateEventFragment.dateEventFragmentListener dateEventFragmentListener;

    private Button nextEventB;
    private EditText datePlace;
    private EditText dateActions;
    private EditText dateLength;
    private SeekBar overall;
    private SeekBar conversation;
    private EditText otherNotes1;


    public dateEventFragment() {
        // Required empty public constructor
    }

    public interface dateEventFragmentListener
    {
        void onInputDateEventSent( String whereDidYouGo, String whatDidYouDo, String howLongDate, Integer dateRate, Integer conversationRate, String otherNotes );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_date_event, container, false);
        nextEventB = v.findViewById(R.id.finishEventB);
        datePlace = v.findViewById(R.id.datePlace);
        dateActions = v.findViewById(R.id.dateActions);
        dateLength = v.findViewById(R.id.dateLength);
        overall = v.findViewById(R.id.dateOverallBar);
        conversation = v.findViewById(R.id.dateConversationBar);
        otherNotes1 = v.findViewById(R.id.dateOtherNotes);

        nextEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String whereDidYouGo = datePlace.getText().toString();
                String whatDidYouDo =  dateActions.getText().toString();
                String howLongDate = dateLength.getText().toString();
                Integer dateRate  = overall.getProgress();
                Integer conversationRate = conversation.getProgress();
                String otherNotes = otherNotes1.getText().toString();

                dateEventFragmentListener.onInputDateEventSent( whereDidYouGo, whatDidYouDo, howLongDate, dateRate, conversationRate, otherNotes );
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof dateEventFragment.dateEventFragmentListener) {
            dateEventFragmentListener = (dateEventFragment.dateEventFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement dateEventFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}