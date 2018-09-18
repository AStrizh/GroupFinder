package com.erudos.groupfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    @BindView(R.id.photo1) ImageView photo1;
    @BindView(R.id.photo2) ImageView photo2;
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

    //TODO: Make this a Save Event button
    @OnClick(R.id.createEventButton)
    public void onClick(View view) {

        //String location = zipText.getText().toString();
        //String searchTerm = searchText.getText().toString();


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

                        //TODO: See if you can safely delete this
                        String businessString;
                        if(business == null){
                            businessString = "No results Returned";
                        }
                        else{
                            businessString = business.getName() +
                                    "\n" +
                                    business.getPhone() +
                                    "\n" +
                                    business.getImageUrl() +
                                    "\n" +
                                    business.getReviewCount();
                        }

                        businessName.setText(business.getName());
                        Picasso.with(BusinessViewActivity.this)
                                     .load(business.getPhotos()
                                     .get(0))
                                     .into(businessPhoto);
                        Picasso.with(BusinessViewActivity.this)
                                     .load(business.getPhotos()
                                     .get(1))
                                     .into(photo1);
                        Picasso.with(BusinessViewActivity.this)
                                     .load(business.getPhotos()
                                     .get(2))
                                     .into(photo2);

                    }
                });
            }
        });
    }



}
