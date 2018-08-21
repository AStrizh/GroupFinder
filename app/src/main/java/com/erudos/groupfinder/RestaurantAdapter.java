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
    private static Context parentContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView restaurantNameText;
        private TextView restaurantAdressText;
        private TextView restaurantPhoneText;
        private ImageView restaurantPicture;
        private LinearLayout itemLayout;

        public ViewHolder(View view) {
            super(view);
            restaurantNameText = view.findViewById(R.id.restaurantNameTextView);
            restaurantAdressText = view.findViewById(R.id.adressTextView);
            restaurantPhoneText = view.findViewById(R.id.numberTextView);
            restaurantPicture = view.findViewById(R.id.restaurantImageView);
            itemLayout = view.findViewById(R.id.itemLayout);
        }

        public void bindRestaurant(final Restaurant restaurant) {
            restaurantNameText.setText(restaurant.getName());
            restaurantAdressText.setText(restaurant.getAddress().toString());
            restaurantPhoneText.setText(restaurant.getPhone());
            Picasso.with(itemView.getContext()).load(restaurant.getImageUrl()).into(restaurantPicture);

            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(itemView.getContext(), restaurantNameText.getText(), Toast.LENGTH_SHORT).show();

                    ArrayList<String> result = new ArrayList<>();
                    result.add(restaurant.getName());
                    result.add(restaurant.getAddress().toString());
                    result.add(restaurant.getPhone());
                    result.add(restaurant.getImageUrl());

                    Intent resultIntent = new Intent();
                    resultIntent.putStringArrayListExtra("result",result);
                    ((RestaurantsActivity)parentContext).setResult(RESULT_OK,resultIntent);

//                    Intent intent = new Intent(mContext, GalleryActivity.class);
//                    intent.putExtra("image_url", mImages.get(position));
//                    intent.putExtra("image_name", mImageNames.get(position));
//                    mContext.startActivity(intent);
                }
            });
        }
    }

    public RestaurantAdapter(YelpSearchResponse restaurants, Context context) {
        this.restaurants = restaurants;
        parentContext = context;
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
        holder.bindRestaurant(restaurants.getRestaurants().get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return restaurants.getRestaurants().size();
    }
}