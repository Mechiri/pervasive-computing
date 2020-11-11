package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class partnerRecyclerViewAdapter extends RecyclerView.Adapter<partnerRecyclerViewAdapter.Viewholder> {

    class Viewholder extends RecyclerView.ViewHolder
    {
        private TextView partnerName;
        private TextView numOfEvents;
        private TextView partnerStatus;
        CircularImageView partnerProfilePic;

        public Viewholder(@NonNull View itemView)
        {
            super(itemView);

            partnerName = itemView.findViewById(R.id.partnerNameLandingPage);
            numOfEvents = itemView.findViewById(R.id.numberOfEvents);
            partnerStatus = itemView.findViewById(R.id.partnerStatus);
            partnerProfilePic = itemView.findViewById(R.id.partnerProfilePic);
        }

        private void setItem(int partnerPicIndex, String name, String numEvents, String status)
        {
            partnerProfilePic.setImageResource(partnerPicIndex);
            partnerName.setText(name);
            numOfEvents.setText(numEvents);
            partnerStatus.setText(status);
        }
    }

    private List<partnerRecyclerViewItem> partners;

    public partnerRecyclerViewAdapter(List<partnerRecyclerViewItem> partners)
    {
        this.partners = partners;
    }

    @NonNull
    @Override
    public partnerRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_recycler_view_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull partnerRecyclerViewAdapter.Viewholder holder, int position) {
        int res = partners.get(position).getImg();
        String partnerName = partners.get(position).getPartnerName();
        String numOfEvents = partners.get(position).getNumOfEvents();
        String partnerStatus = partners.get(position).getPartnerStatus();
        holder.setItem(res, partnerName,numOfEvents,partnerStatus);
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

}
