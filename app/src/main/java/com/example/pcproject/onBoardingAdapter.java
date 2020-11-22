package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
   onBoardingAdapter connects and places the proper information in ViewHolder for start page
 */

public class onBoardingAdapter extends RecyclerView.Adapter<onBoardingAdapter.onBoardingViewHolder>{
    private List<onBoardingItem> onBoardingItems;

    //Instantiate partners List
    public onBoardingAdapter(List<onBoardingItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public onBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new onBoardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.onboardinglayoutcontainer, parent, false
                )
        );
    }

    //get's the position of ViewHolder
    @Override
    public void onBindViewHolder(@NonNull onBoardingViewHolder holder, int position) {
        holder.setOnboardingData(onBoardingItems.get(position));
    }

    //Returns the number of onBoardingItems
    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    //Creates onBoarding ViewHolder for Recycler View
    class onBoardingViewHolder extends RecyclerView.ViewHolder{
        private TextView initial;
        private TextView second;

        //Find's the appropriate elements from xml files
        onBoardingViewHolder(@NonNull View itemView) {
            super(itemView);

            initial = itemView.findViewById(R.id.initalText);
            second = itemView.findViewById(R.id.secondText);
        }

        //Set's the proper partner text
        void setOnboardingData(onBoardingItem onBoardingItem)
        {
            initial.setText(onBoardingItem.getInitial());
            second.setText(onBoardingItem.getSecond());
        }
    }
}
