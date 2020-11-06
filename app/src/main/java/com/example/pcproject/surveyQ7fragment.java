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

public class surveyQ7fragment extends Fragment {

    private surveyQ7FragmentListener q7FragmentListener;
    private Button nextB;
    private Button Q7A;
    private  Button Q7C;

    public surveyQ7fragment() {
        // Required empty public constructor
    }
    public interface surveyQ7FragmentListener
    {
        void calledQ7A(String string);
        void calledQ7C(String string);
        void onInputQ7Next();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q7fragment, container, false);
        nextB = v.findViewById(R.id.Q7surveyB);
        Q7A = v.findViewById(R.id.Q7A);
        Q7C = v.findViewById(R.id.Q7C);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q7FragmentListener.onInputQ7Next();
            }
        });

        Q7A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q7FragmentListener.calledQ7A("Q7A");
                Toast.makeText(getActivity(), "Words of Affirmation", Toast.LENGTH_LONG).show();
            }
        });

        Q7C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q7FragmentListener.calledQ7C("Q7C");
                Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ7fragment.surveyQ7FragmentListener) {
            q7FragmentListener = (surveyQ7fragment.surveyQ7FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ7FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}