package com.example.assignment2;

import android.graphics.Bitmap;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiClient {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    public static void getPosterImage(String url, int position, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
