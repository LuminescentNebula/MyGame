package com.example.mygame.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerCard>{
    @NonNull
    @Override
    public PlayerAdapter.PlayerCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerCard holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
