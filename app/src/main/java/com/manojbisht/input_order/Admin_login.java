package com.manojbisht.input_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_login extends AppCompatActivity {

    private EditText logInUsernameTextView;
    private EditText logInPassword;
    private Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        logInUsernameTextView = findViewById(R.id.logInUsernameTextView);
        logInPassword = findViewById(R.id.logInPassword);
    }

    public void adminLog(View view){
        String uName = logInUsernameTextView.getText().toString();
        String pass = logInPassword.getText().toString();
        if (uName.equals("manojbisht")&& pass.equals("123")){
            Intent intent = new Intent(this,Admin.class);
            startActivity(intent);
            finish();
        }
    }
}
