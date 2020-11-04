package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ5fragment extends Fragment {

    private Button nextB;
    private Button Q5E;
    private Button Q5C;

    public surveyQ5fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q5fragment, container, false);
        nextB = v.findViewById(R.id.Q5surveyB);
        Q5E = v.findViewById(R.id.Q5E);
        Q5C = v.findViewById(R.id.Q5C);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ6fragment surveyQ6fragment = new surveyQ6fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ6fragment);
                transaction.commit();
            }
        });

        Q5E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q5C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}