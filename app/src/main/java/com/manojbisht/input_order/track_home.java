package com.manojbisht.input_order;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manojbisht.input_order.Adapter.RecyclerViewAdapter;
import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.data.TripDataBase;
import com.manojbisht.input_order.model.Trip;
import com.manojbisht.input_order.model.User;

import java.util.ArrayList;

public class track_home extends AppCompatActivity {
    private String user_userName;
    private static final String TAG = "track_home";
    private TextView setting_textView;
    private TextView profile_textView;
    private ViewFlipper viewFlipper;
    private ImageView everestBaseCampImageView;
    private ImageView kinnuarImageView;
    private ImageView darmaImageView;
    private Button inputOrder;
    private ImageView planTrip;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private String darmaImage;
    private String nepalImage;
    private String kinnuarImage;
    private TextView dharmaNumber;
    private TextView dharmaTitle;
    private TextView uttarakhand;
    private TextView dharmaPrice;
    private TextView nepalNumber;
    private TextView nepalTitle;
    private TextView nepalPrice;
    private TextView nepal;
    private TextView kinnuarNumber;
    private TextView kinnuarTitle;
    private TextView kinnuarPrice;
    private TextView himachal;
    private TripDataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_home);
        db = new TripDataBase(this);

        inputOrder = findViewById(R.id.inputOrder);
        planTrip = findViewById(R.id.planTrip);
        darmaImage = "https://emanojbisht.github.io/Letz-Track/images/dharma.jpg";
        nepalImage = "https://emanojbisht.github.io/Letz-Track/images/mountEverest.jpg";
        kinnuarImage = "https://emanojbisht.github.io/Letz-Track/images/himcahal.jpg";


        //taking data from main activity

        String userName = getIntent().getExtras().getString("userName");
        user_userName = userName;
        RegisterDataBase db = new RegisterDataBase(track_home.this);
        User user = db.getUser(userName);


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


//        //admin_textView font-Awesome code
//        admin_textView = findViewById(R.id.admin_textView);
//        Typeface admin = Typeface.createFromAsset(getAssets(), "fonts/solid.ttf");
//        admin_textView.setTypeface(admin);
//        admin_textView.setText("\uf502");

        //code for flipping image
        int images[] = {R.drawable.image_one,R.drawable.image_two,R.drawable.image_three,R.drawable.image_four};
        viewFlipper = findViewById(R.id.display_image);
        for(int image : images){
            flipperImages(image);
        }

        //code for horizontal recyclerView;

        initImageBitMap();

        //setting image in destination image view
        everestBaseCampImageView = findViewById(R.id.everestBaseCampImageView);
        kinnuarImageView = findViewById(R.id.kinnuarImageView);
        darmaImageView = findViewById(R.id.darmaImageView);
        dharmaNumber = findViewById(R.id.darmaNumber);
        dharmaTitle = findViewById(R.id.darmaValleyTextView);
        uttarakhand = findViewById(R.id.uttarakhand);
        dharmaPrice = findViewById(R.id.destinationPrice);
        nepalNumber = findViewById(R.id.everestBaseCampNumber);
        nepalTitle = findViewById(R.id.everestBaseCampTextView);
        nepalPrice = findViewById(R.id.destinationPrice1);
        nepal = findViewById(R.id.nepal);
        kinnuarNumber = findViewById(R.id.kinnuarNumber);
        kinnuarTitle = findViewById(R.id.kinnuarTextView);
        kinnuarPrice = findViewById(R.id.destinationPrice2);
        himachal = findViewById(R.id.himachal);

        Glide.with(this).load(darmaImage).into(darmaImageView);
        Glide.with(this).load(nepalImage).into(everestBaseCampImageView);
        Glide.with(this).load(kinnuarImage).into(kinnuarImageView);

        addData("123BMBM78","Darma Valley","Uttarakhand",darmaImage,"340");
        addData("123BMBM79","Everest Base Camp","Nepal",nepalImage,"560");
        addData("123BMBM80","Kinnuar","Himachal Pradesh",kinnuarImage,"210");

        displaydata();
    }

    //flipping image cosde
    public void flipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
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

//    //code to open settings activity
//    public void openSettings(View view){
//        Intent intent = new Intent(track_home.this, setting.class);
//        startActivity(intent);
//    }


    //code to open profile activity
    public void goToProfile(View view){
        Intent intent = new Intent(track_home.this, profile.class);
        intent.putExtra("userName",user_userName);
        startActivity(intent);
    }


    public void inputOrder(View view){
        Intent intent = new Intent(track_home.this, input_trip_item.class);
        intent.putExtra("userName",user_userName);
        startActivity(intent);
    }

    public void planTrip(View view){
        Intent intent = new Intent(track_home.this, tripPlaced.class);
        intent.putExtra("userName",user_userName);
        startActivity(intent);
    }

    public void dharmaClick(View View){
        Intent intent = new Intent(this, planTrip.class);
        intent.putExtra("userName",user_userName);
        intent.putExtra("number",dharmaNumber.getText().toString().substring(1));
        intent.putExtra("detail",dharmaTitle.getText().toString());
        intent.putExtra("price",dharmaPrice.getText().toString().substring(1).substring(1));
        intent.putExtra("image",darmaImage);
        startActivity(intent);
    }

    public void nepalClick(View View){
        Intent intent = new Intent(this, planTrip.class);
        intent.putExtra("userName",user_userName);
        intent.putExtra("number",nepalNumber.getText().toString().substring(1));
        intent.putExtra("detail",nepalTitle.getText().toString());
        intent.putExtra("price",nepalPrice.getText().toString().substring(1).substring(1));
        intent.putExtra("image",nepalImage);
        startActivity(intent);
    }

    public void kinnuarClick(View View){
        Intent intent = new Intent(this, planTrip.class);
        intent.putExtra("userName",user_userName);
        intent.putExtra("number",kinnuarNumber.getText().toString().substring(1));
        intent.putExtra("detail",kinnuarTitle.getText().toString());
        intent.putExtra("price",kinnuarPrice.getText().toString().substring(1).substring(1));
        intent.putExtra("image",kinnuarImage);
        startActivity(intent);
    }

    public void addData(String number,String title,String detail,String link,String price){
        TripDataBase db = new TripDataBase(this);
        Trip trip = new Trip();
        trip.setInputTripNumber(number);
        trip.setInputTripImage(link);
        trip.setInputTripType(detail);
        trip.setInputTripDetail(title);
        trip.setGetInputTripPrice(price);
        db.inputTrip(trip);
    }

    public void displaydata(){
        dharmaNumber.setText("#123BMBM78");
        dharmaTitle.setText("Darma Valley");
        uttarakhand.setText("Uttarakhand");
        dharmaPrice.setText("$340");
        nepalNumber.setText("#123BMBM79");
        nepalTitle.setText("Everest Base Camp");
        nepal.setText("Nepal");
        nepalPrice.setText("$560");
        kinnuarNumber.setText("#123BMBM80");
        kinnuarPrice.setText("$210");
        kinnuarTitle.setText("Kinnuar");
        himachal.setText("Himachal Pradesh");
    }

    public void darmaOpen(View view){
        String url = "https://www.outlookindia.com/outlooktraveller/explore/story/70911/soulful-sojourns-the-serenity-of-darma-valley";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void nepalOpen(View view){
        String url = "https://www.lonelyplanet.com/articles/how-to-trek-to-everest-base-camp";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void kinnuarOpen(View view){
        String url = "https://www.india.com/travel/kinnaur/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
