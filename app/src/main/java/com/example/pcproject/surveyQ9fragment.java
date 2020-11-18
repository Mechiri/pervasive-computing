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

public class surveyQ9fragment extends Fragment {
    private surveyQ9FragmentListener q9FragmentListener;
    private Button nextB;
    private Button Q9B;
    private Button Q9C;

    public surveyQ9fragment() {
        // Required empty public constructor
    }
    public interface surveyQ9FragmentListener
    {
        void calledQ9B(String string);
        void calledQ9C(String string);
        void onInputQ9Next();
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
                q9FragmentListener.onInputQ9Next();
            }
        });

        Q9B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q9FragmentListener.calledQ9B("Q9B");
                //Toast.makeText(getActivity(), "Quality Time", Toast.LENGTH_LONG).show();
            }
        });

        Q9C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q9FragmentListener.calledQ9C("Q9C");
                //Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ9fragment.surveyQ9FragmentListener) {
            q9FragmentListener = (surveyQ9fragment.surveyQ9FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ9FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}