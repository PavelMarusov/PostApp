package com.example.postapp;

import android.app.Application;

import com.example.postapp.data.PostService;

public class App extends Application {
    public static PostService postService;
    @Override
    public void onCreate() {
        super.onCreate();
        postService = new PostService();
    }
}
