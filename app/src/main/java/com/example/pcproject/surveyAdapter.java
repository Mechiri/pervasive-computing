package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
    An Adapter to place the proper elements into the proper components in each of the
    love language survey question fragments
 */

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

    //get's the current surveyItem position
    @Override
    public void onBindViewHolder(@NonNull surveyAdapter.surveyViewHolder holder, int position) {
        holder.setSurveyData(surveyItems.get(position));
    }

    //Returns the total number of surveyItems
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

        //Sets the proper information to the components of each love language survey question fragment
        void setSurveyData(surveyItem surveyItem)
        {
            question.setText(surveyItem.getQuestion());
            choice1.setText(surveyItem.getChoice1());
            choice2.setText(surveyItem.getChoice2());
        }
    }

}
