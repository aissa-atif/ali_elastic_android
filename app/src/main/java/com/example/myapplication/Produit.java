package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

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
        OFFClient.get("737628064502" , null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject dataObject) {


                JSONObject product = null;
                try {
                    product = (JSONObject) dataObject.get("product");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Titre--------------------------------------------------------------------

                TextView Titre = (TextView) findViewById(R.id.Titre);
                try {

                    Titre.setText(product.getString("brands"));

                } catch (JSONException e) {
                    Titre.setText("NA");
                }

                // nutriscore_grade - product_name - countries - url_front
                String url_front ;
                TextView score_nova = (TextView) findViewById(R.id.score_nova);


                try {
                    String nova = product.getString("nova_groups") ;
                    score_nova.setText(nova);

                    if (nova.equals(""+1)){
                        score_nova.setBackgroundColor(Color.parseColor("#00AA00"));
                    }else if (nova.equals(""+2)){
                        score_nova.setBackgroundColor(Color.parseColor("#FFCC00"));
                    }else if (nova.equals(""+3)){
                        score_nova.setBackgroundColor(Color.parseColor("#FF6600"));
                    }else if (nova.equals(""+4)){
                        Log.e("test", "Nova"+nova );
                        score_nova.setBackgroundColor(Color.parseColor("#FE0000"));
                    }
                } catch (JSONException e) {
                    score_nova.setText("NA");
                }
                 TextView description = (TextView) findViewById(R.id.Description);
                try {
                    description.setText(product.getString("product_name"));
                } catch (JSONException e) {
                    description.setText("NA");
                }

                TextView score = (TextView)findViewById(R.id.score);
                try {
                    score.setText(product.getString("nutriscore_grade"));

                } catch (JSONException e) {
                    score.setText("NA");
                }
                ImageView image_front = (ImageView) findViewById(R.id.image_front) ;
                try {
                    url_front = product.getString("image_front_url");
                    Log.e("url", url_front);
                    Picasso.get().load(url_front).into(image_front);
                } catch (JSONException e) {
                    url_front="NA";
                }
                TextView origines = (TextView)findViewById(R.id.origines);
                try {
                    origines.setText(product.getString("origins"));
                } catch (JSONException e) {
                    origines.setText("NA");
                }
                TextView importés = (TextView) findViewById(R.id.importés);
                try {
                    importés.setText(product.getString("countries_imported"));
                } catch (JSONException e) {
                    importés.setText("NA");
                }
                TextView quantité = (TextView) findViewById(R.id.quantité);
                try {
                    quantité.setText(product.getString("product_quantity")+ "  g" );
                } catch (JSONException e) {
                    quantité.setText("NA");
                }
                TextView ingredients = (TextView) findViewById(R.id.textView8);
                try {
                    ingredients.setText(product.getString("ingredients_text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    ingredients.setText("NA");
                }

                JSONObject nutriments = null;
                try {
                    nutriments = (JSONObject) product.get("nutriments");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TextView calcium = (TextView) findViewById(R.id.calcium);
                try {

                    String calcium_unit = nutriments.getString("calcium_unit");
                    String calcium_serving = nutriments.getString("calcium_serving");
                    calcium.setText(calcium_serving + calcium_unit);

                } catch (JSONException e) {
                    calcium.setText("NA");
                    e.printStackTrace();

                }
                TextView proteins = (TextView) findViewById(R.id.proteins);
                try {
                    String proteins_unit = nutriments.getString("proteins_unit");
                    String proteins_serving = nutriments.getString("proteins_serving");
                    proteins.setText(proteins_serving + proteins_unit);

                } catch (JSONException e) {
                    proteins.setText("NA");
                    e.printStackTrace();
                }
                TextView sugars = (TextView)findViewById(R.id.sugars);
                try {
                    String sugars_unit = nutriments.getString("sugars_unit");
                    String sugars_serving = nutriments.getString("sugars_serving");
                    sugars.setText(sugars_serving + sugars_unit);

                } catch (JSONException e) {
                    sugars.setText("NA");
                    e.printStackTrace();
                }
                TextView vitamic_C = (TextView) findViewById(R.id.vitamin_c);

                try {
                    String vitamin_c_unit = nutriments.getString("vitamin-c_unit");
                    String vitamin_c_serving = nutriments.getString("vitamin-c_serving");
                    vitamic_C.setText(vitamin_c_serving + vitamin_c_unit);

                } catch (JSONException e) {
                    vitamic_C.setText("NA");
                    e.printStackTrace();
                }
                TextView vitamin_a = (TextView) findViewById(R.id.vitamin_a);
                try {
                    String vitamin_a_unit = nutriments.getString("vitamin-a_unit");
                    String vitamin_a_serving = nutriments.getString("vitamin-a_serving");
                    vitamin_a.setText(vitamin_a_serving + vitamin_a_unit);

                } catch (JSONException e) {
                    vitamin_a.setText("NA");
                    e.printStackTrace();
                }
                TextView fiber = (TextView) findViewById(R.id.fiber);
                try {
                    String fiber_unit = nutriments.getString("fiber_unit");
                    String fiber_serving = nutriments.getString("fiber_serving");
                    fiber.setText(fiber_serving+fiber_unit);

                } catch (JSONException e) {
                    fiber.setText("NA");
                    e.printStackTrace();
                }
                TextView sodium = (TextView)findViewById(R.id.sodium);
                try {
                    String sodium_unit = nutriments.getString("sodium_unit");
                    String sodium_serving = nutriments.getString("sodium_serving");
                    sodium.setText(sodium_serving+sodium_unit);

                } catch (JSONException e) {
                    sodium.setText("NA");
                    e.printStackTrace();
                }
                TextView fat = (TextView)findViewById(R.id.fat);
                try {
                    String fat_unit = nutriments.getString("fat_unit");
                    String fat_serving = nutriments.getString("fat_serving");
                    fat.setText(fat_serving+ fat_unit);
                } catch (JSONException e) {
                    fat.setText("NA");

                    e.printStackTrace();
                }
                TextView iron = (TextView)findViewById(R.id.iron);
                try {
                    String iron_unit = nutriments.getString("iron_unit");
                    String iron_serving = nutriments.getString("iron_serving");
                    iron.setText(iron_serving+ iron_unit);

                } catch (JSONException e) {
                    iron.setText("NA");
                    e.printStackTrace();
                }

                TextView energy = (TextView)findViewById(R.id.energy);
                try {
                    String energy_unit = nutriments.getString("energy_unit");
                    String energy_serving = nutriments.getString("energy_serving");
                    energy.setText(energy_serving+energy_unit);

                } catch (JSONException e) {
                    energy.setText("NA");
                    e.printStackTrace();
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray dataList) {
                Log.e("WWWW", "multiple objects");


            }
        });

    }

    @SuppressLint("WrongConstant")
    public void ingredients_visible(View view) {
        TextView ingredients = (TextView) findViewById(R.id.textView8);
        if(ingredients.getVisibility() == View.GONE){
            ingredients.setVisibility(View.VISIBLE);
        }else ingredients.setVisibility(View.GONE);


    }

    public void table_visible(View view) {
        LinearLayout table = (LinearLayout) findViewById(R.id.table);
        if(table.getVisibility() == View.GONE){
            table.setVisibility(View.VISIBLE);
        }else table.setVisibility(View.GONE);

    }
}
