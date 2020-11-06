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

public class surveyQ1fragment extends Fragment {

    private surveyQ1FragmentListener q1FragmentListener;
    private Button nextB;
    private Button Q1A;
    private Button Q1E;

    public surveyQ1fragment() {
        // Required empty public constructor
    }
    public interface surveyQ1FragmentListener
    {
        void calledQ1A(String string);
        void calledQ1E(String string);
        void onInputQ1Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_survey_q1fragment, container, false);
        nextB = v.findViewById(R.id.Q1surveyB);
        Q1A = v.findViewById(R.id.Q1A);
        Q1E = v.findViewById(R.id.Q1E);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1FragmentListener.onInputQ1Next();
            }
        });

        Q1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1FragmentListener.calledQ1A("Q1A");
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        Q1E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1FragmentListener.calledQ1E("Q1E");
                Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ1fragment.surveyQ1FragmentListener) {
            q1FragmentListener = (surveyQ1fragment.surveyQ1FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ1FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}