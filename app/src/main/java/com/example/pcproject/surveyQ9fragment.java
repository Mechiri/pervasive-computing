package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ9fragment extends Fragment {
    private Button nextB;
    private Button Q9B;
    private Button Q9C;

    public surveyQ9fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q9fragment, container, false);
        nextB = v.findViewById(R.id.Q9surveyB);
        Q9B = v.findViewById(R.id.Q9B);
        Q9C = v.findViewById(R.id.Q9C);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ10fragment surveyQ10fragment = new surveyQ10fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ10fragment);
                transaction.commit();
            }
        });

        Q9B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        Q9C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}