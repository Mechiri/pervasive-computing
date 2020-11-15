package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends AppCompatActivity {

    private RecyclerView rView;

    private Button userProfileB;
    private Button addPartnerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        userProfileB = findViewById(R.id.userProfileB);
        addPartnerB = findViewById(R.id.addPartnerB);

        userProfileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, userProfile.class));
            }
        });

        addPartnerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, addPartnerForm.class));
            }
        });

        rView = findViewById(R.id.recyclerViewLanding);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        rView.setLayoutManager(layout);

        List<partnerRecyclerViewItem> partners = new ArrayList<>();

        partners.add(new partnerRecyclerViewItem(R.drawable.loading, "Testing", "2", "ongoing"));
        partners.add(new partnerRecyclerViewItem(R.drawable.loading, "Testing2", "5", "ongoing"));

        partnerRecyclerViewAdapter a = new partnerRecyclerViewAdapter(partners);
        rView.setAdapter(a);
        a.notifyDataSetChanged();
    }
}