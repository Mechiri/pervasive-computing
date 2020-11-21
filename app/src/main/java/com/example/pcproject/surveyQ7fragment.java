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
    Q7 for initial love language survey
 */

public class surveyQ7fragment extends Fragment {

    private surveyQ7FragmentListener q7FragmentListener;
    private Button nextB;
    private Button Q7A;
    private  Button Q7C;

    public surveyQ7fragment() {
        // Required empty public constructor
    }
    public interface surveyQ7FragmentListener
    {
        void calledQ7A(String string);
        void calledQ7C(String string);
        void onInputQ7Next();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q7fragment, container, false);
        nextB = v.findViewById(R.id.Q7surveyB);
        Q7A = v.findViewById(R.id.Q7A);
        Q7C = v.findViewById(R.id.Q7C);

        //Starts the next fragment page in the initial love language survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q7FragmentListener.onInputQ7Next();
            }
        });

        //calls the proper function to record user's choice
        Q7A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q7FragmentListener.calledQ7A("Q7A");
                //Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        //calls the proper function to record user's choice
        Q7C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q7FragmentListener.calledQ7C("Q7C");
                //Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ7fragment.surveyQ7FragmentListener) {
            q7FragmentListener = (surveyQ7fragment.surveyQ7FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ7FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}