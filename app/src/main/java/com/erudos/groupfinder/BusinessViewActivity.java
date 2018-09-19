package com.erudos.groupfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


//TODO: Turn this into a Create Event activity
public class BusinessViewActivity extends AppCompatActivity {

    @BindView(R.id.BusinessName) TextView businessName;
    @BindView(R.id.businessPhoto) ImageView businessPhoto;

    @BindView(R.id.eventName)   EditText eventName;
    @BindView(R.id.eventDateTime)   EditText eventTime;
    @BindView(R.id.eventShortDescription) EditText eventShortDescription;

    //TODO: Create edit text views to fill in the data
    //TODO: Date/Time, Event Name, Business ID, Event Short description, Event Long Description
    //TODO: Decide on a Date/Time class to store event time



    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        getBusiness(id);
    }

    @OnClick(R.id.yelpLaunch)
    public void onYelpClick(View view) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(business.getWebsite()));
        startActivity(browserIntent);
    }

    //TODO: Save event to SQLite or Android Room DB
    @OnClick(R.id.createEventButton)
    public void onClick(View view) {

        String name;
        String description;
        String date;

        name = (eventName != null) ? eventName.getText().toString() : "No Event Name";
        date = (eventTime != null) ? eventTime.getText().toString() : "No Event Date/Time";
        description = (eventShortDescription != null) ?
                eventShortDescription.getText().toString() : "No Event Description";


        Event event = new Event(name,date,description,business);

    }


    //Executes a network call to Yelp Fusion API
    //Sets the responses in a recycler view
    private void getBusiness(String id) {


        YelpService.getBusinessData(id, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {

                business = YelpService.processBusinessResult(response);
                BusinessViewActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        businessName.setText(business.getName());
                        Picasso.with(BusinessViewActivity.this)
                                     .load(business.getPhotos()
                                     .get(0))
                                     .into(businessPhoto);

                    }
                });
            }
        });
    }

}
