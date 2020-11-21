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
    Initial Survey Page 5
 */

public class surveypage5fragment extends Fragment {

    private surveyPage5FragmentListener page5FragmentListener;
    private Button nextB;

    public surveypage5fragment() {
        // Required empty public constructor
    }

    public interface surveyPage5FragmentListener
    {
        void onInputPage5Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_surveypage5fragment, container, false);
        nextB = v.findViewById(R.id.nextSurveyB5);

        //Starts the next fragment page in the initial survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page5FragmentListener.onInputPage5Next();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveypage5fragment.surveyPage5FragmentListener) {
            page5FragmentListener = (surveypage5fragment.surveyPage5FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyPage5FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}