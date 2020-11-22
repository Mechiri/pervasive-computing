package com.example.pcproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/*
    partnerRecyclerViewAdapter connects and places the proper information in ViewHolder for Partner Page
 */

public class partnerRecyclerViewAdapter extends RecyclerView.Adapter<partnerRecyclerViewAdapter.Viewholder> {

    Context context;
    private List<partnerRecyclerViewItem> partners;
    Dialog myDialog;

    //Instantiate partners List and context
    public partnerRecyclerViewAdapter(Context context, List<partnerRecyclerViewItem> partners)
    {
        this.context = context;
        this.partners = partners;
    }

    @NonNull
    @Override
    public partnerRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_recycler_view_layout, parent, false);
        return new Viewholder(view);
    }

    //get's the position of ViewHolder
    @Override
    public void onBindViewHolder(@NonNull partnerRecyclerViewAdapter.Viewholder holder, int position) {
        Bitmap res = partners.get(position).getImg();
        String partnerName = partners.get(position).getPartnerName();
        String numOfEvents = partners.get(position).getNumOfEvents();
        String partnerStatus = partners.get(position).getPartnerStatus();
        final String profileName = partners.get(position).getProfileName();
        holder.setItem(res, partnerName,numOfEvents,partnerStatus);

        final partnerRecyclerViewAdapter.Viewholder viewholder = holder;
        holder.partnerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, partnerPage.class);
                intent.putExtra("ProfileName", profileName);
                context.startActivity(intent);
                Toast.makeText(context, "Test CLick: "+String.valueOf(viewholder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Returns the number of partners
    @Override
    public int getItemCount() {
        return partners.size();
    }

    //Creates Partner ViewHolder for Recycler View
    class Viewholder extends RecyclerView.ViewHolder
    {
        private TextView partnerName;
        private TextView numOfEvents;
        private TextView partnerStatus;
        private ConstraintLayout partnerProfile;
        CircularImageView partnerProfilePic;

        //Find's the appropriate elements from xml files
        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            partnerProfile = itemView.findViewById(R.id.partnerProfiles);
            partnerName = itemView.findViewById(R.id.partnerNameLandingPage);
            numOfEvents = itemView.findViewById(R.id.numberOfEvents);
            partnerStatus = itemView.findViewById(R.id.partnerStatus);
            partnerProfilePic = itemView.findViewById(R.id.partnerProfilePic);
        }

        //Set's the proper partner text
        private void setItem(Bitmap partnerPicIndex, String name, String numEvents, String status)
        {
            partnerProfilePic.setImageBitmap(partnerPicIndex);
            partnerName.setText(name);
            numOfEvents.setText(numEvents);
            partnerStatus.setText(status);
        }
    }

}
