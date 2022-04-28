package com.example.mygame.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mygame.R;
import com.example.mygame.card.Card;
import com.lb.auto_fit_textview.AutoResizeTextView;
import com.rasi.clickableadapter.BaseAdapter;
import com.rasi.clickableadapter.OnViewHolderClickListener;

import java.util.Arrays;
import java.util.HashMap;

public class CollectionAdapter extends BaseAdapter<Card> {
    private HashMap<String,Integer> playerCards;
    private final String TAG = "CollectionAdapter";
    public CollectionAdapter(@NonNull HashMap<String,Integer> playerCards, @Nullable OnViewHolderClickListener itemClickListener) {
        super(Arrays.asList(Card.values()), itemClickListener);
        this.playerCards = playerCards;
    }

    @Override
    protected int getItemView() {
        return R.layout.card;
    }

    @Override
    protected int[] getResIdOfInflatedViews() {
        return new int[]{
                R.id.Headline, R.id.Picture, R.id.HP,
                R.id.Power, R.id.Cost, R.id.Type, R.id.FeatureText, R.id.Amount
        } ;
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableViewHolder holder, int position) {
        final Card item = getItem(position);
        AutoResizeTextView  Headline    =  (AutoResizeTextView)holder.getViewById(R.id.Headline);
        ImageView           Picture     =  (ImageView)holder.getViewById(R.id.Picture);
        TextView            HP          =  (TextView)holder.getViewById(R.id.HP);
        TextView            Power       =  (TextView)holder.getViewById(R.id.Power);
        TextView            Cost        =  (TextView)holder.getViewById(R.id.Cost);
        ImageView           Type        =  (ImageView)holder.getViewById(R.id.Type);
        AutoResizeTextView  FeatureText =  (AutoResizeTextView)holder.getViewById(R.id.FeatureText);
        AutoResizeTextView  Amount      =  (AutoResizeTextView)holder.getViewById(R.id.Amount);

        Headline.setText         (item.                   getHeadline());
        Picture. setImageResource(item.                   getPicture());
        HP.      setText         (String.valueOf(item.    getHp()));
        Power.   setText         (String.valueOf(item.    getPower()));
        Cost.    setText         (String.valueOf(item.    getCost()));
        Type.    setImageResource(item.                   getTypeSym());
        FeatureText.setText      (item.                   getFeature());
        Amount.setVisibility     (View.VISIBLE);

        Log.d(TAG,playerCards.toString());
        if (playerCards.containsKey(String.valueOf(position))){
            Amount.setText(String.valueOf(playerCards.get(String.valueOf(position))));
        } else {
            Amount.setText("0");
        }
    }
}

