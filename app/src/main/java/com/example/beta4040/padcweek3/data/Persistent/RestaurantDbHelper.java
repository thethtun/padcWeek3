package com.example.beta4040.padcweek3.data.Persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;

import com.example.beta4040.padcweek3.data.Persistent.RestaurantContract.RestaurantEntry;

/**
 * Created by beta4040 on 7/4/17.
 */

public class RestaurantDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "restaurants.db";

    public RestaurantDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " ("+
            RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_ADDRSHORT + " TEXT, " +
            RestaurantEntry.COLUMN_IMAGE_URL + " TEXT, " +
            RestaurantEntry.COLUMN_TOTAL_RATING_COUNT  + " INTEGER, " +
            RestaurantEntry.COLUMN_AVERAGE_RATING_COUNT + " INTEGER, " +
            RestaurantEntry.COLUMN_IS_AD + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_IS_NEW  + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_TAG + " TEXT, "  +
            RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " INTEGER NOT NULL, " +
            "UNIQUE ( "  + RestaurantEntry.COLUMN_TITLE + ")ON CONFLICT REPLACE" +
            " );";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
