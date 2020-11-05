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
import android.widget.EditText;

public class surveypage3fragment extends Fragment {

    private surveyPage3FragmentListener page3FragmentListener;
    private Button nextB;
    private EditText idealTextInput;

    public surveypage3fragment() {
        // Required empty public constructor
    }

    public interface surveyPage3FragmentListener
    {
        void onInputPage3Next(String mString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_surveypage3fragment, container, false);
        nextB = v.findViewById(R.id.nextSurveyB3);
        idealTextInput = v.findViewById(R.id.idealTextInput);

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page3FragmentListener.onInputPage3Next(idealTextInput.getText().toString());
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveypage3fragment.surveyPage3FragmentListener) {
            page3FragmentListener = (surveypage3fragment.surveyPage3FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyPage3FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}