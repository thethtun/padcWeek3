package com.example.beta4040.padcweek3.data.Response;

/**
 * Created by beta4040 on 6/23/17.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("restaurants")
    @Expose
    private List<RestaurantResponse> restaurants = null;
    private final static long serialVersionUID = 3050707320460245488L;

    public MainResponse(Integer code, String message, String timestamp, List<RestaurantResponse> restaurants) {
        super();
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.restaurants = restaurants;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }


    public List<RestaurantResponse> getRestaurants() {
        return restaurants;
    }



}