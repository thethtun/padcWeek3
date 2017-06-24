package com.example.beta4040.padcweek3.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.beta4040.padcweek3.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by beta4040 on 6/23/17.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rating_star_count)
    public RatingBar ratingBar;

    @BindView(R.id.rating_count)
    public TextView ratingTextView;

    @BindView(R.id.restaurant_title)
    public TextView restaurantTitle;

    @BindView(R.id.restaurant_tag)
    public TextView restaurantTag;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
