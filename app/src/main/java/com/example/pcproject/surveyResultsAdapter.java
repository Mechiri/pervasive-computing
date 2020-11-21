package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
    surveyResultsAdapter connects and places the proper information in ViewHolder for SurveyResult Page
 */

public class surveyResultsAdapter extends RecyclerView.Adapter<surveyResultsAdapter.surveyResultsViewHoler> {

    private List<onBoardingItem> onBoardingItems;

    //Instantiate onBoardingItems List
    public surveyResultsAdapter(List<onBoardingItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public surveyResultsViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new surveyResultsAdapter.surveyResultsViewHoler(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.surveyresultslayoutcontainer, parent, false
                )
        );
    }

    //get's the position of ViewHolder
    @Override
    public void onBindViewHolder(@NonNull surveyResultsViewHoler holder, int position) {
        holder.setSurveyResultsData(onBoardingItems.get(position));
    }

    //Returns the number of items survey results
    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }


    //Creates Survey Result ViewHolder for Recycler View
    class surveyResultsViewHoler extends RecyclerView.ViewHolder
    {
        private TextView loveLanguageName;
        private TextView loveLanguageDescription;

        //Find's the appropriate elements from xml files
        public surveyResultsViewHoler(@NonNull View itemView) {
            super(itemView);
            loveLanguageName = itemView.findViewById(R.id.loveLanguageName);
            loveLanguageDescription = itemView.findViewById(R.id.loveLanguageDescription);
        }

        //Set's the proper love language text
        void setSurveyResultsData(onBoardingItem onBoardingItem)
        {
            loveLanguageName.setText(onBoardingItem.getInitial());
            loveLanguageDescription.setText(onBoardingItem.getSecond());
        }
    }
}
