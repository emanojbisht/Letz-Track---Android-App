package com.manojbisht.input_order;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.manojbisht.input_order.data.OrderDataBase;
import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.model.Order;
import com.manojbisht.input_order.model.User;

import java.util.Calendar;

public class planTrip extends AppCompatActivity {
    private TextView setting_textView;
    private TextView profile_textView;
    private ImageView planTripTourImage;
    private EditText planTripTourName;
    private EditText planTripTourEmail;
    private EditText planTripTourPhoneNumber;
    private EditText planTripTourDAte;
    private EditText planTripTourCFee;
    private EditText planTripTourPrice;
    private EditText planTripTourttAmount;
    private DatePickerDialog datePickerDialogue;
    private Button planTripTourButton;
    private Button tourDate;
    private String user_userName;
    private String number;
    private String userName;
    private String detail;
    private String price;
    private String image;
    private String todayDateSelect;
    private String tripDateSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //taking data from main activity

        number = getIntent().getExtras().getString("number");
        detail = getIntent().getExtras().getString("detail");
        price = getIntent().getExtras().getString("price");
        image = getIntent().getExtras().getString("image");
        //taking data from main activity

        userName = getIntent().getExtras().getString("userName");
        user_userName = userName;
        RegisterDataBase db = new RegisterDataBase(this);
        User user = db.getUser(userName);

        planTripTourImage = findViewById(R.id.planTripTourImage);
        planTripTourName = findViewById(R.id.planTripTourName);
        planTripTourEmail = findViewById(R.id.planTripTourEmail);
        planTripTourPhoneNumber = findViewById(R.id.planTripTourPhoneNumber);
        planTripTourDAte = findViewById(R.id.planTripTourDAte);
        planTripTourPrice = findViewById(R.id.planTripTourPrice);
        planTripTourCFee = findViewById(R.id.planTripTourCFee);
        planTripTourttAmount = findViewById(R.id.planTripTourttAmount);
        tourDate = findViewById(R.id.tourDate);
        planTripTourButton = findViewById(R.id.planTripTourButton);

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
        initDatePick();
        todayDateSelect = getTodayDate();
        tourDate.setText(getTodayDate());
        display();
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day,month,year);
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

    public void display(){
        Glide.with(this).load(image).into(planTripTourImage);
        planTripTourName.setText(detail);
        planTripTourName.setEnabled(false);
        planTripTourPrice.setText("$"+price);
        planTripTourPrice.setEnabled(false);
        planTripTourCFee.setEnabled(false);
        planTripTourttAmount.setText("$"+price);
        planTripTourttAmount.setEnabled(false);

    }

    public void initDatePick(){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                tripDateSelect = date;
                tourDate.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialogue = new DatePickerDialog(this,style,onDateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day +" " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JLY";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEp";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view){
        datePickerDialogue.show();
    }

    public void placeMyTrip(View view){
        Order order = new Order();
        String email = planTripTourEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
// onClick of button perform this simplest code.

        String phone = "+91" + planTripTourPhoneNumber.getText().toString();
        String phonePattern = "^\\+[0-9]{10,13}$";

        if (email.equals("") || phone.equals("")){
            Snackbar.make(view, "Please fill the required information !", Snackbar.LENGTH_SHORT).show();
        }else {

            if (email.matches(emailPattern) && phone.matches(phonePattern))
            {
                order.setOrderNumber(number);
                order.setOrderUserNAme(user_userName);
                order.setOrderEmail(planTripTourEmail.getText().toString());
                order.setOrderPhoneNumber(planTripTourPhoneNumber.getText().toString());
                order.setOrderTripDate(tripDateSelect);
                order.setOrdercurrentDate(todayDateSelect);
                Snackbar.make(view, "We are planning your Trip.....!", Snackbar.LENGTH_SHORT).show();
                OrderDataBase db = new OrderDataBase(this);
                db.inputOrder(order);
                Intent intent = new Intent(this,track_home.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid email address or phone number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class Admin_login extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_login);
        }
    }
}
