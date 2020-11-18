package com.example.pcproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ8fragment extends Fragment {

    private surveyQ8FragmentListener q8FragmentListener;
    private Button nextB;
    private Button Q8E;
    private Button Q8A;


    public surveyQ8fragment() {
        // Required empty public constructor
    }

    public interface surveyQ8FragmentListener
    {
        void calledQ8E(String string);
        void calledQ8A(String string);
        void onInputQ8Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q8fragment, container, false);
        nextB = v.findViewById(R.id.Q8surveyB);
        Q8E = v.findViewById(R.id.Q8E);
        Q8A = v.findViewById(R.id.Q8A);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q8FragmentListener.onInputQ8Next();
            }
        });

        Q8E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q8FragmentListener.calledQ8E("Q8E");
                //Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q8A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q8FragmentListener.calledQ8A("Q8A");
                //Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ8fragment.surveyQ8FragmentListener) {
            q8FragmentListener = (surveyQ8fragment.surveyQ8FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ8FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}