package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class surveyAdapter extends RecyclerView.Adapter<surveyAdapter.surveyViewHolder>{

    private List<surveyItem> surveyItems;

    public surveyAdapter(List<surveyItem> surveyItems) {
        this.surveyItems = surveyItems;
    }

    @NonNull
    @Override
    public surveyAdapter.surveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new surveyAdapter.surveyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.lovelanguagelayoutcontainer, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull surveyAdapter.surveyViewHolder holder, int position) {
        holder.setSurveyData(surveyItems.get(position));
    }

    @Override
    public int getItemCount() {
        return surveyItems.size();
    }

    class surveyViewHolder extends RecyclerView.ViewHolder{
        private TextView question;
        private Button choice1;
        private Button choice2;

        surveyViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            choice1 = itemView.findViewById(R.id.choice1);
            choice2 = itemView.findViewById(R.id.choice2);
        }

        void setSurveyData(surveyItem surveyItem)
        {
            question.setText(surveyItem.getQuestion());
            choice1.setText(surveyItem.getChoice1());
            choice2.setText(surveyItem.getChoice2());
        }
    }

}
