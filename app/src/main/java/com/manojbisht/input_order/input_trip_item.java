package com.manojbisht.input_order;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.data.TripDataBase;
import com.manojbisht.input_order.model.Trip;
import com.manojbisht.input_order.model.User;

public class input_trip_item extends AppCompatActivity {
    private TextView inputOrderImageURL;
    private TextView inputOrderNumber;
    private TextView inputOrderDetail ;
    private TextView inputOrderDesc;
    private TextView inputOrderPricing;
    private Button inputOrderButton;
    private TextView setting_textView;
    private TextView profile_textView;
    private String user_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_trip_item);

        String userName = getIntent().getExtras().getString("userName");
        user_userName = userName;
        RegisterDataBase rDB = new RegisterDataBase(this);
        User user = rDB.getUser(userName);

//       // setting_textView font-Awesome code
//        setting_textView = findViewById(R.id.setting_textView);
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
//        setting_textView.setTypeface(font);
//        setting_textView.setText("\uf013");

        //profile_textView font-Awesome code
        profile_textView = findViewById(R.id.profile_textView);
        Typeface profile = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
        profile_textView.setTypeface(profile);
        profile_textView.setText("\uf2bd");

        inputOrderImageURL = findViewById(R.id.inputOrderImageURL);
        inputOrderNumber = findViewById(R.id.inputOrderNumber);
        inputOrderDetail = findViewById(R.id.inputOrderDetail);
        inputOrderDesc = findViewById(R.id.inputOrderDesc);
        inputOrderPricing = findViewById(R.id.inputOrderPricing);
        inputOrderButton = findViewById(R.id.inputOrderButton);
    }

    public void putOrder(View view){
        TripDataBase db = new TripDataBase(this);
        Trip trip = new Trip();
        trip.setInputTripNumber(inputOrderNumber.getText().toString());
        trip.setInputTripImage(inputOrderImageURL.getText().toString());
        trip.setInputTripType(inputOrderDetail.getText().toString());
        trip.setInputTripDetail(inputOrderDesc.getText().toString());
        trip.setGetInputTripPrice(inputOrderPricing.getText().toString());
        db.inputTrip(trip);
        Intent intent = new Intent(this,tripPlaced.class);
        intent.putExtra("userName",user_userName);
        startActivity(intent);
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
