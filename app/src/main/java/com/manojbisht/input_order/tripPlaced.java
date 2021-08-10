package com.manojbisht.input_order;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manojbisht.input_order.Adapter.TripRecyclerViewAdapter;
import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.data.TripDataBase;
import com.manojbisht.input_order.model.Trip;
import com.manojbisht.input_order.model.User;

import java.util.List;

public class tripPlaced extends AppCompatActivity {
    private String user_userName;
    private RecyclerView tripRecyclerView;
    private TripRecyclerViewAdapter tripRecyclerAdapter;
    private TripDataBase db;
    private TextView setting_textView;
    private TextView profile_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_placed);

        //taking data from main activity

        String userName = getIntent().getExtras().getString("userName");
        user_userName = userName;
        RegisterDataBase rDB = new RegisterDataBase(this);
        User user = rDB.getUser(userName);

//        //setting_textView font-Awesome code
//        setting_textView = findViewById(R.id.setting_textView);
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
//        setting_textView.setTypeface(font);
//        setting_textView.setText("\uf013");

        //profile_textView font-Awesome code
        profile_textView = findViewById(R.id.profile_textView);
        Typeface profile = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
        profile_textView.setTypeface(profile);
        profile_textView.setText("\uf2bd");

        db = new TripDataBase(this);

        List<Trip> tripList = db.getAllTrip();

        tripRecyclerView = findViewById(R.id.tripRecyclerView);
        tripRecyclerView.setHasFixedSize(true);
        tripRecyclerView.setHasFixedSize(true);
        tripRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripRecyclerAdapter = new TripRecyclerViewAdapter(this, tripList,user_userName);
        tripRecyclerView.setAdapter(tripRecyclerAdapter);
    }

//    //code to open settings activity
//    public void openSettings(View view){
//        Intent intent = new Intent(this, setting.class);
//        startActivity(intent);
//    }


    //code to open profile activity
    public void goToProfile(View view){
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("userName",user_userName);
        startActivity(intent);
    }
}
