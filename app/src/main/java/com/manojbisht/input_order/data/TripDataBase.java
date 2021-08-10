package com.manojbisht.input_order.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.manojbisht.input_order.model.Trip;
import com.manojbisht.input_order.util.TripUtil;

import java.util.ArrayList;
import java.util.List;

public class TripDataBase extends SQLiteOpenHelper {

    public TripDataBase(@Nullable Context context) {
        super(context, TripUtil.DATABASE_NAME, null, TripUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TRIP = " CREATE TABLE " + TripUtil.TABLE_TRIP
                + " ( " + TripUtil.trip_number + " TEXT PRIMARY KEY,"
                + TripUtil.trip_image + " TEXT, "
                + TripUtil.trip_type + " TEXT, "
                + TripUtil.trip_desc + " TEXT, "
                + TripUtil.trip_price + " TEXT ); ";
        db.execSQL(CREATE_TABLE_TRIP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TripUtil.TABLE_TRIP);
        onCreate(db);
    }

    //inputting a trip to db;
    public void inputTrip(Trip trip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TripUtil.trip_number, trip.getInputTripNumber());
        values.put(TripUtil.trip_image, trip.getInputTripImage());
        values.put(TripUtil.trip_type, trip.getInputTripType());
        values.put(TripUtil.trip_desc, trip.getInputTripDetail());
        values.put(TripUtil.trip_price, trip.getGetInputTripPrice());
        db.insert(TripUtil.TABLE_TRIP,null,values);
        db.close();

    }

    //get all trip detail
    public List<Trip> getAllTrip(){
        List<Trip> tripList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM "+ TripUtil.TABLE_TRIP;
        Cursor cursor = db.rawQuery(selectAll, null);
        if(cursor.moveToFirst()){
            do{
                Trip trip = new Trip();
                trip.setInputTripNumber(cursor.getString(0));
                trip.setInputTripImage(cursor.getString(1));
                trip.setInputTripType(cursor.getString(2));
                trip.setInputTripDetail(cursor.getString(3));
                trip.setGetInputTripPrice(cursor.getString(4));
                tripList.add(trip);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tripList;
    }

}
