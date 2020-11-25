package com.example.myapplication;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class scan_test {

    private String BASE_URL;
    private static AsyncHttpClient client;

    public scan_test(){
        this.BASE_URL = "https://alivisiond4.herokuapp.com/api/images/";

    }

    // PUBLIC - GET, POST, PUT, DELETE

    public void get(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(this.BASE_URL, params, responseHandler);
    }



    // PRIVATE

}
