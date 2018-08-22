package com.erudos.groupfinder;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpService {

    static void findBusinesses(String searchTerm, String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_SEARCH_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.TERM_QUERY_PARAMETER, urlEncoder(searchTerm));
        urlBuilder.addQueryParameter(Constants.LIMIT_QUERY_PARAMETER, "50");

        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    static void getBusinessData(String id, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        String urlString = String.format("%s%s", Constants.YELP_BUSINESS_URL, id);
        Request request= new Request.Builder()
                .url(urlString)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    static YelpSearchResponse processSearchResults(Response response) {

        YelpSearchResponse searchResponse = null;

        try {

            ArrayList<Business> businesses = new ArrayList<>();
            String jsonData = response.body().string();

            JSONObject yelpJSON = new JSONObject(jsonData);

            int total = yelpJSON.getInt("total");
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");


            for (int i = 0; i < businessesJSON.length(); i++) {

                JSONObject businessJSON = businessesJSON.getJSONObject(i);
                String id = businessJSON.getString("id");
                String name = businessJSON.getString("name");
                String phone = businessJSON.optString("display_phone", "Phone not available");
                String imageUrl = businessJSON.getString("image_url");

                ArrayList<String> address = new ArrayList<>();
                JSONArray addressJSON = businessJSON.getJSONObject("location")
                        .getJSONArray("display_address");
                for (int y = 0; y < addressJSON.length(); y++) {
                    address.add(addressJSON.get(y).toString());
                }

                Business business = new Business(id, name, phone, imageUrl, address);
                businesses.add(business);
            }

            searchResponse = new YelpSearchResponse(total, businesses);

        }
        catch (IOException | JSONException e){
            e.printStackTrace();
        }

        return searchResponse;
    }

    //Method needs to be finished and tested
    static Business processBusinessResult(Response response) {

        Business business = null;

        try {

            String jsonData = response.body().string();
            JSONObject businessJSON = new JSONObject(jsonData);


            String id = businessJSON.getString("id");
            String name = businessJSON.getString("name");
            String phone = businessJSON.optString("phone", "Phone not available");
            String imageUrl = businessJSON.getString("image_url");
            String website = businessJSON.getString("url");
            String price = businessJSON.getString("price");

            int reviewCount = businessJSON.getInt("review_count");
            double rating = businessJSON.getDouble("rating");
            boolean openNow = false; //Figure out how to get the open now value
            ArrayList<String> photos = new ArrayList<>();

            JSONArray photosArray = businessJSON.getJSONArray("photos");
            for (int y = 0; y < photosArray.length(); y++) {
                photos.add(photosArray.get(y).toString());
            }

            business = new Business(id, name, phone, imageUrl, website, price, reviewCount,
            rating, openNow, photos);

        }
        catch (IOException | JSONException e){
            e.printStackTrace();
        }

        return business;
    }


    static String urlEncoder( String encode) {

        String encoded = "default";

        try {encoded = URLEncoder.encode(encode,"UTF-8");}
        catch (UnsupportedEncodingException e) {}

        return encoded;
    }
}
