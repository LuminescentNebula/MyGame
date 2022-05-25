package com.example.mygame.activities;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        Log.d("APP","OnCreate");
    }
}
