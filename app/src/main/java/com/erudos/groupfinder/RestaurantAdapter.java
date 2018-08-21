package com.erudos.groupfinder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private YelpSearchResponse restaurants;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView restaurantNameText;
        private TextView restaurantAdressText;
        private TextView restaurantPhoneText;
        private ImageView restaurantPicture;
        private LinearLayout itemLayout;

        ViewHolder(View view) {
            super(view);
            restaurantNameText = view.findViewById(R.id.restaurantNameTextView);
            restaurantAdressText = view.findViewById(R.id.adressTextView);
            restaurantPhoneText = view.findViewById(R.id.numberTextView);
            restaurantPicture = view.findViewById(R.id.restaurantImageView);
            itemLayout = view.findViewById(R.id.itemLayout);
        }

        void bindRestaurant(final Restaurant restaurant) {
            restaurantNameText.setText(restaurant.getName());
            restaurantAdressText.setText(restaurant.getAddress().toString());
            restaurantPhoneText.setText(restaurant.getPhone());
            Picasso.with(itemView.getContext()).load(restaurant.getImageUrl()).into(restaurantPicture);

            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(itemView.getContext(),
                                   restaurantNameText.getText(),
                                   Toast.LENGTH_SHORT).show();

                    ArrayList<String> result = new ArrayList<>();
                    result.add(restaurant.getName());
                    result.add(restaurant.getAddress().toString());
                    result.add(restaurant.getPhone());
                    result.add(restaurant.getImageUrl());

                    Intent resultIntent = new Intent();
                    resultIntent.putStringArrayListExtra("result",result);
                    ((RestaurantsActivity)itemView.getContext()).setResult(RESULT_OK,resultIntent);

                }
            });
        }
    }

    RestaurantAdapter(YelpSearchResponse restaurants) {
        this.restaurants = restaurants;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.restaurant_items;
        LayoutInflater inflater = LayoutInflater.from(context);

        // create a new view
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindRestaurant(restaurants.getRestaurants().get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return restaurants.getRestaurants().size();
    }
}