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
    Q11 for initial love language survey
 */

public class surveyQ11fragment extends Fragment {
    private surveyQ11FragmentListener q11FragmentListener;
    private Button nextB;
    private Button Q11B;
    private Button Q11A;

    public surveyQ11fragment() {
        // Required empty public constructor
    }
    public interface surveyQ11FragmentListener
    {
        void calledQ11B(String string);
        void calledQ11A(String string);
        void onInputQ11Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q11fragment, container, false);
        nextB = v.findViewById(R.id.Q11surveyB);
        Q11B = v.findViewById(R.id.Q11B);
        Q11A = v.findViewById(R.id.Q11A);

        //Starts the next fragment page in the initial love language survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q11FragmentListener.onInputQ11Next();
            }
        });

        //calls the proper function to record user's choice
        Q11B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q11FragmentListener.calledQ11B("Q11B");
                //Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        //calls the proper function to record user's choice
        Q11A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q11FragmentListener.calledQ11A("Q11A");
                //Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ11fragment.surveyQ11FragmentListener) {
            q11FragmentListener = (surveyQ11fragment.surveyQ11FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ11FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}