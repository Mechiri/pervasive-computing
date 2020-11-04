package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class surveyQ6fragment extends Fragment {

    private Button nextB;
    private Button Q6B;
    private Button Q6E;

    public surveyQ6fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q6fragment, container, false);
        nextB = v.findViewById(R.id.Q6surveyB);
        Q6B = v.findViewById(R.id.Q6B);
        Q6E = v.findViewById(R.id.Q6E);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ7fragment surveyQ7fragment = new surveyQ7fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ7fragment);
                transaction.commit();
            }
        });

        Q6B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        Q6E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}