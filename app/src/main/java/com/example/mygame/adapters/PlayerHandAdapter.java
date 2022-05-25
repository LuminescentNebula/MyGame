package com.example.mygame.adapters;

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
import com.lb.auto_fit_textview.AutoResizeTextView;

import java.util.ArrayList;

public class PlayerHandAdapter extends RecyclerView.Adapter<PlayerHandAdapter.Crd> {
    private static final String TAGHandSetter = "PlayerHandAdapter";
    ArrayList<Card> Gen = new ArrayList<>();
    public PlayerHandAdapter(ArrayList<Card> Gen) {
        this.Gen = Gen;
    }

    @NonNull
    @Override
    public PlayerHandAdapter.Crd onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cut_card,parent,false);
        return new Crd(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Crd holder, int position) {
        holder.Headline.setText         (Gen.get(position).                 getHeadline());
        holder.Picture. setImageResource(Gen.get(position).                 getPicture());
        holder.HP.      setText         (String.valueOf(Gen.get(position).  getHp()));
        holder.Power.   setText         (String.valueOf(Gen.get(position).  getPower()));
        holder.Cost.    setText         (String.valueOf(Gen.get(position).  getCost()));
        holder.Type.    setImageResource(Gen.get(position).                 getTypeSym());
        if (Gen.get(position).isGold()) {
            holder.Card.setBackgroundResource(R.drawable.card_background_gold);
        }
    }

    @Override
    public int getItemCount() {
        return Gen.size();
    }

    class Crd extends RecyclerView.ViewHolder {
        ConstraintLayout    Card;
        AutoResizeTextView  Headline;
        ImageView           Picture;
        TextView            HP;
        TextView            Power;
        TextView            Cost;
        ImageView           Type;

        public Crd(@NonNull View itemView) {
            super(itemView);
            Card    = itemView.findViewById(R.id.CutCard);
            Headline= itemView.findViewById(R.id.Headline);
            Picture = itemView.findViewById(R.id.Picture);
            HP      = itemView.findViewById(R.id.HP);
            Power   = itemView.findViewById(R.id.Power);
            Cost    = itemView.findViewById(R.id.Cost);
            Type    = itemView.findViewById(R.id.Type);
        }
    }



}
