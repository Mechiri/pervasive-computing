package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class surveyQ15fragment extends Fragment {
    private Button nextB;
    private Button Q15A;
    private Button Q15D;

    public surveyQ15fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q15fragment, container, false);
        nextB = v.findViewById(R.id.Q15surveyB);
        Q15A = v.findViewById(R.id.Q15A);
        Q15D = v.findViewById(R.id.Q15D);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Q15A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        Q15D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}