package com.example.pizzaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBAdapter extends SQLiteOpenHelper {

    public static final String ORDER_ID = "_id";
    public static final String PIZZA_SIZE = "size";
    public static final String PIZZA_TOPPING = "topping";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String ORDER_DATE_TIME = "order_date";
    public static final String TAG = "DBAdapter";

    public static final String DBNAME = "DaysPizza";
    public static final String DBTABLE = "OrderDetails";
    public static final int    DBVERSION = 1;

    public DBAdapter(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + DBTABLE + " (" +
                ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PIZZA_SIZE + " TEXT, " +
                PIZZA_TOPPING + " TEXT, " +
                CUSTOMER_NAME + " TEXT, " +
                ORDER_DATE_TIME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBTABLE);
        onCreate(db);
    }


}
