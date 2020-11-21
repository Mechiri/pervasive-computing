package com.example.pcproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/*
    Q15 for initial love language survey
 */

public class surveyQ15fragment extends Fragment {
    private surveyQ15FragmentListener q15FragmentListener;
    private Button nextB;
    private Button Q15A;
    private Button Q15D;

    public surveyQ15fragment() {
        // Required empty public constructor
    }
    public interface surveyQ15FragmentListener
    {
        void calledQ15A(String string);
        void calledQ15D(String string);
        void onInputQ15Next();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q15fragment, container, false);
        nextB = v.findViewById(R.id.Q15surveyB);
        Q15A = v.findViewById(R.id.Q15A);
        Q15D = v.findViewById(R.id.Q15D);

        //Starts the next fragment page in the initial love language survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q15FragmentListener.onInputQ15Next();
            }
        });

        //calls the proper function to record user's choice
        Q15A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q15FragmentListener.calledQ15A("Q15A");
                //Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        //calls the proper function to record user's choice
        Q15D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q15FragmentListener.calledQ15D("Q15D");
                //Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ15fragment.surveyQ15FragmentListener) {
            q15FragmentListener = (surveyQ15fragment.surveyQ15FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ15FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}