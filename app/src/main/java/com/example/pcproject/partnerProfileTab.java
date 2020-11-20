package com.example.pcproject;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class partnerProfileTab extends Fragment {

    private TextView partnerName;
    private TextView partnerBirthday;
    private TextView partnerOccupation;

    private TextView partnerIndicator;
    private Integer numIndicator; //testing the average experience color changing

    private ProgressBar PT;
    private ProgressBar WOA;
    private ProgressBar G;
    private ProgressBar AOS;
    private ProgressBar QT;
    private Bitmap profileImg; //This should be for partner profile image

    public partnerProfileTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_partner_profile_tab, container, false);

        partnerName = v.findViewById(R.id.partnerNameTab);
        partnerBirthday = v.findViewById(R.id.partnerBirthday);
        partnerOccupation = v.findViewById(R.id.partnerOccupation);
        partnerIndicator = v.findViewById(R.id.partnerIndicator);

        //testing
        numIndicator = 20;
        if(numIndicator <= 33)
        {
            partnerIndicator.setBackgroundResource(R.drawable.partner_bad_indicator);
        }
        else if(numIndicator >= 34 && numIndicator <= 66)
        {
            partnerIndicator.setBackgroundResource(R.drawable.partner_ok_indicator);
        }
        else if(numIndicator >= 67 && numIndicator <= 100)
        {
            partnerIndicator.setBackgroundResource(R.drawable.partner_good_indicator);
        }

        PT = v.findViewById(R.id.PTprogressBar);
        WOA = v.findViewById(R.id.WOAprogressBar);
        G = v.findViewById(R.id.GprogressBar);
        AOS = v.findViewById(R.id.AOSprogressBar);
        QT = v.findViewById(R.id.QTprogressBar);

        //how to set the progressBars
        PT.setProgress(60);
        WOA.setProgress(70);
        G.setProgress(10);
        AOS.setProgress(50);
        QT.setProgress(90);

        return v;
    }
}