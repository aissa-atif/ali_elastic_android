package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("WWWWW", "TEST");

        //String endpoint = "http://10.0.2.2:9200";
        String endpoint = "http://51.210.181.177:9200/";
        ElasticsearchRestClient ESClient = new ElasticsearchRestClient(endpoint);

        ESClient.get("/foods_enriched/_search?q=food_code:571", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject dataObject) {
                // If the response is JSONObject instead of expected JSONArray
                Log.e("WWWW", "one object");
                try {
                    JSONObject hits_0 = (JSONObject) dataObject.get("hits") ;
                    JSONArray hits_1 =  (JSONArray) hits_0.get("hits") ;
                    JSONObject hits_2 = (JSONObject) hits_1.get(0);
                    JSONObject source = (JSONObject) hits_2.get("_source") ;
                    JSONArray nutrients = (JSONArray) source.get("nutrients") ;
                    String food_code = source.getString("food_code");

                    String food_description = source.getString("food_description");

                    ArrayList<List_View_Food> Item = new ArrayList<List_View_Food>();
                    for (int i=0;i< nutrients.length();i++){

                        JSONObject name_nut =(JSONObject) nutrients.get(i);
                        JSONObject name = (JSONObject) name_nut.get("name") ;
                        String value = name_nut.getString("nutrient_value");


                        String nutrient_name = name.get("nutrient_name").toString();
                        String uniti = name.get("unit").toString();
                        Item.add(new List_View_Food(nutrient_name,Float.parseFloat(value),uniti));
                        ListView ls = (ListView)findViewById(R.id.listview);
                        MycustomeAdapter myadapter = new MycustomeAdapter(Item);
                        ls.setAdapter(myadapter);
                        TextView txtname = (TextView)findViewById(R.id.nut_name);
                        txtname.setText(food_description);



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray dataList) {
                Log.e("WWWW", "multiple objects");
                // Pop one
                try {
                    JSONObject firstEvent = (JSONObject) dataList.get(0);
                    Log.e("WWWW", "one extracted object");

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    // WELP.
                }


            }
        });
    }


    //listview -------------------------------
    class MycustomeAdapter extends BaseAdapter{
        ArrayList<List_View_Food> Item = new ArrayList<List_View_Food>();
        MycustomeAdapter(ArrayList<List_View_Food>Item){
            this.Item = Item;

        }


        @Override
        public int getCount() {
            return Item.size();
        }

        @Override
        public Object getItem(int position) {
            return Item.get(position).name;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater  Inflater = getLayoutInflater();
            View view1 = Inflater.inflate(R.layout.row_listview, null);
            TextView txtname = (TextView) view1.findViewById(R.id.nutrient);
            TextView txtvaleur = (TextView) view1.findViewById(R.id.valeur);
            TextView txtuniti = (TextView) view1.findViewById(R.id.uniti);
            txtname.setText(Item.get(position).name);
            float valeur =  Item.get(position).valeur;

            txtvaleur.setText(String.valueOf(valeur));
            txtuniti.setText(Item.get(position).uniti);



            return view1;
        }
    }



}
