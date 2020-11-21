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
    Initial Survey Page 6 providing information for love language survey
 */

public class surveypage6fragment extends Fragment {

    private surveyPage6FragmentListener page6FragmentListener;
    private Button nextB;

    public surveypage6fragment() {
        // Required empty public constructor
    }

    public interface surveyPage6FragmentListener
    {
        void onInputPage6Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_surveypage6fragment, container, false);
        nextB = v.findViewById(R.id.nextSurveyB6);

        //Starts the next fragment page for love language survey Q1
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page6FragmentListener.onInputPage6Next();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveypage6fragment.surveyPage6FragmentListener) {
            page6FragmentListener = (surveypage6fragment.surveyPage6FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyPage6FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}