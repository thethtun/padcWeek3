package com.example.beta4040.padcweek3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beta4040.padcweek3.R;
import com.example.beta4040.padcweek3.view.RestaurantViewHolder;
import com.example.beta4040.padcweek3.data.model.RestaurantVO;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by beta4040 on 6/23/17.
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private static final String TAG = recyclerViewAdapter.class.getSimpleName();
    private List<RestaurantVO> RestaurantObject;

    public recyclerViewAdapter(List<RestaurantVO> RestaurantObject) {
        this.RestaurantObject = RestaurantObject;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_recycler_view, parent, false);
        Log.d(TAG, "Yay~~Adapter works");
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.ratingBar.setRating(RestaurantObject.get(position).getAverageRatingValue());
        holder.ratingTextView.setText("("+ RestaurantObject.get(position).getTotalRatingCount()+")");
        holder.restaurantTitle.setText(RestaurantObject.get(position).getTitle());
        holder.deliveryTime.setText(String.valueOf("Deliver in "+RestaurantObject.get(position).getLeadTimeInMin() + " mins"));

        List<String> RestaurantTagsList = RestaurantObject.get(position).getTags();
        String TagForEachRestaurant = "";
        for (int i=0; i< RestaurantTagsList.size(); i++){
            TagForEachRestaurant = TagForEachRestaurant + RestaurantTagsList.get(i) + ", ";
        }
        Log.v("RestauratTag", String.valueOf(TagForEachRestaurant));
        holder.restaurantTag.setText(TagForEachRestaurant);

        if(RestaurantObject.get(position).getIsAd()){
            holder.restaurantAdTag.setVisibility(View.VISIBLE);
        }else{
            holder.restaurantAdTag.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return RestaurantObject.size();
    }
}
