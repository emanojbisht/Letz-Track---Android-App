package com.manojbisht.input_order.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.manojbisht.input_order.model.User;
import com.manojbisht.input_order.util.Util;

import java.util.ArrayList;
import java.util.List;

public class RegisterDataBase extends SQLiteOpenHelper {
    public RegisterDataBase(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Register Table
        String CREATE_REGISTER_TABLE = " CREATE TABLE " + Util.TABLE_REGISTER
                + " ( " + Util.register_name + " TEXT, "
                + Util.register_userName + " TEXT PRIMARY KEY, "
                + Util.register_password + " TEXT, "
                + Util.register_question + " TEXT, "
                + Util.register_answer + " TEXT ); ";
        db.execSQL(CREATE_REGISTER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + Util.TABLE_REGISTER);
        onCreate(db);
    }

    //register a user to the database
    public void registerUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.register_name, user.getrName());
        values.put(Util.register_userName, user.getrUserName());
        values.put(Util.register_password, user.getrPassword());
        values.put(Util.register_question, user.getrQuestion());
        values.put(Util.register_answer, user.getrAnswer());
        db.insert(Util.TABLE_REGISTER, null, values);
        Log.d("rockValue", "added ");
        db.close();

    }

    //get a user
    public User getUser(String userName){
        boolean flag = true;
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_REGISTER, new String[]{
                Util.register_name, Util.register_userName, Util.register_password, Util.register_question, Util.register_answer
        }, Util.register_userName + "=?",new String[]{String.valueOf(userName)},null,null,null);

        if(cursor != null) {
            flag = cursor.moveToFirst();
        }

        if(flag){
            user.setrName(cursor.getString(0));
            user.setrUserName(cursor.getString(1));
            user.setrPassword(cursor.getString(2));
            user.setrQuestion(cursor.getString(3));
            user.setrAnswer(cursor.getString(4));
        }else{
            user = null;
        }
        cursor.close();
        db.close();
        return user;
    }

    //get All users
    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM "+Util.TABLE_REGISTER;
        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setrName(cursor.getString(0));
                user.setrUserName(cursor.getString(1));
                user.setrPassword(cursor.getString(2));
                user.setrQuestion(cursor.getString(3));
                user.setrAnswer(cursor.getString(4));
                userList.add(user);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }


    //get user count
    public int getCount(){
        String count = " SELECT * FROM "+Util.TABLE_REGISTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(count,null);
        return  cursor.getCount();
    }



}
