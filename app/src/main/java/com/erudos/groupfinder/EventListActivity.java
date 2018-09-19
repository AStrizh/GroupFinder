package com.erudos.groupfinder;

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

public class EventListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    //private EventListResponse events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerViewManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewManager);

        getEvents();
    }


    //Executes a network call to Yelp Fusion API
    //Sets the responses in a recycler view
    //TODO: WIll access Firebase to get list of events
    private void getEvents() {

        ArrayList<Event> events = new ArrayList<>();

        //String id, String name, String phone, String imageUrl, ArrayList<String> address
        Business business2 = new Business("4t_cecgFJezeKbkpgmMRxQ",
                                          "Marilyn Jean IV",
                                          "917-650-3212",
                                          "https://s3-media2.fl.yelpcdn.com/bphoto/wz2qTn-iC9pYm8arkNniig/o.jpg",
                                           new ArrayList<String>());

        Business business1 = new Business("CVxz9B5ncpMwVK1bk_c1YA",
                                          "Veselka",
                                          "212-228-9682",
                                          "https://s3-media2.fl.yelpcdn.com/bphoto/aksIi5Mur35LGL366qmlZg/o.jpg",
                                           new ArrayList<String>());


        Event event1 = new Event("Ukrainian Dinner", "Trying Ukrainian food",
                "13 JAN 2019", business1);
        Event event2 = new Event("Fishing Fun", "Going fishing in Brooklyn",
                "27 MAR 2020", business2);

        events.add(event1);
        events.add(event2);

        adapter = new EventListAdapter(events);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));




//        YelpService.findBusinesses(searchTerm, location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//
//                restaurants = YelpService.processSearchResults(response);
//
//                BusinessListActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        adapter = new BusinessListAdapter(restaurants);
//                        recyclerView.setAdapter(adapter);
//                        recyclerView.addItemDecoration(
//                                new DividerItemDecoration(
//                                        recyclerView.getContext(),
//                                        DividerItemDecoration.VERTICAL));
//
//                        String tempString = restaurantsView.getText().toString();
//                        String resultMessege = tempString
//                                + "\n"
//                                + getString(R.string.total)
//                                + restaurants.getTotal();
//
//                        restaurantsView.setText(resultMessege);
//
//                    }
//                });
//            }
//        });
    }
}
