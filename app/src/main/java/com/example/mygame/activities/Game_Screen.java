package com.example.mygame.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.adapters.EnemyAdapter;
import com.example.mygame.adapters.PlayerHandAdapter;
import com.example.mygame.adapters.PlayerShowHandAdapter;
import com.example.mygame.card.Card;
import com.rasi.clickableadapter.OnViewHolderClickListener;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class Game_Screen extends AppCompatActivity implements OnViewHolderClickListener {

    final static int  CardAmount=9;

    Card[] PlayerCards = new Card[CardAmount];
    Card[] onFieldPlayerCards = new Card[CardAmount];
    int EnemyCards;
    PlayerHandAdapter playerHandAdapter = new PlayerHandAdapter(Arrays.asList(Card.values()), this);
    PlayerShowHandAdapter playerShowHandAdapter = new PlayerShowHandAdapter(Arrays.asList(onFieldPlayerCards),this);

    private static final String TAGCardSetter = "CardSetter";
    private static final String TAGCardAttack = "CardAttack";
    private static final String TAGSetParam   = "CardParam";
    private static final String TAGSetClick   = "PlayerCardClick";

    /**
     * Массив названий частей карт
    String[] subtitles = {"Headline","RedGem","BlueGem","Cost",
                            "Description","LoreText","Picture"};
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
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

        Button gen = findViewById(R.id.Generate);
        View.OnClickListener GenClick = v -> Gen();
        gen.setOnClickListener(GenClick);

        ImageButton show = findViewById(R.id.Show);
        View.OnClickListener ShowClick = v -> Show();
        show.setOnClickListener(ShowClick);
    }

    /*class initPlayerRecycler extends Thread {
        @Override
        public synchronized void start() {
            super.start();
            initPlayerRecyclerView(9);

        }
    }*/


    public void initPlayerRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.PlayerCardLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        //TODO: отсылаются данные, которые нужно внести в view
        recyclerView.setAdapter(playerHandAdapter);
    }

    public void addCardPlayerRecyclerView(int n ){

    }
    public void initEnemyRecyclerView(){
        EnemyAdapter adapter;
        RecyclerView recyclerView = findViewById(R.id.EnemyCardLayout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new EnemyAdapter(CardAmount);// TODO: отсылаются данные, которые нужно внести в view
        recyclerView.setAdapter(adapter);
        //TODO: Заполнение руки карт противника (только количество, маски)
    }

    public void addCardEnemyRecyclerView(int n ){

    }

    public void Gen(){
        Random r = new Random();
        /*initPlayerRecycler yet = new initPlayerRecycler();
        yet.setName("initPlayer");
        yet.start();*/
        initPlayerRecyclerView();
        initEnemyRecyclerView();
    }

    public void Show(){
        //Animation anim =  AnimationUtils.loadAnimation(this, R.anim.trans);
        //recyclerView.setItemAnimator();
        findViewById(R.id.PlayerCardLayout).setTranslationY(50);
    }

    @Override
    public void onClickAtItem(int i) {
        Toast.makeText(this,i+" clicked",Toast.LENGTH_SHORT).show();
        Log.i(TAGSetClick,i+"clicked");


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

