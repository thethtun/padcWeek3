package com.example.beta4040.padcweek3.data.retrofit;

import android.util.Log;

import com.example.beta4040.padcweek3.data.model.ResponseVO;
import com.example.beta4040.padcweek3.data.model.RestaurantVO;
import com.example.beta4040.padcweek3.event.RetrofitResponseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        Call<ResponseVO> response = api.restaurantDataResponse();
        response.enqueue(new Callback<ResponseVO>() {
            @Override
            public void onResponse(Call<ResponseVO> call, Response<ResponseVO> response) {
                restaurants = response.body().getRestaurants();
                EventBus.getDefault().post(new RetrofitResponseEvent.RastaurantsResponseData(restaurants));
            }

            @Override
            public void onFailure(Call<ResponseVO> call, Throwable t) {

            }
        });
    }


}
