package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ2fragment extends Fragment {

    private Button nextB;
    private Button Q2B;
    private Button Q2D;

    public surveyQ2fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q2fragment, container, false);
        nextB = v.findViewById(R.id.Q2surveyB);
        Q2B = v.findViewById(R.id.Q2B);
        Q2D = v.findViewById(R.id.Q2D);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ3fragment surveyQ3fragment = new surveyQ3fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ3fragment);
                transaction.commit();
            }
        });

        Q2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        Q2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}