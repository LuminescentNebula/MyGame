package com.example.mygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.DBs.Enemy;
import com.example.mygame.DBs.Game;
import com.example.mygame.DBs.Player;
import com.example.mygame.R;
import com.example.mygame.adapters.FieldAdapter;
import com.example.mygame.adapters.PlayerHandAdapter;
import com.example.mygame.adapters.PlayerShowHandAdapter;
import com.example.mygame.card.Card;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.lb.auto_fit_textview.AutoResizeTextView;
import com.rasi.clickableadapter.OnViewHolderClickListener;

import java.util.ArrayList;
import java.util.Objects;


public class Game_Screen extends AppCompatActivity implements OnViewHolderClickListener {

    final static int  CardAmount=4;

    private PlayerHandAdapter playerHandAdapter;
    private PlayerShowHandAdapter playerShowHandAdapter;
    private FieldAdapter playerFieldAdapter;
    private FieldAdapter enemyFieldAdapter;

    private static final String TAG   = "GameScreen";
    private ImageButton next;
    private ImageButton show;
    private AutoResizeTextView mana;
    RecyclerView recyclerView;
    RecyclerView showRecyclerView;
    RecyclerView PlayerFieldRecyclerView;
    RecyclerView EnemyFieldRecyclerView;
    private FirebaseFirestore DB = FirebaseFirestore.getInstance();

    private Player          player;
    private Enemy           enemy;
    private Game            game;
    private ArrayList<Card> playerSet;
    private ArrayList<Card> playerField;
    private ArrayList<Card> enemyField;

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

        Intent intent= getIntent();
        Gson gson = new Gson();
        player = gson.fromJson(intent.getStringExtra("player"),Player.class);
        enemy = gson.fromJson(intent.getStringExtra("enemy"),Enemy.class);
        game = gson.fromJson(intent.getStringExtra("game"),Game.class);
        Log.d(TAG, "player "+ gson.toJson(player));
        Log.d(TAG, "enemy "+ gson.toJson(enemy));
        Log.d(TAG, "game "+ gson.toJson(game));

        TextView temp = findViewById(R.id.temp_ID);
        temp.setText(game.getId());

        ImageView playerAvatar = findViewById(R.id.PlayerAvatar);
        playerAvatar.setImageResource(getResources().getIdentifier("avatar_"+player.getAvatar(),"drawable",getPackageName()));
        ImageView enemyAvatar = findViewById(R.id.EnemyAvatar);
        enemyAvatar.setImageResource(getResources().getIdentifier("avatar_"+enemy.getAvatar(),"drawable",getPackageName()));

        playerSet = player.getSets().get(0).getList();
        playerField = game.getFieldPlayerCards();
        enemyField = game.getFieldEnemyCards();


        playerShowHandAdapter = new PlayerShowHandAdapter(playerSet,this);
        playerHandAdapter = new PlayerHandAdapter(playerSet);
        playerFieldAdapter = new FieldAdapter(playerField,this);
        enemyFieldAdapter =new FieldAdapter(enemyField,this);

        initPlayerRecyclerView();
        initFieldRecyclerView();

        next = findViewById(R.id.Next);
        next.setOnClickListener(v -> Next());

        show = findViewById(R.id.Show);
        show.setOnClickListener(v -> Show());

        mana = findViewById(R.id.Mana);
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        //Stroke to mana
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(this, R.color.green_gem));
        shapeDrawable.setStroke(2.0f, ContextCompat.getColor(this, R.color.black));
        ViewCompat.setBackground(mana, shapeDrawable);
}

    private void Next() {
        next.setImageResource(R.drawable.placeholder);


    }

    /*class initPlayerRecycler extends Thread {
        @Override
        public synchronized void start() {
            super.start();
            initPlayerRecyclerView(9);

        }
    }*/

    public void initPlayerRecyclerView(){
        recyclerView = findViewById(R.id.PlayerCardLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(playerHandAdapter);

        showRecyclerView = findViewById(R.id.PlayerShowCardLayout);
        showRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        showRecyclerView.setAdapter(playerShowHandAdapter);
    }

    public void updatePlayerRecyclerView(int i){
        playerHandAdapter.notifyItemRemoved(i);
        playerShowHandAdapter.notifyItemRemoved(i);
    }

    public void initFieldRecyclerView(){
        PlayerFieldRecyclerView= findViewById(R.id.PlayerField);
        PlayerFieldRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        PlayerFieldRecyclerView.setAdapter(playerFieldAdapter);

        EnemyFieldRecyclerView = findViewById(R.id.EnemyField);
        EnemyFieldRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        EnemyFieldRecyclerView.setAdapter(enemyFieldAdapter);
        Log.d(TAG,new Gson().toJson(playerField));
    }

    private void updateFieldRecyclerView() {
        playerFieldAdapter.notifyDataSetChanged();
        Log.d(TAG,new Gson().toJson(playerField));
    }
//    public void initEnemyRecyclerView(){
//        EnemyAdapter adapter;
//        RecyclerView recyclerView = findViewById(R.id.EnemyCardLayout);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter= new EnemyAdapter(CardAmount);//
//        recyclerView.setAdapter(adapter);
//        // Заполнение руки карт противника (только количество, маски)
//    }

    public void Show(){
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
        ConstraintSet st = new ConstraintSet();
        //st.setVerticalWeight(R.id.Battlefield,(float) 4);
        st.connect(R.id.PlayerCardLayout,ConstraintSet.BOTTOM,R.id.HeadConstraintLayout,ConstraintSet.BOTTOM);
        st.connect(R.id.PlayerCardLayout,ConstraintSet.END,R.id.HeadConstraintLayout,ConstraintSet.END);
        st.connect(R.id.PlayerCardLayout,ConstraintSet.START,R.id.Buttons,ConstraintSet.END);
        st.connect(R.id.PlayerCardLayout,ConstraintSet.TOP,R.id.Battlefield,ConstraintSet.BOTTOM);

        if (showRecyclerView.getVisibility()==View.INVISIBLE) {
            show.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
            st.setVerticalWeight(R.id.PlayerCardLayout,(float) 3.5);
            //lp.verticalWeight = (float) 2.5;
            showRecyclerView.setVisibility(View.VISIBLE);
        } else {
            show.setImageResource(R.drawable.ic_baseline_arrow_upward_24);
            //lp.verticalWeight = (float) 1.5;
            st.setVerticalWeight(R.id.PlayerCardLayout,(float) 1.5);
            showRecyclerView.setVisibility(View.INVISIBLE);
        }
        st.applyTo(findViewById(R.id.HeadConstraintLayout));
    }

    public void setMana(int i){
        mana = findViewById(R.id.Mana);
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mana.getLayoutParams();
        lp.matchConstraintPercentHeight = (float) 0.1*i;
        mana.setLayoutParams(lp);
        mana.setText(String.valueOf(i));
    }

    @Override
    public void onClickAtItem(int i) {
        //TODO: add to field and sent
        Log.d(TAG,"PlayerField add 0"+playerField.size());
        if (player.getUID().equals(game.getPlayer())){
            if(game.addFieldPlayerCards(playerSet.get(i))){
                Log.d(TAG,"PlayerField add 1"+playerField.size());
                playerSet.remove(i);
                updatePlayerRecyclerView(i);
                updateFieldRecyclerView();
            }
        } else {
            if (game.addFieldEnemyCards(playerSet.get(i))) {
                Log.d(TAG,"PlayerField add 2"+playerField.size());
                playerSet.remove(i);
                updatePlayerRecyclerView(i);
                updateFieldRecyclerView();
            }
        }
        //Toast.makeText(this,i+" clicked",Toast.LENGTH_SHORT).show();
        Log.i(TAG,i+"clicked");
    }



//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
}

