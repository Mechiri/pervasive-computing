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

/*
    Initial Survey Page 1
 */

public class surveyPage1Fragment extends Fragment {

    private Button nextB;
    private surveyPage1FragmentListener page1FragmentListener;
    public surveyPage1Fragment() {
        // Required empty public constructor
    }

    public interface surveyPage1FragmentListener
    {
        void onInputPage1Sent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_surveypage1fragment, container, false);
        nextB = v.findViewById(R.id.nextSurveyB);

        //Starts the next fragment page in the initial survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page1FragmentListener.onInputPage1Sent();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyPage1FragmentListener) {
            page1FragmentListener = (surveyPage1FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyPage1FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}