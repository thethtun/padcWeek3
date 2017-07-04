package com.example.beta4040.padcweek3.event;

import com.example.beta4040.padcweek3.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by beta4040 on 6/24/17.
 */

public class RetrofitResponseEvent {

    public static class RastaurantsResponseData
    {
        List<RestaurantVO> restaurants;

        public RastaurantsResponseData(List<RestaurantVO> restaurants) {
            this.restaurants = restaurants;
        }

        public List<RestaurantVO> getRestaurants() {
            return restaurants;
        }
    }
}
