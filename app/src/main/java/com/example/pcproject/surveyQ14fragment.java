package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ14fragment extends Fragment {
    private Button nextB;
    private Button Q14E;
    private Button Q14B;

    public surveyQ14fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q14fragment, container, false);
        nextB = v.findViewById(R.id.Q14surveyB);
        Q14E = v.findViewById(R.id.Q14E);
        Q14B = v.findViewById(R.id.Q14B);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ15fragment surveyQ15fragment = new surveyQ15fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ15fragment);
                transaction.commit();
            }
        });

        Q14E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q14B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}