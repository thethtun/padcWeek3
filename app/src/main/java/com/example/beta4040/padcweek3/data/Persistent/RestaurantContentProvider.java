package com.example.beta4040.padcweek3.data.Persistent;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.R.attr.id;
import static android.R.attr.value;

/**
 * Created by beta4040 on 7/4/17.
 */

public class RestaurantContentProvider extends ContentProvider {
    private RestaurantDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new RestaurantDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor;
        queryCursor = dbHelper.getReadableDatabase().query(
                RestaurantContract.RestaurantEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int insertedRow =0;
        try{
            db.beginTransaction();
            for(ContentValues cv : values){
                long id = db.insert(RestaurantContract.RestaurantEntry.TABLE_NAME, null, cv);
                if(id>0){
                    insertedRow++;
                }
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            db.close();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return insertedRow;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long returnID = db.insert(RestaurantContract.RestaurantEntry.TABLE_NAME, null, contentValues);
        if(returnID>0){
            getContext().getContentResolver().notifyChange(uri, null);
        }else{
            throw new SQLException("Failed to insert rows into " + uri);
        }
        return RestaurantContract.RestaurantEntry.buildContentUriWithID(returnID);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedID = db.delete(RestaurantContract.RestaurantEntry.TABLE_NAME,
                selection,
                selectionArgs);
        if(deletedID>0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deletedID;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = db.update(RestaurantContract.RestaurantEntry.TABLE_NAME,
                contentValues,
                selection,
                selectionArgs);
        if(updateRows>0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updateRows;
    }
}
