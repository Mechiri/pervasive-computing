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
    Q12 for initial love language survey
 */

public class surveyQ12fragment extends Fragment {
    private surveyQ12FragmentListener q12FragmentListener;
    private Button nextB;
    private Button Q12E;
    private Button Q12D;

    public surveyQ12fragment() {
        // Required empty public constructor
    }
    public interface surveyQ12FragmentListener
    {
        void calledQ12E(String string);
        void calledQ12D(String string);
        void onInputQ12Next();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q12fragment, container, false);
        nextB = v.findViewById(R.id.Q12surveyB);
        Q12E = v.findViewById(R.id.Q12E);
        Q12D = v.findViewById(R.id.Q12D);

        //Starts the next fragment page in the initial love language survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q12FragmentListener.onInputQ12Next();
            }
        });

        //calls the proper function to record user's choice
        Q12E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q12FragmentListener.calledQ12E("Q12E");
                //Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        //calls the proper function to record user's choice
        Q12D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q12FragmentListener.calledQ12D("Q12D");
                //Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ12fragment.surveyQ12FragmentListener) {
            q12FragmentListener = (surveyQ12fragment.surveyQ12FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ12FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}