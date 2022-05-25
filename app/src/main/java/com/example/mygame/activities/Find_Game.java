package com.example.mygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mygame.DBs.CardSet;
import com.example.mygame.DBs.Enemy;
import com.example.mygame.DBs.Game;
import com.example.mygame.DBs.Player;
import com.example.mygame.R;
import com.example.mygame.card.CardType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Find_Game extends AppCompatActivity {
    private Player player;
    private Enemy enemy =null;
    private Game game;
    private DocumentReference gameRef;
    private CardType cur;
    private FirebaseFirestore DB = FirebaseFirestore.getInstance();
    private static final String TAG ="FindGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_game);
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
        Intent intent = getIntent();
        Gson gson = new Gson();
        player = gson.fromJson(intent.getStringExtra("player"), Player.class);

        setsButtons();


        ImageButton back = findViewById(R.id.GoBack);
        View.OnClickListener backClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        back.setOnClickListener(backClick);

    }

    private void setsButtons(){
        for (int i =1;i<=player.getSetsAmount();i++){
            ImageButton s = findViewById(getID(i));
            s.setVisibility(View.VISIBLE);
            int finalI = i;
            s.setOnClickListener(v-> chooseSet(finalI));
            //s.setOnLongClickListener(v -> player.d);
        }
        for (int i =player.getSetsAmount()+1;i<=9;i++){
            ImageButton s = findViewById(getID(i));
            s.setVisibility(View.GONE);
        }
    }

    private void chooseSet(int j){
        for (int i =1;i<j;i++){
            ImageButton s = findViewById(getID(i));
            s.setVisibility(View.GONE);
        }
        for (int i =j+1;i<=9;i++){
            ImageButton s = findViewById(getID(i));
            s.setVisibility(View.GONE);
        }
        ArrayList<CardSet> onlySet = new ArrayList<CardSet>();
        onlySet.add(player.getSets().get(j - 1));
        player.setSets(onlySet);
        Log.d(TAG,"Set chosen");
        findViewById(R.id.Choose).setVisibility(View.GONE);
        searchGame();
    }

    private int getID(int i){
        switch (i){
            case 1: return R.id.set1;
            case 2: return R.id.set2;
            case 3: return R.id.set3;
            case 4: return R.id.set4;
            case 5: return R.id.set5;
            case 6: return R.id.set6;
            case 7: return R.id.set7;
            case 8: return R.id.set8;
            case 9: return R.id.set9;
        }
        return 0;
    }

    private void searchGame() {
        turnOnLoading();
        DB.collection("Games")      //Поиск свободной комнаты
                .whereEqualTo("enemy", null)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()) {                   //Если таких нет, то создать свою и ждать
                            Log.d(TAG,"Creating room");
                            gameRef = DB.collection("Games").document();
                            game = new Game(gameRef.getId(), player.getUID());
                            gameRef.set(game).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    gameRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                            if (error != null) {
                                                Log.w(TAG, "Listen failed.", error);
                                                return;
                                            }
                                            if (value.get("enemy") != null) {
                                                game = value.toObject(Game.class);
                                                turnOffLoading();
                                            }
                                        }
                                    });
                                }
                            });
                        } else {        //Иначе добавление себя в эту комнату
                            Log.d(TAG,"Adding to room");
                            List<Game> gameList = task.getResult().toObjects(Game.class);
                            game = gameList.get(0);
                            game.change();
                            Log.d(TAG, "game "+game.getId());
                            game.setPlayer(player.getUID());
                            DB.collection("Games").document(game.getId()).update("enemy", player.getUID()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    turnOffLoading();
                                }
                            });
                        }
                    }
                });
    }

    private void changeLoading(){
        ImageView bar = findViewById(R.id.progress);
        if (cur != CardType.Abstract){
            cur = CardType.values()[cur.get()+1];
        } else {
            cur = CardType.Human;
        }
        bar.setImageResource(cur.symCard());
    }
    private void turnOnLoading() {
        Log.d(TAG,"Loading turned on");
        ImageView bar = findViewById(R.id.progress);
        cur = CardType.values()[new Random().nextInt(6)];
        bar.setImageResource(cur.symCard());
        bar.setVisibility(View.VISIBLE);
        bar.setAnimation(AnimationUtils.loadAnimation(this,R.anim.full_rotate));
    }
    private void turnOffLoading(){
        ImageView bar = findViewById(R.id.progress);
        bar.setVisibility(View.GONE);
        bar.clearAnimation();
        Log.d(TAG, "Loading turned off");
        Intent intent = new Intent(Find_Game.this,Game_Screen.class);
        Gson gson = new Gson();
        intent.putExtra("player",gson.toJson(player));
        intent.putExtra("game",gson.toJson(game));

        DB.collection("Players").
                document(game.getEnemy()).
                get().
                addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                enemy = task.getResult().toObject(Enemy.class);
                intent.putExtra("enemy",gson.toJson(enemy));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DB.collection("Games")
                .whereEqualTo("player", player.getUID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (Game g : task.getResult().toObjects(Game.class)) {
                            DB.collection("Games").document(g.getId()).delete();
                        }
                        Find_Game.super.onBackPressed();
                    }
                });
    }

}
