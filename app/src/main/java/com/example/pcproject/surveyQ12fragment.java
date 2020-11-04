package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ12fragment extends Fragment {
    private Button nextB;
    private Button Q12E;
    private Button Q12D;

    public surveyQ12fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q12fragment, container, false);
        nextB = v.findViewById(R.id.Q12surveyB);
        Q12E = v.findViewById(R.id.Q12E);
        Q12D = v.findViewById(R.id.Q12D);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ13fragment surveyQ13fragment = new surveyQ13fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ13fragment);
                transaction.commit();
            }
        });

        Q12E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q12D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}