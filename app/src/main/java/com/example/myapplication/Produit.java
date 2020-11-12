package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Produit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);

        String Key;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                Key= null;
            } else {
                Key = extras.getString("key");
            }
        } else {
            Key = (String) savedInstanceState.getSerializable("key");
        }

        open_food_fac OFFClient = new open_food_fac();
        OFFClient.get(Key , null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject dataObject) {


                JSONObject product = null;
                try {
                    product = (JSONObject) dataObject.get("product");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Titre--------------------------------------------------------------------
                String Titre ;
                try {


                    JSONArray Titre_1 = (JSONArray) product.get("brands_tags");
                    Titre = Titre_1.get(0).toString();
                    Log.e("Titre", Titre );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // nutriscore_grade - product_name - countries - url_front
                String product_name ;
                String nutriscore_grade ;
                String Countries ;
                String nutriscore_score ;
                String url_front ;
                TextView score_nova = (TextView) findViewById(R.id.score_nova);

                try {


                    score_nova.setText(product.getString("nutriscore_grade"));


                } catch (JSONException e) {
                    score_nova.setText("NA");
                }

                try {

                    product_name = product.getString("product_name");


                } catch (JSONException e) {
                    product_name = "NA";
                }

                try {

                    Countries = product.getString("brands_tags");

                } catch (JSONException e) {
                    Countries = "NA";
                }

                try {

                    nutriscore_score = product.getString("nutriscore_score");


                } catch (JSONException e) {
                    nutriscore_score = "NA";
                }
                try {

                    url_front = product.getString("image_front_url");

                } catch (JSONException e) {
                    url_front="NA";
                }




            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray dataList) {
                Log.e("WWWW", "multiple objects");




            }
        });

    }
}
