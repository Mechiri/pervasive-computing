package com.example.pcproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/*
    Initial Survey Page 4 asking user's what their deepest desires in a relationship
 */

public class surveypage4fragment extends Fragment {

    private surveyPage4FragmentListener page4FragmentListener;
    private Button nextB;
    private EditText desiresTextInput;

    public surveypage4fragment() {
        // Required empty public constructor
    }

    public interface surveyPage4FragmentListener
    {
        void onInputPage4Next(String mString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_surveypage4fragment, container, false);
        nextB = v.findViewById(R.id.nextSurveyB4);
        desiresTextInput = v.findViewById(R.id.desiresTextInput);

        //Starts the next fragment page in the initial survey and records the user's text
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page4FragmentListener.onInputPage4Next(desiresTextInput.getText().toString());
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveypage4fragment.surveyPage4FragmentListener) {
            page4FragmentListener = (surveypage4fragment.surveyPage4FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyPage4FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}