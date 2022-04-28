package com.example.mygame.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.adapter.EnemyAdapter;
import com.example.mygame.adapter.PlayerAdapter;
import com.example.mygame.card.AllCards;
import com.example.mygame.card.Card;
import com.example.mygame.card.CardType;

import java.util.Objects;
import java.util.Random;


public class Game_Screen extends AppCompatActivity {

    final static int  CardAmount=9;
    //TODO: Использовать Enum, для всех строк и картинок


    Card[] PlayerCards = new Card[CardAmount];
    Card[] EnemyCards = new Card[CardAmount];
    private static final String TAGCardSetter = "CardSetter";
    private static final String TAGCardAttack = "CardAttack";
    private static final String TAGSetParam   = "CardParam";

    /**
     * Массив названий частей карт
     */
    String[] subtitles = {"Headline","RedGem","BlueGem","Cost",
                            "Description","LoreText","Picture"};

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

        /* Проверка, что версия ситема достаточна для AutoTextSizeType
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        TODO autotextsize (Удалить, если autofittext заработает);
        }*/
    }

    int n=0;

    public void initPlayerRecyclerView(int n){
        PlayerAdapter adapter;
        RecyclerView recyclerView = findViewById(R.id.PlayerCardLayout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new PlayerAdapter(AllCards.PreGen);// TODO: отсылаются данные, которые нужно внести в view
        recyclerView.setAdapter(adapter);
    }

    public void addCardPlayerRecyclerView(int n ){

    }

    public void initEnemyRecyclerView(int n){
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

    public void Gen(View view){
        n++;
        Random r = new Random();
        initPlayerRecyclerView(r.nextInt(9));
        initEnemyRecyclerView(r.nextInt(9));
        //Поле в которое вводили номер карты
    }
}

