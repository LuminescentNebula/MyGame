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

public class FieldAdapter extends BaseAdapter<Card> {
    private static final String TAGHandSetter = "PlayerFieldSetter";
    public FieldAdapter(@NonNull List Gen, @Nullable OnViewHolderClickListener itemClickListener) {
        super(Gen, itemClickListener);
    }

    @Override
    protected int getItemView() {
        return R.layout.mini_card;
    }

    @Override
    protected int[] getResIdOfInflatedViews() {
        return new int[]{
                R.id.MiniCard, R.id.MiniPicture, R.id.MiniHP,
                R.id.MiniPower,R.id.Effect1, R.id.Effect2
        } ;
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableViewHolder holder, int position) {
        final Card item = getItem(position);
        ConstraintLayout Card        =  (ConstraintLayout)holder.getViewById( R.id.MiniCard);
        ImageView Picture     =  (ImageView)holder.getViewById(R.id.MiniPicture);
        TextView HP          =  (TextView)holder.getViewById(R.id.MiniHP);
        TextView            Power       =  (TextView)holder.getViewById(R.id.MiniPower);
        ImageView           Effect1     =  (ImageView)holder.getViewById(R.id.Effect1);
        ImageView           Effect2     =  (ImageView)holder.getViewById(R.id.Effect2);

        Picture. setImageResource(item.                   getPicture());
        HP.      setText         (String.valueOf(item.    getHp()));
        Power.   setText         (String.valueOf(item.    getPower()));
        if (item.isGold()) {
            Card.setBackgroundResource(R.drawable.card_background_gold);
        }
        Log.i(TAGHandSetter,"Card "+position+ "set");

    }
}
