package com.example.beta4040.padcweek3.data.Persistent;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by beta4040 on 7/4/17.
 */

public class RestaurantContract {
    public static final String CONTENT_AUTHORITY = "com.example.beta4040.padcweek3";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_AUTHORITY);
    public static final String PATH_RESTAURANT = "restaurants";

    public static class RestaurantEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/"  + CONTENT_AUTHORITY + PATH_RESTAURANT;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_RESTAURANT;

        public static final String TABLE_NAME = "restaurants";
        public static final String COLUMN_TITLE = "Name";
        public static final String COLUMN_ADDRSHORT = "Address";
        public static final String COLUMN_IMAGE_URL = "Image Url";
        public static final String COLUMN_TOTAL_RATING_COUNT = "Total Rating Count";
        public static final String COLUMN_AVERAGE_RATING_COUNT = "Average Rating Count";
        public static final String COLUMN_IS_AD = "Contains Ad";
        public static final String COLUMN_IS_NEW = "Newly Opened";
        public static final String COLUMN_TAG = "Tag";
        public static final String COLUMN_LEAD_TIME_IN_MIN = "Delivery Time";

        public static Uri buildContentUriWithID(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
