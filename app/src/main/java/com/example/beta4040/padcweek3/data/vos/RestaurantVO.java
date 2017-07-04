package com.example.beta4040.padcweek3.data.vos;

/**
 * Created by beta4040 on 6/23/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.nfc.Tag;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.example.beta4040.padcweek3.activity.RestaurantActivity;
import com.example.beta4040.padcweek3.data.Persistent.RestaurantContract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.beta4040.padcweek3.data.Persistent.RestaurantContract.RestaurantEntry;


public class RestaurantVO implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("addr-short")
    @Expose
    private String addrShort;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("total-rating-count")
    @Expose
    private Integer totalRatingCount;
    @SerializedName("average-rating-value")
    @Expose
    private Float averageRatingValue;
    @SerializedName("is-ad")
    @Expose
    private Boolean isAd;
    @SerializedName("is-new")
    @Expose
    private Boolean isNew;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("lead-time-in-min")
    @Expose
    private Integer leadTimeInMin;

    private String tagString ="";
    private final static long serialVersionUID = -5271147397991539851L;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestaurantVO() {
    }

    /**
     *
     * @param tags
     * @param title
     * @param addrShort
     * @param isAd
     * @param averageRatingValue
     * @param isNew
     * @param image
     * @param leadTimeInMin
     * @param totalRatingCount
     */
    public RestaurantVO(String title, String addrShort, String image, Integer totalRatingCount, Float averageRatingValue, Boolean isAd, Boolean isNew, List<String> tags, Integer leadTimeInMin) {
        super();
        this.title = title;
        this.addrShort = addrShort;
        this.image = image;
        this.totalRatingCount = totalRatingCount;
        this.averageRatingValue = averageRatingValue;
        this.isAd = isAd;
        this.isNew = isNew;
        this.tags = tags;
        this.leadTimeInMin = leadTimeInMin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(Integer totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public Float getAverageRatingValue() {
        return averageRatingValue;
    }

    public void setAverageRatingValue(Float averageRatingValue) {
        this.averageRatingValue = averageRatingValue;
    }

    public Boolean getIsAd() {
        return isAd;
    }

    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public void setLeadTimeInMin(Integer leadTimeInMin) {
        this.leadTimeInMin = leadTimeInMin;
    }

    public static void saveRestaurantsIntoDatabase(List<RestaurantVO> restaurantVOs, Context context){

        ContentValues[] contentValues = new ContentValues[restaurantVOs.size()];
        for(int index =0; index<restaurantVOs.size(); index++){
            RestaurantVO restaurantVO = restaurantVOs.get(index);
            contentValues[index] = restaurantVO.parseToContentValues();
        }
        int insertedRows = context.getContentResolver().bulkInsert(RestaurantEntry.CONTENT_URI, contentValues);
        Log.d("BulkInsert()", String.valueOf(insertedRows));
    }

    public ContentValues parseToContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantEntry.COLUMN_ADDRSHORT, addrShort);
        cv.put(RestaurantEntry.COLUMN_AVERAGE_RATING_COUNT, averageRatingValue);
        cv.put(RestaurantEntry.COLUMN_IMAGE_URL, image);
        cv.put(RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantEntry.COLUMN_IS_NEW, isNew);
        cv.put(RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, leadTimeInMin);
        cv.put(RestaurantEntry.COLUMN_TAG, convertTagArrayIntoString(tags));
        cv.put(RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCount);
        return cv;
    }

    public static RestaurantVO parseFromContentValues(Cursor data){
        RestaurantVO restaurantVO = new RestaurantVO();
        restaurantVO.title = data.getString(data.getColumnIndex(RestaurantEntry.COLUMN_TITLE));
        restaurantVO.addrShort = data.getString(data.getColumnIndex(RestaurantEntry.COLUMN_ADDRSHORT));
        restaurantVO.image = data.getString(data.getColumnIndex(RestaurantEntry.COLUMN_IMAGE_URL));
        restaurantVO.totalRatingCount = data.getInt(data.getColumnIndex(RestaurantEntry.COLUMN_TOTAL_RATING_COUNT));
        restaurantVO.averageRatingValue = data.getFloat(data.getColumnIndex(RestaurantEntry.COLUMN_AVERAGE_RATING_COUNT));
        restaurantVO.isAd = data.getString(data.getColumnIndex(RestaurantEntry.COLUMN_IS_AD)).equals("true");
        restaurantVO.isNew = data.getString(data.getColumnIndex(RestaurantEntry.COLUMN_IS_NEW)).equals("true");
        String tagString = data.getString(data.getColumnIndex(RestaurantEntry.COLUMN_TAG));
        String [] tagArray = tagString.split(", ");
        restaurantVO.tags = Arrays.asList(tagArray);
        restaurantVO.leadTimeInMin = data.getInt(data.getColumnIndex(RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN));

        return restaurantVO;
    }

    public String convertTagArrayIntoString(List<String> tags){
        String TagString = "";
        this.tags = tags;
        for(String tag : tags ){
            TagString = TagString + tag + ", ";
        }
        return TagString;
    }
}


