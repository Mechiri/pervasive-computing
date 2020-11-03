package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class onBoardingAdapter extends RecyclerView.Adapter<onBoardingAdapter.onBoardingViewHolder>{
    private List<onBoardingItem> onBoardingItems;

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

    @Override
    public void onBindViewHolder(@NonNull onBoardingViewHolder holder, int position) {
        holder.setOnboardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class onBoardingViewHolder extends RecyclerView.ViewHolder{
        private TextView initial;
        private TextView second;

        onBoardingViewHolder(@NonNull View itemView) {
            super(itemView);

            initial = itemView.findViewById(R.id.initalText);
            second = itemView.findViewById(R.id.secondText);
        }

        void setOnboardingData(onBoardingItem onBoardingItem)
        {
            initial.setText(onBoardingItem.getInitial());
            second.setText(onBoardingItem.getSecond());
        }
    }
}
