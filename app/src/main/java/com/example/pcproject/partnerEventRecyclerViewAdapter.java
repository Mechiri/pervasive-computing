package com.example.pcproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class partnerEventRecyclerViewAdapter extends RecyclerView.Adapter<partnerEventRecyclerViewAdapter.Viewholder> {

    class Viewholder extends RecyclerView.ViewHolder
    {
        private TextView eventTitle;
        private TextView eventDate;
        private ImageView eventPic;
        private SeekBar physicalTouch;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.partnerEventTitle);
            eventDate = itemView.findViewById(R.id.partnerEventDate);
            eventPic = itemView.findViewById(R.id.partnerEventPic);
            physicalTouch = itemView.findViewById(R.id.physicalTouchSeekBar);

            physicalTouch.setEnabled(false);
        }

        private void setItem(int eventPicIndex, String title, String date, Integer status)
        {
            eventPic.setImageResource(eventPicIndex);
            eventTitle.setText(title);
            eventDate.setText(date);
            physicalTouch.setProgress(status);
        }
    }

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
        holder.setItem(res, eventTitle, eventDate, physicalTouchStatus);
    }

    @Override
    public int getItemCount() {
        return partnerEvents.size();
    }
}
