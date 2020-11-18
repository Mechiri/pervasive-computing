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

public class surveyQ14fragment extends Fragment {
    private surveyQ14FragmentListener q14FragmentListener;
    private Button nextB;
    private Button Q14E;
    private Button Q14B;

    public surveyQ14fragment() {
        // Required empty public constructor
    }

    public interface surveyQ14FragmentListener
    {
        void calledQ14E(String string);
        void calledQ14B(String string);
        void onInputQ14Next();
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
                q14FragmentListener.onInputQ14Next();
            }
        });

        Q14E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q14FragmentListener.calledQ14E("Q14E");
                //Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q14B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q14FragmentListener.calledQ14B("Q14B");
                //Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ14fragment.surveyQ14FragmentListener) {
            q14FragmentListener = (surveyQ14fragment.surveyQ14FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ14FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}