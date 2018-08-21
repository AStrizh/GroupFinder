package com.erudos.groupfinder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class RestaurantsActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.locationTextView) TextView restaurantsView;
    private RecyclerView.Adapter adapter;
    private YelpSearchResponse restaurants;
    private Context activityContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        Intent intent = getIntent();
        ButterKnife.bind(this);

        String location = intent.getStringExtra("location");
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerViewManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewManager);

        String tempString = restaurantsView.getText().toString() + location;
        restaurantsView.setText(tempString);
        getRestaurants(location);
    }


    //Executes a network call to Yelp Fusion API
    //Sets the responses in a recycler view
    private void getRestaurants(String location) {

        YelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                restaurants = YelpService.processResults(response);
                RestaurantsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        adapter = new RestaurantAdapter(restaurants);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addItemDecoration(
                                new DividerItemDecoration(
                                        recyclerView.getContext(),
                                        DividerItemDecoration.VERTICAL));

                        String tempString = restaurantsView.getText().toString();
                        String resultMessege = tempString
                                + "\n"
                                + getString(R.string.total)
                                + restaurants.getTotal();

                        restaurantsView.setText(resultMessege);

                    }
                });
            }
        });
    }


}
