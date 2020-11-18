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

public class surveyQ2fragment extends Fragment {

    private surveyQ2FragmentListener q2FragmentListener;
    private Button nextB;
    private Button Q2B;
    private Button Q2D;

    public surveyQ2fragment() {
        // Required empty public constructor
    }

    public interface surveyQ2FragmentListener
    {
        void calledQ2B(String string);
        void calledQ2D(String string);
        void onInputQ2Next();
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
                q2FragmentListener.onInputQ2Next();
            }
        });

        Q2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q2FragmentListener.calledQ2B("Q2B");
                //Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        Q2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q2FragmentListener.calledQ2D("Q2D");
                //Toast.makeText(getActivity(), "Acts of Service", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ2fragment.surveyQ2FragmentListener) {
            q2FragmentListener = (surveyQ2fragment.surveyQ2FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ2FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}