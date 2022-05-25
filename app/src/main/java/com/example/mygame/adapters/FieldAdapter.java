package com.example.mygame.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.card.Card;

import java.util.ArrayList;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.MiniCard> {
    private static final String TAG = "FieldSetter";
    ArrayList<Card> Gen = new ArrayList<>();
    private OnFieldCardListener mOnFieldCardListener;

    public FieldAdapter(ArrayList<Card> Gen, OnFieldCardListener onFieldCardListener) {
        this.Gen=Gen;
        mOnFieldCardListener=onFieldCardListener;
    }
    @NonNull
    @Override
    public FieldAdapter.MiniCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_card,parent,false);
        return new MiniCard(view,mOnFieldCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldAdapter.MiniCard holder, int position) {
        holder.Picture.    setImageResource(Gen.get(position).                 getPicture());
        holder.HP.         setText         (String.valueOf(Gen.get(position).  getHp()));
        holder.Power.      setText         (String.valueOf(Gen.get(position).  getPower()));
        if (Gen.get(position).isGold()) {
            holder.Card.setBackgroundResource(R.drawable.card_background_gold);
        }
    }

    @Override
    public int getItemCount() {
        return Gen.size();
    }

    public class MiniCard extends RecyclerView.ViewHolder{
        ConstraintLayout    Card;
        ImageView           Picture;
        TextView            HP;
        TextView            Power;
        ImageView           Effect1;
        ImageView           Effect2;
        OnFieldCardListener onFieldCardListener;

        public MiniCard(@NonNull View itemView,OnFieldCardListener onFieldCardListener){
            super(itemView);
            Card    =itemView.findViewById(R.id.MiniCard);
            Picture =itemView.findViewById(R.id.MiniPicture);
            HP      =itemView.findViewById(R.id.MiniHP);
            Power   =itemView.findViewById(R.id.MiniPower);
            Effect1 =itemView.findViewById(R.id.Effect1);
            Effect2 =itemView.findViewById(R.id.Effect2);

            this.onFieldCardListener = onFieldCardListener;
            itemView.setOnClickListener(this::onClick);
        }

        public void onClick(View view){
            Log.d("MiniCard", "onClick: "+getAdapterPosition());
            onFieldCardListener.OnFieldCardClick(getAdapterPosition());
        }
    }

    public interface OnFieldCardListener{
        void OnFieldCardClick(int position);
    }
}
