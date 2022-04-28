package com.example.mygame.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mygame.DBs.FireStoreDBClient;
import com.example.mygame.DBs.Player;
import com.example.mygame.DBs.RoomDBClient;
import com.example.mygame.R;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.Random;

import lombok.NonNull;

public class Profile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Player player;
    private final String TAG="Profile";

    //TODO локальное обновление аватара
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
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

        Player player = RoomDBClient
                .getInstance(getApplicationContext())
                .getDB()
                .playerDao().getPlayer();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Button signOut = findViewById(R.id.SignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        EditText name = findViewById(R.id.EditName);
        try {
            name.setText(user.getDisplayName());
        } catch (NullPointerException e){}
        Button change = findViewById(R.id.ChangeName);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireStoreDBClient.updateProfileName(user,name.getText().toString());
                player.setName(name.getText().toString());
            }
        });

        Button rand = findViewById(R.id.ChangeRandom);
        rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] array = getResources().getStringArray(R.array.player_names);
                String randomStr = array[new Random().nextInt(array.length)];
                name.setText(randomStr);
            }
        });
        Slider slider = findViewById(R.id.Slider);
        slider.setValue(player.getAvatar());
        ImageView avatar = findViewById(R.id.avatar);
        avatar.setBackgroundResource(getResources().getIdentifier("avatar_"+player.getAvatar(),"drawable",getPackageName()));

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                avatar.setBackgroundResource(getResources().getIdentifier("avatar_"+(int)value,"drawable",getPackageName()));
                //Log.d(TAG,"avatar_"+(int)value);
            }
        });

        ImageButton back = findViewById(R.id.GoBack);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FireStoreDBClient.updateAvatar(user,(int)slider.getValue());
                player.setAvatar((int)slider.getValue());
                RoomDBClient.getInstance(getApplicationContext())
                        .getDB()
                        .playerDao()
                        .update(player);
                onBackPressed();
            }
        });

//        mBinding.verifyEmailButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendEmailVerification();
//            }
//        });
    }

    private void signOut() {
        RoomDBClient
                .getInstance(getApplicationContext())
                .getDB()
                .playerDao().delete(player);
        mAuth.signOut();
    }
}
