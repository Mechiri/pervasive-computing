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
import android.widget.Toast;

/*
    Q13 for initial love language survey
 */


public class surveyQ13fragment extends Fragment {
    private surveyQ13FragmentListener q13FragmentListener;
    private Button nextB;
    private Button Q13A;
    private Button Q13C;

    public surveyQ13fragment() {
        // Required empty public constructor
    }

    public interface surveyQ13FragmentListener
    {
        void calledQ13A(String string);
        void calledQ13C(String string);
        void onInputQ13Next();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q13fragment, container, false);
        nextB = v.findViewById(R.id.Q13surveyB);
        Q13A = v.findViewById(R.id.Q13A);
        Q13C = v.findViewById(R.id.Q13C);

        //Starts the next fragment page in the initial love language survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q13FragmentListener.onInputQ13Next();
            }
        });

        //calls the proper function to record user's choice
        Q13A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q13FragmentListener.calledQ13A("Q13A");
                //Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        //calls the proper function to record user's choice
        Q13C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q13FragmentListener.calledQ13C("Q13C");
                //Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ13fragment.surveyQ13FragmentListener) {
            q13FragmentListener = (surveyQ13fragment.surveyQ13FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ13FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}