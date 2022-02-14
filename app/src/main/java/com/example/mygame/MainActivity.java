package com.example.mygame;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationBarMenu;


public class MainActivity extends AppCompatActivity {

    int Counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    //Hiding status bar
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();   //Hiding title bar

        View decorView = getWindow().getDecorView();    //Hiding navigation bar
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);



    }
}


/*    public void Gen(View view) {
        Button GenerateButton = (Button) findViewById(R.id.generate);
        Counter++;
        GenerateButton.setText(String.valueOf(Counter));
    }

    public void Attack(View view) {
        Button MyCard = (Button) findViewById(R.id.MyCard);
        MyCard.setText("Work!");
    }

    public abstract class Card {
        boolean minion;
        boolean gold;
        int cost;
        int hp;
        int power;
        int[] quirks = {0, 0};
        int[] effects = {0, 0};
    }
}*/

