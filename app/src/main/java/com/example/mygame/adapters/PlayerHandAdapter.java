package com.example.mygame.adapters;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mygame.R;
import com.example.mygame.card.Card;
import com.lb.auto_fit_textview.AutoResizeTextView;
import com.rasi.clickableadapter.BaseAdapter;
import com.rasi.clickableadapter.OnViewHolderClickListener;

import java.util.Arrays;
import java.util.List;

public class PlayerHandAdapter extends BaseAdapter<Card> {
    private static final String TAGHandSetter = "PlayerHandSetter";
    public PlayerHandAdapter(@NonNull List Gen, @Nullable OnViewHolderClickListener itemClickListener) {
        super(Gen, itemClickListener);
    }

    @Override
    protected int getItemView() {
        return R.layout.cut_card;
    }

    @Override
    protected int[] getResIdOfInflatedViews() {
        return new int[]{
                R.id.CutCard, R.id.Headline, R.id.Picture, R.id.HP,
                R.id.Power, R.id.Cost, R.id.Type //R.id.FeatureText,
                //R.id.Effect1, //R.id.Effect2
        } ;
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableViewHolder holder, int position) {
        final Card item = getItem(position);
        ConstraintLayout    Card        =  (ConstraintLayout)holder.getViewById(R.id.CutCard);
        AutoResizeTextView  Headline    =  (AutoResizeTextView)holder.getViewById(R.id.Headline);
        ImageView           Picture     =  (ImageView)holder.getViewById(R.id.Picture);
        TextView            HP          =  (TextView)holder.getViewById(R.id.HP);
        TextView            Power       =  (TextView)holder.getViewById(R.id.Power);
        TextView            Cost        =  (TextView)holder.getViewById(R.id.Cost);
        ImageView           Type        =  (ImageView)holder.getViewById(R.id.Type);
        //AutoResizeTextView  FeatureText =  (AutoResizeTextView)holder.getViewById(R.id.FeatureText);
        //ImageView           Effect1     =  (ImageView)holder.getViewById(R.id.Effect1);
        //ImageView           Effect2     =  (ImageView)holder.getViewById(R.id.Effect2);

        Headline.setText         (item.                   getHeadline());
        Picture. setImageResource(item.                   getPicture());
        HP.      setText         (String.valueOf(item.    getHp()));
        Power.   setText         (String.valueOf(item.    getPower()));
        Cost.    setText         (String.valueOf(item.    getCost()));
        Type.    setImageResource (item.                   getTypeSym());
        //FeatureText.setText      (item.                   getFeature());
        if (item.isGold()) {
            Card.setBackgroundResource(R.drawable.card_background_gold);
        }
        Log.i(TAGHandSetter,"Card "+position+ "set");

    }
}
