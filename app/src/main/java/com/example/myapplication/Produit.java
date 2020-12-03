package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

import static com.example.myapplication.R.drawable.ic_nova_group_1;

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

                 Evalutation_nutri Evaluation = new Evalutation_nutri();
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
                ImageView score_nova = (ImageView) findViewById(R.id.score_nova);
                TextView nova_text = (TextView)findViewById(R.id.nova_text);


                try {
                    String nova = product.getString("nova_groups") ;


                    if (nova.equals(""+1)){
                        score_nova.setBackgroundResource(ic_nova_group_1);
                        nova_text.setText("Aliments non transformés ou transformés minimalement");
                    }else if (nova.equals(""+2)){
                        score_nova.setBackgroundResource(R.drawable.ic_nova_group_2);
                        nova_text.setText("Ingrédients culinaires transformés");
                    }else if (nova.equals(""+3)){
                        score_nova.setBackgroundResource(R.drawable.ic_nova_group_3);
                        nova_text.setText("Aliments transformés");
                    }else if (nova.equals(""+4)){
                        nova_text.setText("Produits alimentaires et boissons ultra-transformés");
                        score_nova.setBackgroundResource(R.drawable.ic_nova_group_4);
                    }
                } catch (JSONException e) {

                }
                 TextView description = (TextView) findViewById(R.id.Description);
                try {
                    description.setText(product.getString("product_name"));
                } catch (JSONException e) {
                    description.setText("NA");
                }

                ImageView score = (ImageView) findViewById(R.id.score);
                String score_nut ;
                try {
                   score_nut = product.getString("nutriscore_grade");

                    if (score_nut.equals(""+1)){
                        score.setBackgroundResource(R.drawable.ic_nutriscore_a);
                    }else if (score_nut.equals("b")){
                        score.setBackgroundResource(R.drawable.ic_nutriscore_b);
                    }else if (score_nut.equals("c")){
                        score.setBackgroundResource(R.drawable.ic_nutriscore_c);
                    }else if (score_nut.equals("d")){
                        score.setBackgroundResource(R.drawable.ic_nutriscore_d);
                    }
                    else if (score_nut.equals("e")){
                        score.setBackgroundResource(R.drawable.ic_nutriscore_e);
                    }

                } catch (JSONException e) {

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
                TextView Level_sucre = (TextView)findViewById(R.id.Level_Sucre_textview);
                ImageView Level_sucre_image = (ImageView)findViewById(R.id.Level_Sucre_image);
                try {
                    String sugars_unit = nutriments.getString("sugars_unit");
                    String sugars_serving = nutriments.getString("sugars_serving");
                    sugars.setText(sugars_serving + sugars_unit);
                    String sugars_100g = nutriments.getString("sugars_100g");
                    Evaluation.Evaluation_sucres(Float.parseFloat(sugars_100g),Level_sucre,Level_sucre_image);



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
                TextView lipide = (TextView)findViewById(R.id.Level_grasse_textview);
                ImageView lipide_image = (ImageView) findViewById(R.id.Level_grasse_image);
                try {
                    String fat_unit = nutriments.getString("fat_unit");
                    String fat_serving = nutriments.getString("fat_serving");
                    fat.setText(fat_serving+ fat_unit);
                    String fat_100g = nutriments.getString("fat_100g");
                    Evaluation.Evaluation_Lipid(Float.parseFloat(fat_100g),lipide,lipide_image);

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

                TextView sel = (TextView)findViewById(R.id.Level_Sel_textview);
                ImageView sel_image = (ImageView)findViewById(R.id.Level_Sel_image);
                try {
                    String salt_100g = nutriments.getString("salt_100g");
                    Evaluation.Evaluation_sel(Float.parseFloat(salt_100g),sel,sel_image);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                TextView satured_fat = (TextView) findViewById(R.id.saturated_fat);
                TextView Level_grasseS = (TextView)findViewById(R.id.Level_grasseS_view);
                ImageView Level_grasseS_image = (ImageView)findViewById(R.id.Level_grasseS_image);
                try {

                    String saturated_fat_serving = nutriments.getString("saturated-fat_serving");
                    String saturated_fat_unit = nutriments.getString("saturated-fat_unit");
                    satured_fat.setText(saturated_fat_serving +saturated_fat_unit);
                    String saturated_fat_100g = nutriments.getString("saturated-fat_100g");
                    Evaluation.Evaluation_Acide_g_s(Float.parseFloat(saturated_fat_100g),Level_grasseS,Level_grasseS_image);

                } catch (JSONException e) {
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
