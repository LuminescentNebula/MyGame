package com.example.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    int Counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

