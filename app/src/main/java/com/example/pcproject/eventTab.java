package com.example.pcproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class eventTab extends Fragment {

    private static final String TAG = "eventTab";

    private Context context;
    private RecyclerView rView;
    List<partnerEventRecyclerViewItem> partnerEvents;
    private Button addPartnerEventB;
    private String partnerProfileName;

    public eventTab(Context context, String partnerProfileName) {
        // Required empty public constructor
        this.context = context;
        this.partnerProfileName = partnerProfileName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_tab, container, false);
        rView = v.findViewById(R.id.partnerEventRecyclerView);
        partnerEventRecyclerViewAdapter a = new partnerEventRecyclerViewAdapter(partnerEvents);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(layout);
        rView.setAdapter(a);

        addPartnerEventB = v.findViewById(R.id.addPartnerEventB);

        addPartnerEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventForm.class);
                intent.putExtra("ProfileName", partnerProfileName);
                Log.d(TAG, "Partner Profile Name: "+partnerProfileName+" coming.........1");
                startActivity(intent);
            }
        });
        /*rView.setAdapter(a);
        a.notifyDataSetChanged();*/

        /*((RecyclerView) v.findViewById(R.id.partnerEventRecyclerView)).setAdapter(a);
        a.notifyDataSetChanged();*/

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        partnerEvents = new ArrayList<>();
        partnerEvents.add(new partnerEventRecyclerViewItem(
                "Test Event 1",
                "11/14/2020",
                R.drawable.loading,
                80,
                20,
                50,
                30,
                90));
        partnerEvents.add(new partnerEventRecyclerViewItem(
                "Test Event 2",
                "11/16/2020",
                R.drawable.loading,
                20,
                50,
                5,
                70,
                85));
    }
}