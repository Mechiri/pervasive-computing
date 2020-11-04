package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ11fragment extends Fragment {
    private Button nextB;
    private Button Q11B;
    private Button Q11A;

    public surveyQ11fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q11fragment, container, false);
        nextB = v.findViewById(R.id.Q11surveyB);
        Q11B = v.findViewById(R.id.Q11B);
        Q11A = v.findViewById(R.id.Q11A);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ12fragment surveyQ12fragment = new surveyQ12fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ12fragment);
                transaction.commit();
            }
        });

        Q11B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        Q11A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}