package com.example.mygame.DBs;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FireStoreDBClient {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseAuth.AuthStateListener mAuthListener;
    private static FirebaseUser user = mAuth.getCurrentUser();

    private static final String TAG = "FireStoreDB";

    public static void updateProfileName(String name) {
        Log.d(TAG, "Name = " + name);
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        CollectionReference Players = DB.collection("Players");
        //Обновляем профиль
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Players.document(user.getUid())
                                    .update("name", user.getDisplayName())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d(TAG, "User profile updated" + user.getDisplayName());
                                        }
                                    });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "User profile update FAILED");
            }
        });
    }

    public static void updateAvatar(int Avatar) {
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        CollectionReference Players = DB.collection("Players");
        Players.document(user.getUid()).update("avatar", Avatar)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Avatar updated");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Avatar update FAILED");
            }
        });
    }

    public static void updateMoney(int money) {
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        CollectionReference Players = DB.collection("Players");
        Players.document(user.getUid()).update("money", money)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Money updated");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Money update FAILED");
            }
        });
    }

    public static void createPlayerDocument(FirebaseUser user) {
        HashMap<String, Integer> allCards = new HashMap<String, Integer>();
        ArrayList<CardSet> sets = new ArrayList<>();
        Player data = new Player(user.getDisplayName(),
                new Random().nextInt(10),
                0,
                allCards,
                sets,
                user.getUid());
        Log.d(TAG, user.getUid());
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        CollectionReference Players = DB.collection("Players");

        Players.document(user.getUid()).set(data)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.d(TAG, "DocumentSnapshot written");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "DocumentSnapshot FAILED");
                Log.d(TAG, data.getName() + " "
                        + data.getAvatar() + " "
                        + data.getMoney());
            }
        });
    }

    public static void updatePlayer(Player player) {
        Log.d(TAG, user.getUid());
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        CollectionReference Players = DB.collection("Players");
        Players.document(user.getUid())
                .set(player)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.d(TAG, "Fire updated");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Fire update FAILED");
            }
        });
    }

    public static void updateSets(List<CardSet> sets) {
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        CollectionReference Players = DB.collection("Players");
        Players.document(user.getUid()).update("sets", sets)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Sets updated");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Sets update FAILED");
            }
        });
    }
}


