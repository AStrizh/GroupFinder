package com.erudos.groupfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    static final int PICK_RESTAURANT = 1;
    @BindView(R.id.locationEditText) EditText enteredText;
    @BindView(R.id.resultLayout) LinearLayout resultLayout;
    @BindView(R.id.resultNameTextView) TextView nameView;
    @BindView(R.id.resultAdressView) TextView addressView;
    @BindView(R.id.resultNumberView) TextView numberView;
    @BindView(R.id.resultImageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        resultLayout.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.restaurantbutton)
    public void onClick(View view) {
        String location = enteredText.getText().toString();
        Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
        intent.putExtra("location", location);

        startActivityForResult(intent,PICK_RESTAURANT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String name = "No Name";
        if (requestCode == PICK_RESTAURANT) {
            if (resultCode == RESULT_OK) {

                ArrayList<String> results = data.getStringArrayListExtra("result");

                name = results.get(0);
                nameView.setText(results.get(0));
                addressView.setText(results.get(1));
                numberView.setText(results.get(2));
                Picasso.with(this).load(results.get(3)).into(imageView);

                resultLayout.setVisibility(View.VISIBLE);
            }
        }
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }
}
