package com.manojbisht.input_order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class setting extends AppCompatActivity {

    private TextView icon_about;
    private TextView logOut;
    public static final String MESSAGE_ID = "message_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        logOut = findViewById(R.id.logOut);

        icon_about = findViewById(R.id.icon_about);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
        icon_about.setTypeface(font);
        icon_about.setText("\uf05a");

    }


    public void logOut(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        finishAffinity();
        startActivity(new Intent(setting.this,MainActivity.class));

    }
}
