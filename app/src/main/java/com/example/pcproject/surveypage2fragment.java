package com.example.pcproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/*
    Initial Survey Page 2 asking user's what they are looking for in a relationship
 */

public class surveypage2fragment extends Fragment {

    private surveyPage2FragmentListener page2FragmentListener;
    private Button nextB;
    private Button marriageB;
    private Button relationshipB;
    private Button casualB;
    private Button hookUpB;

    public surveypage2fragment() {
        // Required empty public constructor
    }

    public interface surveyPage2FragmentListener
    {
        void onInputPage2Sent(String mString);
        void onInputPage2Next();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_surveypage2fragment, container, false);
        nextB = v.findViewById(R.id.nextSurveyB2);
        marriageB = v.findViewById(R.id.marriageB);
        relationshipB = v.findViewById(R.id.relationshipB);
        casualB = v.findViewById(R.id.casualB);
        hookUpB = v.findViewById(R.id.hookUpB);

        //Starts the next fragment page in the initial survey
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2FragmentListener.onInputPage2Next();
            }
        });

        //calls the proper function to record user's choice
        marriageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2FragmentListener.onInputPage2Sent("Marriage");
                //Toast.makeText(getActivity(), "Marriage Selected", Toast.LENGTH_SHORT).show();
            }
        });

        //calls the proper function to record user's choice
        relationshipB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2FragmentListener.onInputPage2Sent("Relationship");
                //Toast.makeText(getActivity(), "Relationship Selected", Toast.LENGTH_SHORT).show();
            }
        });

        //calls the proper function to record user's choice
        casualB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2FragmentListener.onInputPage2Sent("Casual");
                //Toast.makeText(getActivity(), "Casual Selected", Toast.LENGTH_SHORT).show();
            }
        });

        //calls the proper function to record user's choice
        hookUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2FragmentListener.onInputPage2Sent("Hookup");
                //Toast.makeText(getActivity(), "Hook Up Selected", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

    //Attaches the appropriate fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof surveypage2fragment.surveyPage2FragmentListener) {
            page2FragmentListener = (surveypage2fragment.surveyPage2FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement surveyPage2FragmentListener");
        }
    }

    //Removes the fragment from UI
    @Override
    public void onDetach() {
        super.onDetach();
    }
}