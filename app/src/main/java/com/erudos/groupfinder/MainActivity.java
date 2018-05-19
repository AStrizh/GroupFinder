package com.erudos.groupfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.locationEditText) EditText enteredText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.restaurantbutton)
    public void onClick(View view) {
        String location = enteredText.getText().toString();
        Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }
}
