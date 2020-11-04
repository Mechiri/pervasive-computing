package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ10fragment extends Fragment {
    private Button nextB;
    private Button Q10D;
    private Button Q10A;

    public surveyQ10fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q10fragment, container, false);
        nextB = v.findViewById(R.id.Q10surveyB);
        Q10D = v.findViewById(R.id.Q10D);
        Q10A = v.findViewById(R.id.Q10A);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyQ11fragment surveyQ11fragment = new surveyQ11fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, surveyQ11fragment);
                transaction.commit();
            }
        });

        Q10D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        Q10A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}