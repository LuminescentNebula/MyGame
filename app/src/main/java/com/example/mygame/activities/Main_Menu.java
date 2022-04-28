package com.example.mygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mygame.R;

import java.util.Objects;

public class Main_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        // Hiding status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hiding title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Hiding navigation bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//TODO: нормальное выключение активити
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void StartGame(View view) {
        Intent intent = new Intent(Main_Menu.this, Game_Screen.class);
        startActivity(intent);
    }

    public void OpenCollection(View view) {
        Intent intent = new Intent(Main_Menu.this,Card_Collection.class);
        startActivity(intent);
    }

    public void OpenLootboxes(View view) {
        Intent intent = new Intent(Main_Menu.this,Card_Collection.class);
        startActivity(intent);
    }

    public void OpenMoney(View view) {
        Intent intent = new Intent(Main_Menu.this,Money.class);
        startActivity(intent);
    }

    public void OpenProfile(View view) {
        Intent intent = new Intent(Main_Menu.this,Profile.class);
        startActivity(intent);
    }

    public void OpenSettings(View view) {

    }
}
