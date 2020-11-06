package com.example.myapplication;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

public class ElasticsearchRestClient {

    private String BASE_URL;
    private static AsyncHttpClient client;

    public ElasticsearchRestClient(String endpoint){
        this.BASE_URL = endpoint;
        this.client = new AsyncHttpClient();
        this.client.setBasicAuth("aissa","aissa123");
    }

    // PUBLIC - GET, POST, PUT, DELETE

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    // PRIVATE

    private String getAbsoluteUrl(String relativeUrl) {
        return this.BASE_URL + relativeUrl;
    }
}
