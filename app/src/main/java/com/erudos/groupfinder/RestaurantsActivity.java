package com.erudos.groupfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


public class RestaurantsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recyclerViewManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewManager);

        TextView restaurantsView = findViewById(R.id.locationTextView);
        String tempString = restaurantsView.getText().toString();
        restaurantsView.setText(tempString + location);
        //restaurantsView.setVisibility(View.INVISIBLE);

        String[] myDataset = new String[]{"oval","unwieldy","north","frequent","hammer",
                "disgusted","cook","simplistic","mature","frantic","spotless","grotesque",
                "yummy","knot","voice","tenuous","fire","prick","stream","plucky","languid",
                "graceful","sparkle","zippy","rude","parched","unusual","request","sloppy",
                "turn","trite","five","axiomatic","purpose","silent","erect","obey",
                "interesting","fine","fear","quarter","faithful","house",
                "ski","horses","mice","army","jelly","fairies","hissing"};

        adapter = new RestaurantAdapter(myDataset);
        recyclerView.setAdapter(adapter);
    }
}
