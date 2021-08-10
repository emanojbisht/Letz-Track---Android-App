package com.manojbisht.input_order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manojbisht.input_order.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private static final String TAG = "track_home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void planTrip(View view){
        Intent intent = new Intent(this, tripPlaced.class);
        intent.putExtra("userName","manojbisht");
        startActivity(intent);
    }

    public void inputOrder(View view){
        Intent intent = new Intent(this, input_trip_item.class);
        intent.putExtra("userName","manojbisht");
        startActivity(intent);
    }

    private void initImageBitMap(){
        mImageUrls.add("https://emanojbisht.github.io/Letz-Track/images/mountain.png");
        mNames.add("Peaks");

        mImageUrls.add("https://emanojbisht.github.io/Letz-Track/images/village.png");
        mNames.add("Villages");

        mImageUrls.add("https://emanojbisht.github.io/Letz-Track/images/culture.png");
        mNames.add("Culture");


        mImageUrls.add("https://emanojbisht.github.io/Letz-Track/images/adventurer.png");
        mNames.add("Adventure");

        mImageUrls.add("https://emanojbisht.github.io/Letz-Track/images/tour.png");
        mNames.add("Tour");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.explore_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter =  new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
}
