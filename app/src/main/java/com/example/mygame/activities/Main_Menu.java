package com.example.mygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mygame.DBs.FireStoreDBClient;
import com.example.mygame.DBs.Player;
import com.example.mygame.R;
import com.example.mygame.card.Card;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Main_Menu extends AppCompatActivity {
    private static final String TAG = "MainMenu";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user = mAuth.getCurrentUser();
    private FirebaseFirestore DB = FirebaseFirestore.getInstance();
    private DocumentReference PlayerDocRef;
    private Player player;

    //TODO: музыка
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

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    PlayerDocRef = DB.collection("Players").document(user.getUid());
                    turnOnLoading();
                    PlayerDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            player=task.getResult().toObject(Player.class);
                            player.setMoney(Math.toIntExact((Long)task.getResult().get("money")));
                            if (player !=null) {
                                updateUI();
                            }
                            turnOffLoading();
                            Log.d(TAG,"Task = "+task.getResult().toString());
                        }
                    });
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Intent intent = new Intent(Main_Menu.this, LoginActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
        if (player !=null) {
            updateUI();
        }
    }

    private void turnOnLoading() {
        ImageView bar = findViewById(R.id.progress);
        LinearLayout barMain = findViewById(R.id.progressMain);
        barMain.setVisibility(View.VISIBLE);
        bar.setAnimation(AnimationUtils.loadAnimation(this,R.anim.full_rotate));
    }
    private void turnOffLoading(){
        ImageView bar = findViewById(R.id.progress);
        LinearLayout barMain = findViewById(R.id.progressMain);
        barMain.setVisibility(View.GONE);
        bar.clearAnimation();
    }

    private void updateUI() {
        DB.collection("Players").document(player.getUID()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                player = task.getResult().toObject(Player.class);
                player.setMoney(Math.toIntExact((Long)task.getResult().get("money")));
                TextView displayName = findViewById(R.id.display_name);
                displayName.setText(player.getName());

                ImageView avatar =  findViewById(R.id.Profile);
                avatar.setBackgroundResource(getResources().getIdentifier("avatar_" + player.getAvatar(), "drawable", getPackageName()));

                TextView money = findViewById(R.id.MoneyAmount);
                money.setText(String.valueOf(player.getMoney()));

                Log.d(TAG, "Player{" +
                        ", Name='" + player.getName() + '\'' +
                        ", Money=" + player.getMoney() +
                        ", Avatar=" + player.getAvatar() +
                        ", AllCards=" + player.getAllCards().toString() +
                        '}');
                Log.d(TAG,"UI updated");
            }
        });
    }



    public void StartGame(View view) {
        Intent intent = new Intent(Main_Menu.this, Find_Game.class);
        Gson gson = new Gson();
        intent.putExtra("player",gson.toJson(player));
        startActivity(intent);
    }

    public void OpenCollection(View view) {
        Intent intent = new Intent(Main_Menu.this,Card_Collection.class);

        Gson gson = new Gson();
        intent.putExtra("player",gson.toJson(player));
        startActivity(intent);
    }

    public void OpenLootboxes(View view) {
        if (player.getMoney()>=10) {
            Toast.makeText(this,"No real lootboxes yet, but now you have more cards",Toast.LENGTH_LONG).show();
            player.addMoney(-10);
            HashMap<String, Integer> moreCards = new HashMap<>();
            Random random = new Random();
            moreCards.put(String.valueOf(random.nextInt(Card.values().length)), Math.abs(random.nextInt() % 10));
            moreCards.put(String.valueOf(random.nextInt(Card.values().length)), Math.abs(random.nextInt() % 10));
            moreCards.put(String.valueOf(random.nextInt(Card.values().length)), Math.abs(random.nextInt() % 10));
            player.addCards(moreCards);
            FireStoreDBClient.updatePlayer(player);
            //Intent intent = new Intent(Main_Menu.this,Card_Collection.class);
            //startActivity(intent);
            updateUI();
        } else {
            Toast.makeText(this,"You have no money",Toast.LENGTH_LONG).show();
        }
    }

    public void OpenMoney(View view) {
        player.addMoney(1);
        updateUI();
        FireStoreDBClient.updateMoney(player.getMoney());
        Toast.makeText(this,"Not working yet",Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(Main_Menu.this,Money.class);
        //startActivity(intent);
    }

    public void OpenProfile(View view) {
        Intent intent = new Intent(Main_Menu.this,Profile.class);
        Gson gson = new Gson();

        intent.putExtra("player",gson.toJson(player));
        startActivity(intent);
    }

    public void OpenSettings(View view) {
        Toast.makeText(this,"Nothing to set up yet",Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(Main_Menu.this,SettingsActivity.class);
        //startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"LifeCycle_"+"onPause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"LifeCycle_"+"onResume");
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    PlayerDocRef = DB.collection("Players").document(user.getUid());
                    turnOnLoading();
                    PlayerDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            player=task.getResult().toObject(Player.class);
                            player.setMoney(Math.toIntExact((Long)task.getResult().get("money")));
                            if (player !=null) {
                                updateUI();
                            }
                            turnOffLoading();
                            Log.d(TAG,"Task = "+task.getResult().toString());
                        }
                    });
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Intent intent = new Intent(Main_Menu.this, LoginActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        if (player !=null) {
            updateUI();
        }
        Log.d(TAG,"LifeCycle_"+"onStart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"LifeCycle_"+"onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"LifeCycle_"+"onDestroy");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"LifeCycle_"+"onRestart");
    }
}
