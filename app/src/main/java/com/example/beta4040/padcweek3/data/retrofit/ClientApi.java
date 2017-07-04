package com.example.beta4040.padcweek3.data.retrofit;

import com.example.beta4040.padcweek3.data.Response.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by beta4040 on 6/23/17.
 */

public interface ClientApi {
    @GET("get-restaurants.php")
    Call<RestaurantResponse> restaurantDataResponse();
}
