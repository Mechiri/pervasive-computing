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

public class surveyQ3fragment extends Fragment {

    private surveyQ3FragmentListener q3FragmentListener;
    private Button nextB;
    private Button Q3C;
    private Button Q3B;

    public surveyQ3fragment() {
        // Required empty public constructor
    }

    public interface surveyQ3FragmentListener
    {
        void calledQ3C(String string);
        void calledQ3B(String string);
        void onInputQ3Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q3fragment, container, false);
        nextB = v.findViewById(R.id.Q3surveyB);
        Q3C = v.findViewById(R.id.Q3C);
        Q3B = v.findViewById(R.id.Q3B);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q3FragmentListener.onInputQ3Next();
            }
        });

        Q3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q3FragmentListener.calledQ3C("Q3C");
                Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        Q3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q3FragmentListener.calledQ3B("Q3B");
                Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ3fragment.surveyQ3FragmentListener) {
            q3FragmentListener = (surveyQ3fragment.surveyQ3FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ3FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}