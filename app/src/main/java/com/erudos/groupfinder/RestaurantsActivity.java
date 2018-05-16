package com.erudos.groupfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        TextView restaurantsView = findViewById(R.id.locationTextView);
        String tempString = restaurantsView.getText().toString();
        restaurantsView.setText(tempString + location);
    }
}
