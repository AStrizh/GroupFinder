package com.erudos.groupfinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private ArrayList<Restaurant> restaurants;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView restaurantNameText;
        private TextView restaurantAdressText;
        private TextView restaurantPhoneText;
        private ImageView restaurantPicture;

        public ViewHolder(View view) {
            super(view);
            restaurantNameText = view.findViewById(R.id.restaurantNameTextView);
            restaurantAdressText = view.findViewById(R.id.adressTextView);
            restaurantPhoneText = view.findViewById(R.id.numberTextView);
            restaurantPicture = view.findViewById(R.id.restaurantImageView);
        }

        public void bindRestaurant(Restaurant restaurant) {
            restaurantNameText.setText(restaurant.getName());
            restaurantAdressText.setText(restaurant.getAddress().toString());
            restaurantPhoneText.setText(restaurant.getPhone());
            Picasso.with(itemView.getContext()).load(restaurant.getImageUrl()).into(restaurantPicture);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RestaurantAdapter(ArrayList<Restaurant> myRestaurants) {
        restaurants = myRestaurants;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.restaurant_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachImmediately = false;

        // create a new view
        View view = inflater.inflate(layoutIdForListItem, parent, attachImmediately);

        //View view = inflater.inflate(layoutIdForListItem, parent, attachImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.holderView.setText(restaurants.get(position));
        holder.bindRestaurant(restaurants.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}