package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import com.google.gson.internal.$Gson$Preconditions;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class partnerEventRecyclerViewAdapter extends RecyclerView.Adapter<partnerEventRecyclerViewAdapter.Viewholder> {

    private List<partnerEventRecyclerViewItem> partnerEvents;

    public partnerEventRecyclerViewAdapter(List<partnerEventRecyclerViewItem> partnerEvents)
    {
        this.partnerEvents = partnerEvents;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_event_recycler_view_layout, parent, false);
        return new partnerEventRecyclerViewAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        int res = partnerEvents.get(position).getEventImg();
        String eventTitle = partnerEvents.get(position).getEventTitle();
        String eventDate = partnerEvents.get(position).getEventDate();
        Integer physicalTouchStatus = partnerEvents.get(position).getPhysicalTouch();
        Integer wordsOfAffStatus = partnerEvents.get(position).getWordsOfAff();
        Integer giftsStatus = partnerEvents.get(position).getGifts();
        Integer actsOfServiceStatus = partnerEvents.get(position).getActOfService();
        Integer qualityTimeStatus = partnerEvents.get(position).getQualityTime();
        holder.setItem(res, eventTitle, eventDate, physicalTouchStatus, wordsOfAffStatus, giftsStatus, actsOfServiceStatus, qualityTimeStatus);
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

        public Viewholder(@NonNull View itemView) {
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

            physicalTouch.setEnabled(false);
            wordsOfAff.setEnabled(false);
            gifts.setEnabled(false);
            actsOfService.setEnabled(false);
            qualityTime.setEnabled(false);
        }

        private void setItem(int eventPicIndex,
                             String title,
                             String date,
                             Integer PTstatus,
                             Integer WOAstatus,
                             Integer Gstatus,
                             Integer AOSstatus,
                             Integer QTstatus)
        {
            eventPic.setImageResource(eventPicIndex);
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
