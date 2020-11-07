package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class surveyResultsAdapter extends RecyclerView.Adapter<surveyResultsAdapter.surveyResultsViewHoler> {

    private List<onBoardingItem> onBoardingItems;

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

    @Override
    public void onBindViewHolder(@NonNull surveyResultsViewHoler holder, int position) {
        holder.setSurveyResultsData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }


    class surveyResultsViewHoler extends RecyclerView.ViewHolder
    {
        private TextView loveLanguageName;
        private TextView loveLanguageDescription;
        public surveyResultsViewHoler(@NonNull View itemView) {
            super(itemView);
            loveLanguageName = itemView.findViewById(R.id.loveLanguageName);
            loveLanguageDescription = itemView.findViewById(R.id.loveLanguageDescription);
        }
        void setSurveyResultsData(onBoardingItem onBoardingItem)
        {
            loveLanguageName.setText(onBoardingItem.getInitial());
            loveLanguageDescription.setText(onBoardingItem.getSecond());
        }
    }
}
