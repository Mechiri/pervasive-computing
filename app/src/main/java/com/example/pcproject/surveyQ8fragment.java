package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ8fragment extends Fragment {
    private Button nextB;
    private Button Q8E;
    private Button Q8A;


    public surveyQ8fragment() {
        // Required empty public constructor
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
                surveyQ9fragment surveyQ9fragment = new surveyQ9fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ9fragment);
                transaction.commit();
            }
        });

        Q8E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q8A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}