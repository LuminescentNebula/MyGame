package com.example.mygame.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;

public class EnemyAdapter extends RecyclerView.Adapter<EnemyAdapter.EnemyCard>{
    private static final String TAGHandSetter = "EnemyHandSetter";
    int masks;      //Static?

    public EnemyAdapter(int masks) {
        this.masks=masks;
    }

    @NonNull
    @Override
    public EnemyAdapter.EnemyCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mask,parent,false);
        return new EnemyAdapter.EnemyCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnemyAdapter.EnemyCard holder, int position) {
        holder.Mask.setBackgroundResource(R.drawable.mask);
        Log.i(TAGHandSetter,"Card "+position+ "set");
    }

    @Override
    public int getItemCount() {
        return masks;
    }

    /*private void addItem(Card item) {
        cards.add(item);
        MyAdapter.notifyDataSetChanged();
    }*/

    class EnemyCard extends RecyclerView.ViewHolder {
        ImageView Mask;

        public EnemyCard(@NonNull View itemView) {
            super(itemView);
            Mask    = itemView.findViewById(R.id.mask);
        }
    }
}
