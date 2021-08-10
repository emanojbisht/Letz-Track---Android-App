package com.manojbisht.input_order.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.manojbisht.input_order.model.Order;
import com.manojbisht.input_order.util.OrderUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderDataBase extends SQLiteOpenHelper {

    public OrderDataBase(@Nullable Context context) {
        super(context, OrderUtil.DATABASE_NAME, null, OrderUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDER_TRIP = " CREATE TABLE " + OrderUtil.TABLE_TRIP
                + " ( " + OrderUtil.order_number + " TEXT PRIMARY KEY,"
                + OrderUtil.order_userName + " TEXT, "
                + OrderUtil.order_email + " TEXT, "
                + OrderUtil.order_phone + " TEXT, "
                + OrderUtil.order_trip_date + " TEXT, "
                + OrderUtil.order_current_date + " TEXT ); ";
        db.execSQL(CREATE_ORDER_TRIP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + OrderUtil.TABLE_TRIP);
        onCreate(db);
    }

    //inputting a trip to db;
    public void inputOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderUtil.order_number, order.getOrderNumber());
        values.put(OrderUtil.order_userName, order.getOrderUserNAme());
        values.put(OrderUtil.order_email, order.getOrderEmail());
        values.put(OrderUtil.order_phone, order.getOrderPhoneNumber());
        values.put(OrderUtil.order_trip_date, order.getOrderTripDate());
        values.put(OrderUtil.order_current_date, order.getOrdercurrentDate());
        db.insert(OrderUtil.TABLE_TRIP,null,values);
        db.close();
    }

    //get all trip detail
    public List<Order> getAllOrder(){
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM "+ OrderUtil.TABLE_TRIP;
        Cursor cursor = db.rawQuery(selectAll, null);
        if(cursor.moveToFirst()){
            do{
                Order order = new Order();
                order.setOrderNumber(cursor.getString(0));
                order.setOrderUserNAme(cursor.getString(1));
                order.setOrderEmail(cursor.getString(2));
                order.setOrderPhoneNumber(cursor.getString(3));
                order.setOrderTripDate(cursor.getString(4));
                order.setOrdercurrentDate(cursor.getString(5));
                orderList.add(order);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orderList;
    }

}
