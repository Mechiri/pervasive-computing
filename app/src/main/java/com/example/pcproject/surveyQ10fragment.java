package com.example.pcproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class surveyQ10fragment extends Fragment {
    private surveyQ10FragmentListener q10FragmentListener;
    private Button nextB;
    private Button Q10D;
    private Button Q10A;

    public surveyQ10fragment() {
        // Required empty public constructor
    }
    public interface surveyQ10FragmentListener
    {
        void calledQ10D(String string);
        void calledQ10A(String string);
        void onInputQ10Next();
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
                q10FragmentListener.onInputQ10Next();
            }
        });

        Q10D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q10FragmentListener.calledQ10D("Q10D");
                Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        Q10A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q10FragmentListener.calledQ10A("Q10A");
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ10fragment.surveyQ10FragmentListener) {
            q10FragmentListener = (surveyQ10fragment.surveyQ10FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ10FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}