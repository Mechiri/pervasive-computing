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
    Q6 for initial love language survey
 */

public class surveyQ6fragment extends Fragment {

    private surveyQ6FragmentListener q6FragmentListener;
    private Button nextB;
    private Button Q6B;
    private Button Q6E;

    public surveyQ6fragment() {
        // Required empty public constructor
    }
    public interface surveyQ6FragmentListener
    {
        void calledQ6B(String string);
        void calledQ6E(String string);
        void onInputQ6Next();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q6fragment, container, false);
        nextB = v.findViewById(R.id.Q6surveyB);
        Q6B = v.findViewById(R.id.Q6B);
        Q6E = v.findViewById(R.id.Q6E);

        //Starts the next fragment page in the initial love language survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q6FragmentListener.onInputQ6Next();
            }
        });

        //calls the proper function to record user's choice
        Q6B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q6FragmentListener.calledQ6B("Q6B");
                //Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        //calls the proper function to record user's choice
        Q6E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q6FragmentListener.calledQ6E("Q6E");
                //Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ6fragment.surveyQ6FragmentListener) {
            q6FragmentListener = (surveyQ6fragment.surveyQ6FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ6FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}