package com.example.mygame.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.card.Card;

import me.grantland.widget.AutofitTextView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerCard> {

    private static Card[] cards;       //Static?
    private static final String TAGHandSetter = "PlayerHandSetter";

    public PlayerAdapter(Card[] cards) {
        this.cards=cards;
    }

    @NonNull
    @Override
    public PlayerCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new PlayerCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerCard holder, int position) {
        holder.Headline.setText         (cards[position].                   getHeadline());
        holder.Picture. setImageResource(cards[position].                   getPicture());
        holder.HP.      setText         (String.valueOf(cards[position].    getHp()));
        holder.Power.   setText         (String.valueOf(cards[position].    getPower()));
        holder.Cost.    setText         (String.valueOf(cards[position].    getCost()));
        holder.Type.    setText         (cards[position].                   getType());
        holder.FeatureText.setText      (cards[position].                   getFeature());
        Log.i(TAGHandSetter,"Card "+position+ "set");
        /*
        holder.Effect1.setImageResource();
        holder.Effect2.setImageResource();
        holder.Quirk1.setText();
        holder.Quirk2.setText();
        */
    }

    @Override
    public int getItemCount() {
        return cards.length;
    }

    /*private void addItem(Card item) {
        cards.add(item);
        MyAdapter.notifyDataSetChanged();
    }*/

    class PlayerCard extends RecyclerView.ViewHolder {
        AutofitTextView     Headline;
        ImageView           Picture;
        TextView            HP;
        TextView            Power;
        TextView            Cost;
        AutofitTextView     Type;
        AutofitTextView     FeatureText;
        ImageView           Effect1;
        ImageView           Effect2;
        //AutofitTextView     Quirk1;
        //AutofitTextView     Quirk2;

        public PlayerCard(@NonNull View itemView) {
            super(itemView);
            Headline    = itemView.findViewById(R.id.Headline);
            Picture     = itemView.findViewById(R.id.Picture);
            HP          = itemView.findViewById(R.id.HP);
            Power       = itemView.findViewById(R.id.Power);
            Cost        = itemView.findViewById(R.id.Cost);
            Type        = itemView.findViewById(R.id.Type);
            FeatureText = itemView.findViewById(R.id.FeatureText);
            Effect1     = itemView.findViewById(R.id.Effect1);
            Effect2     = itemView.findViewById(R.id.Effect2);
            //Quirk1      = itemView.findViewById(R.id.Quirk1);
           // Quirk2      = itemView.findViewById(R.id.Quirk2);
        }
    }
}
