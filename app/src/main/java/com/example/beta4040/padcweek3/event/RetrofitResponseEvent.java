package com.example.beta4040.padcweek3.event;

import com.example.beta4040.padcweek3.data.Response.RestaurantResponse;

import java.util.List;

/**
 * Created by beta4040 on 6/24/17.
 */

public class RetrofitResponseEvent {

    public static class RastaurantsResponseData
    {
        List<RestaurantResponse> restaurants;

        public RastaurantsResponseData(List<RestaurantResponse> restaurants) {
            this.restaurants = restaurants;
        }

        public List<RestaurantResponse> getRestaurants() {
            return restaurants;
        }
    }
}
