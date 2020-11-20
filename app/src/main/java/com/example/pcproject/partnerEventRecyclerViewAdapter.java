package com.example.pcproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import com.google.gson.internal.$Gson$Preconditions;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class partnerEventRecyclerViewAdapter extends RecyclerView.Adapter<partnerEventRecyclerViewAdapter.Viewholder> {

    private static final String TAG = "partnerEventRecyclerVie";
    private List<partnerEventRecyclerViewItem> partnerEvents;
    private Context context;

    public partnerEventRecyclerViewAdapter(List<partnerEventRecyclerViewItem> partnerEvents, Context context)
    {
        this.partnerEvents = partnerEvents;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_event_recycler_view_layout, parent, false);
        return new partnerEventRecyclerViewAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final Bitmap res = partnerEvents.get(position).getEventImg();
        final String eventTitle = partnerEvents.get(position).getEventTitle();
        final String eventDate = partnerEvents.get(position).getEventDate();
        final Integer physicalTouchStatus = partnerEvents.get(position).getPhysicalTouch();
        final Integer wordsOfAffStatus = partnerEvents.get(position).getWordsOfAff();
        final Integer giftsStatus = partnerEvents.get(position).getGifts();
        final Integer actsOfServiceStatus = partnerEvents.get(position).getActOfService();
        final Integer qualityTimeStatus = partnerEvents.get(position).getQualityTime();
        final String eventType = partnerEvents.get(position).getEventType();
        final String traitsLearned = partnerEvents.get(position).getTraitsLearned();
        final String talkAbout = partnerEvents.get(position).getTalkAbout();
        final String youReallyLiked = partnerEvents.get(position).getYouReallyLiked();
        final String youDidNotLiked = partnerEvents.get(position).getYouDidNotLiked();
        final String partnerProfileName = partnerEvents.get(position).getPartnerProfileName();
        final String eventName = partnerEvents.get(position).getParentName();
        holder.setItem(res, eventTitle, eventDate, physicalTouchStatus, wordsOfAffStatus, giftsStatus, actsOfServiceStatus, qualityTimeStatus);

        final partnerEventRecyclerViewAdapter.Viewholder viewHolder = holder;
        viewHolder.moreInfoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, partnerEvents.get(0).getEventTitle());
                Intent intent = new Intent(context, EventDetail.class);
                intent.putExtra("partnerProfileName", partnerProfileName);
                intent.putExtra("eventName", eventName);
                intent.putExtra("eventType", eventType);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return partnerEvents.size();
    }

    class Viewholder extends RecyclerView.ViewHolder
    {
        private TextView eventTitle;
        private TextView eventDate;
        private ImageView eventPic;
        private SeekBar physicalTouch;
        private SeekBar wordsOfAff;
        private SeekBar gifts;
        private SeekBar actsOfService;
        private SeekBar qualityTime;

        //Note sure where to set an onClickListener for this button, but at least it exists
        private Button moreInfoB;

        public Viewholder(@NonNull final View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.partnerEventTitle);
            eventDate = itemView.findViewById(R.id.partnerEventDate);
            eventPic = itemView.findViewById(R.id.eventPic);
            physicalTouch = itemView.findViewById(R.id.physicalTouchSeekBar);

            wordsOfAff = itemView.findViewById(R.id.wordsOfAffirSeekBar);
            gifts = itemView.findViewById(R.id.giftsSeekBar);
            actsOfService = itemView.findViewById(R.id.actsOfServiceSeekBar);
            qualityTime = itemView.findViewById(R.id.qualityTimeSeekBar);

            moreInfoB = itemView.findViewById(R.id.moreInfoB);

            /*
            moreInfoB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, partnerEvents.get(0).getEventTitle());
                    //Intent i = new Intent(context, EventDetail.class);
                    //context.startActivity(i);
                }
            });
             */

            physicalTouch.setEnabled(false);
            wordsOfAff.setEnabled(false);
            gifts.setEnabled(false);
            actsOfService.setEnabled(false);
            qualityTime.setEnabled(false);
        }

        private void setItem(Bitmap eventPicIndex,
                             String title,
                             String date,
                             Integer PTstatus,
                             Integer WOAstatus,
                             Integer Gstatus,
                             Integer AOSstatus,
                             Integer QTstatus)
        {
            eventPic.setImageBitmap(eventPicIndex);
            eventTitle.setText(title);
            eventDate.setText(date);
            physicalTouch.setProgress(PTstatus);
            wordsOfAff.setProgress(WOAstatus);
            gifts.setProgress(Gstatus);
            actsOfService.setProgress(AOSstatus);
            qualityTime.setProgress(QTstatus);
        }
    }
}
