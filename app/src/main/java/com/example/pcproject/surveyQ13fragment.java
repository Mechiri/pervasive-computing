package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class surveyQ13fragment extends Fragment {
    private Button nextB;
    private Button Q13A;
    private Button Q13C;

    public surveyQ13fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q13fragment, container, false);
        nextB = v.findViewById(R.id.Q13surveyB);
        Q13A = v.findViewById(R.id.Q13A);
        Q13C = v.findViewById(R.id.Q13C);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ14fragment surveyQ14fragment = new surveyQ14fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ14fragment);
                transaction.commit();
            }
        });

        Q13A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        Q13C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}