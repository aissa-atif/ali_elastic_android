package com.example.myapplication;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class open_food_fac {
    private String BASE_URL;
    private static AsyncHttpClient client;
    public open_food_fac(){
        this.BASE_URL = "https://world.openfoodfacts.org/api/v0/product/";
        this.client = new AsyncHttpClient();
    }
    // PUBLIC - GET, POST, PUT, DELETE
    public void get(String id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(id), params, responseHandler);
    }
    public void post(String id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(id), params, responseHandler);
    }
    // PRIVATE
    private String getAbsoluteUrl(String id) {
        return this.BASE_URL + id + ".json";
    }
}