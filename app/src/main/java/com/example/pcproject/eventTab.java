package com.example.pcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class eventTab extends Fragment {

    private RecyclerView rView;
    List<partnerEventRecyclerViewItem> partnerEvents;

    public eventTab() {
        // Required empty public constructor
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