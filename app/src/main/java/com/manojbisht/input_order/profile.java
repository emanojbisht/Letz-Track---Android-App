package com.manojbisht.input_order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.model.User;

public class profile extends AppCompatActivity {
    private RegisterDataBase db;
    private TextView profile_name;
    private String userName;
    private TextView profile_userName;
    public static final String MESSAGE_ID = "message_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = new RegisterDataBase(profile.this);


        userName = getIntent().getExtras().getString("userName");
        profile_name = findViewById(R.id.profile_name);
        profile_userName = findViewById(R.id.profile_userName);

        User user = db.getUser(userName);
        profile_name.setText(""+user.getrName());
        profile_userName.setText("@"+user.getrUserName());

        TextView instagram =findViewById(R.id.instagram);
        Typeface profile = Typeface.createFromAsset(getAssets(), "fonts/brands.ttf");
        instagram.setTypeface(profile);
        instagram.setText("\uf16d");

        TextView facebook =findViewById(R.id.facebook);
        Typeface f1 = Typeface.createFromAsset(getAssets(), "fonts/brands.ttf");
        facebook.setTypeface(f1);
        facebook.setText("\uf39e");

        TextView mail =findViewById(R.id.mail);
        Typeface f2 = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
        mail.setTypeface(f2);
        mail.setText("\uf2b6");

    }


    public void editProfile(View view){
        Intent intent = new Intent(profile.this,Edit_profile.class);
        startActivity(intent);
    }

    public void openOrders(View view){
        Intent intent = new Intent(this,myTrip.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
    }

    public void logOut(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        finishAffinity();
        startActivity(new Intent(this,MainActivity.class));

    }

    public void instagramOpen(View view){
        String url = "https://www.instagram.com/emanojbisht";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void facebookOpen(View view){
        String url = "https://www.facebook.com/emanojbisht/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void MailOpen(View view){
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"itsslimslimshady.mb@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
        intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
        intent.putExtra(Intent.EXTRA_CC,"itsslimslimshady.mb@gmail.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    public void aboutUsOpen(View view){
        String url = "https://emanojbisht.github.io/myWebsite/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
