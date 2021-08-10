package com.manojbisht.input_order;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.manojbisht.input_order.data.RegisterDataBase;

public class login_details extends AppCompatActivity {
    private ListView loginListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);

        loginListView = findViewById(R.id.loginListView);
        RegisterDataBase db = new RegisterDataBase(this);


    }
}
