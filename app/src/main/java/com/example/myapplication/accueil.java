package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;

import cz.msebera.android.httpclient.Header;

public class accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("je suis la ", "onCreate: " );


        setContentView(R.layout.accueil);

    }

    public void start_scan(View view) {

        Intent myIntent = new Intent(accueil.this, scan.class);
        accueil.this.startActivity(myIntent);

    }
}
