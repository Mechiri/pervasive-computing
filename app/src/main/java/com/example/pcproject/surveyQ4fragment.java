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


public class surveyQ4fragment extends Fragment {

    private surveyQ4FragmentListener q4FragmentListener;
    private Button nextB;
    private Button Q4D;
    private Button Q4E;

    public surveyQ4fragment() {
        // Required empty public constructor
    }

    public interface surveyQ4FragmentListener
    {
        void calledQ4D(String string);
        void calledQ4E(String string);
        void onInputQ4Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q4fragment, container, false);
        nextB = v.findViewById(R.id.Q4surveyB);
        Q4D = v.findViewById(R.id.Q4D);
        Q4E = v.findViewById(R.id.Q4E);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q4FragmentListener.onInputQ4Next();
            }
        });

        Q4D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q4FragmentListener.calledQ4D("Q4D");
                //Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        Q4E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q4FragmentListener.calledQ4E("Q4E");
                //Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ4fragment.surveyQ4FragmentListener) {
            q4FragmentListener = (surveyQ4fragment.surveyQ4FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ4FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}