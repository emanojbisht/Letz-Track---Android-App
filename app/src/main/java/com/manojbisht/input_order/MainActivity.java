package com.manojbisht.input_order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText logInUserName;
    private EditText logInPassword;
    private Button logInButton;
    private Button loginCreateAccount;
    private RegisterDataBase db;
    public static final String MESSAGE_ID = "message_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new RegisterDataBase(MainActivity.this);
        logInUserName = findViewById(R.id.logInUsernameTextView);
        logInPassword = findViewById(R.id.logInPassword);
        logInButton = findViewById(R.id.logInButton);
        loginCreateAccount = findViewById(R.id.logInCreateAccount);


        //checking internet connection
        checkConnection(logInButton);

    }

    public String checkUserInSharedPreference(){
        String userName = "";
        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        userName = getSharedData.getString("userName","");
        return userName;
    }


    // Checking Internet connection of user
    public void checkConnection(View view){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if(null != activeNetwork){
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                String userName = checkUserInSharedPreference();
                if(!userName.equals("")){
                    takeMe(userName);
                }else{
                    logInUser();
                }
            }
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                String userName = checkUserInSharedPreference();
                if(!userName.equals("")){
                    takeMe(userName);
                }else{
                    logInUser();
                }
            }
        }else{
            Snackbar.make(view, "No Internet Connection !!! ", Snackbar.LENGTH_SHORT).show();
        }
    }

    //code to move to track_home
    public void takeMe(String userName){
        Intent intent = new Intent(MainActivity.this,track_home.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
        finish();
    }

    //login in user
    public void logInUser(){
        List<User> u = db.getAllUsers();
        for(User i : u){
            Log.d("Regq", "username : "+i.getrUserName()+" Password : "+i.getrPassword());
        }
        Log.d("Regq", "onCreate: "+db.getCount());


        //button to login
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = logInUserName.getText().toString().trim();
                String password = logInPassword.getText().toString().trim();
                if(userName.equals("") || password.equals("")){
                    Snackbar.make(view, "field cannot be blank!", Snackbar.LENGTH_SHORT).show();
                }else{
                    User user = db.getUser(userName);
                    if(user == null){
                        Toast.makeText(MainActivity.this, userName+" no account exist!", Toast.LENGTH_SHORT).show();
                    }
                    else if (user.getrPassword().equals(password)){

                        //Storing login details in shared preference
                        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName",""+userName);
                        editor.apply();

                        //moving to track home activity;
                        takeMe(userName);
                    }else{
                        Toast.makeText(MainActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //button to register user in the app
        loginCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void AdminLogin(View view){
        Intent intent = new Intent(MainActivity.this, Admin_login.class);
        startActivity(intent);
        finish();
    }


}
