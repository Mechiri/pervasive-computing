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

public class surveyQ5fragment extends Fragment {

    private surveyQ5FragmentListener q5FragmentListener;
    private Button nextB;
    private Button Q5E;
    private Button Q5C;

    public surveyQ5fragment() {
        // Required empty public constructor
    }

    public interface surveyQ5FragmentListener
    {
        void calledQ5E(String string);
        void calledQ5C(String string);
        void onInputQ5Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_q5fragment, container, false);
        nextB = v.findViewById(R.id.Q5surveyB);
        Q5E = v.findViewById(R.id.Q5E);
        Q5C = v.findViewById(R.id.Q5C);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5FragmentListener.onInputQ5Next();
            }
        });

        Q5E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5FragmentListener.calledQ5E("Q5E");
                //Toast.makeText(getActivity(), "Physical Touch", Toast.LENGTH_LONG).show();
            }
        });

        Q5C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5FragmentListener.calledQ5C("Q5C");
                //Toast.makeText(getActivity(), "Receiving Gifts", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveyQ5fragment.surveyQ5FragmentListener) {
            q5FragmentListener = (surveyQ5fragment.surveyQ5FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyQ5FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}