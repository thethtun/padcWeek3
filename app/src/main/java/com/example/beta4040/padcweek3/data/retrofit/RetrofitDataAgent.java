package com.example.beta4040.padcweek3.data.retrofit;

import android.util.Log;

import com.example.beta4040.padcweek3.data.Response.RestaurantResponse;
import com.example.beta4040.padcweek3.data.vos.RestaurantVO;
import com.example.beta4040.padcweek3.event.RetrofitResponseEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by beta4040 on 6/23/17.
 */

public class RetrofitDataAgent {
    private static final String TAG = RetrofitDataAgent.class.getSimpleName() ;
    private static RetrofitDataAgent objInstance;
    private ClientApi api;
    private List<RestaurantVO> restaurants;

    public RetrofitDataAgent(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://aungpyaephyo.xyz/restaurants/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ClientApi.class);
    }
    public static RetrofitDataAgent getObjInstance() {
        if(objInstance==null){
            return objInstance = new RetrofitDataAgent();
        }return objInstance;
    }

    public void fetchRestaurantData(){
        Call<RestaurantResponse> response = api.restaurantDataResponse();
        response.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                RestaurantResponse restaurantResponse = response.body();
                if(restaurantResponse != null) {
                    restaurants = response.body().getRestaurants();
                    Log.d("response Success", String.valueOf(response.body().getRestaurants()));
                    EventBus.getDefault().post(new RetrofitResponseEvent.RastaurantsResponseData(restaurants));
                }else{
                    Log.d("Error code", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Log.e("Error", "network failed");
            }
        });
    }


}
