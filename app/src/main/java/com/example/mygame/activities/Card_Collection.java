package com.example.mygame.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.adapter.PlayerAdapter;
import com.example.mygame.card.AllCards;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.transition.MaterialFade;

import java.util.Objects;

public class Card_Collection extends AppCompatActivity {
    private static final String TAGCardCollection   = "CardCollection";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_collection);
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

        //TODO: нормальная коллекция
        initCollectionRecyclerView(9);

        ImageButton back = findViewById(R.id.GoBack);
        View.OnClickListener PopClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(v);
            }
        };

    }


    protected void goBack(View view) {
        //startActivity(new Intent(Card_Collection.this,Main_Menu.class));
        ConstraintLayout pop = findViewById(R.id.PopUp);
        ConstraintLayout mainpop = findViewById(R.id.PopUpMain);
        if (mainpop.getVisibility()==View.GONE){
            mainpop.setVisibility(View.VISIBLE);
        } else {
            mainpop.setVisibility(View.GONE);
        }

        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        //Stroke
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(this,R.color.second_background));
        shapeDrawable.setStroke(5.0f, ContextCompat.getColor(this,R.color.black));
        //Set
        ViewCompat.setBackground(pop,shapeDrawable);

        Log.i(TAGCardCollection,"Visibility");

    }

    public void initCollectionRecyclerView(int n){
        PlayerAdapter adapter;
        RecyclerView recyclerView = findViewById(R.id.CollectionRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new PlayerAdapter(AllCards.PreGen);// TODO: отсылаются данные, которые нужно внести в view
        recyclerView.setAdapter(adapter);
    }

}
