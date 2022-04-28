package com.example.mygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.adapters.CollectionAdapter;
import com.example.mygame.adapters.SetAdapter;
import com.example.mygame.card.Card;
import com.example.mygame.DBs.CardSet;
import com.example.mygame.DBs.Player;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.lb.auto_fit_textview.AutoResizeTextView;
import com.rasi.clickableadapter.OnViewHolderClickListener;

import java.util.HashMap;
import java.util.Objects;


public class Card_Collection extends AppCompatActivity implements OnViewHolderClickListener {
    private static final String TAG   = "CardCollection";
    private Player player;
    private RecyclerView recyclerView;
    private CardSet cardSet;
    private SetAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_collection);
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

        Intent intent = getIntent();
        Gson gson = new Gson();
        player = gson.fromJson(intent.getStringExtra("player"), Player.class);
        Log.d(TAG,"Allcards :"+player.getAllCards().toString());

        recyclerView = findViewById(R.id.CollectionRecyclerView);

        ConstraintLayout pop = findViewById(R.id.PopUp);
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        //Stroke
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(this, R.color.second_background));
        shapeDrawable.setStroke(5.0f, ContextCompat.getColor(this, R.color.black));
        //Set
        ViewCompat.setBackground(pop, shapeDrawable);

        updateSetsButtons();

        ImageButton back = findViewById(R.id.GoBack);
        View.OnClickListener backClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        back.setOnClickListener(backClick);
        //TODO: Анимация кнопкам

        ImageButton plus = findViewById(R.id.Plus);
        View.OnClickListener plusClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.pop));
                createSet();
            }
        };
        plus.setOnClickListener(plusClick);

        ImageButton allCards = findViewById(R.id.AllCards);
        View.OnClickListener allCardsClick = v-> initRecyclerView(player.getAllCards());
        allCards.setOnClickListener(allCardsClick);
    }

    private void initRecyclerView(HashMap<String,Integer> set){ // отсылаются данные, которые нужно внести
        //TODO Оптимизировать recyclerView
        CollectionAdapter collectionAdapter = new CollectionAdapter(set,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(collectionAdapter);
    }

    private void deleteSet(){

    }
    private void createSet() {
        //TODO
        initRecyclerView(player.getAllCards());
        cardSet = new CardSet();
        ConstraintLayout popUp = findViewById(R.id.PopUpName);
        popUp.setVisibility(View.VISIBLE);
        TextInputEditText field = findViewById(R.id.NameInput);
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String name = field.getText().toString().trim();
                if (name.length()>25){
                    name = name.substring(0,24);
                }
                cardSet.setName(name);
                popUp.setVisibility(View.GONE);
                ConstraintLayout Filler = findViewById(R.id.SetFiller);
                Filler.setVisibility(View.VISIBLE);
                initSetRecyclerView();
            }
        });
        //set.put()

    }

    private void updateSetsButtons(){
        for (int i =1;i<=player.getSetsAmount();i++){
            ImageButton s = findViewById(getID(i));
            s.setVisibility(View.VISIBLE);
            int finalI = i;
            s.setOnClickListener(v-> initRecyclerView(player.getSets().get(finalI).getTheSet()));
        }
        for (int i =player.getSetsAmount()+1;i<=9;i++){
            ImageButton s = findViewById(getID(i));
            s.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"LifeCycle_"+"onPause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"LifeCycle_"+"onResume");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"LifeCycle_"+"onStart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"LifeCycle_"+"onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"LifeCycle_"+"onDestroy");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"LifeCycle_"+"onRestart");
    }

    @Override
    public void onClickAtItem(int i) {
        ConstraintLayout setFiller = findViewById(R.id.SetFiller);
        if (setFiller.getVisibility()==View.GONE) {
            ConstraintLayout pop_main = findViewById(R.id.PopUpMain);
            TextView description = findViewById(R.id.Description);
            ConstraintLayout Crd = findViewById(R.id.BiggerCard);

            if (pop_main.getVisibility() == View.GONE) {
                pop_main.setVisibility(View.VISIBLE);
            } else {
                pop_main.setVisibility(View.GONE);
            }

            AutoResizeTextView Headline = (AutoResizeTextView) Crd.findViewById(R.id.Headline);
            ImageView Picture = (ImageView) Crd.findViewById(R.id.Picture);
            TextView HP = (TextView) Crd.findViewById(R.id.HP);
            TextView Power = (TextView) Crd.findViewById(R.id.Power);
            TextView Cost = (TextView) Crd.findViewById(R.id.Cost);
            ImageView Type = (ImageView) Crd.findViewById(R.id.Type);
            AutoResizeTextView FeatureText = (AutoResizeTextView) Crd.findViewById(R.id.FeatureText);

            Headline.setText(Card.values()[i].getHeadline());
            Picture.setImageResource(Card.values()[i].getPicture());
            HP.setText(String.valueOf(Card.values()[i].getHp()));
            Power.setText(String.valueOf(Card.values()[i].getPower()));
            Cost.setText(String.valueOf(Card.values()[i].getCost()));
            Type.setImageResource(Card.values()[i].getTypeSym());
            FeatureText.setText(Card.values()[i].getFeature());

            description.setText(Card.values()[i].getLore());
            Log.d(TAG, "Pop Up Visibility");
        } else {
            View v = recyclerView.focusSearch(i);
            if (cardSet.add(i)) {
                v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop));
                AutoResizeTextView amount = findViewById(R.id.SetAmount);
                amount.setText(String.valueOf(cardSet.getCurrentSize()) +
                        "/"+
                        String.valueOf(cardSet.getMaxSize()));
                updateSetRecyclerViewItem();
                if (cardSet.getReady()){
                    //TODO Music сигнал завершения
                    player.addSet(cardSet);
                    setFiller.setVisibility(View.GONE);
                    amount.setText(0 +"/"+cardSet.getMaxSize());
                    updateSetsButtons();
                }
            } else {
                v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
            }
        }
    }

    private void initSetRecyclerView() {
        RecyclerView setRecyclerView = findViewById(R.id.SetRecyclerView);
        sAdapter = new SetAdapter(cardSet.getTheSetList());
        setRecyclerView.setAdapter(sAdapter);
    }
    private void  updateSetRecyclerViewItem() {
        sAdapter.notifyItemRangeInserted(cardSet.getCurrentSize()-1, 1);
    }

    private int getID(int i){
        switch (i){
            case 1: return R.id.set1;
            case 2: return R.id.set2;
            case 3: return R.id.set3;
            case 4: return R.id.set4;
            case 5: return R.id.set5;
            case 6: return R.id.set6;
            case 7: return R.id.set7;
            case 8: return R.id.set8;
            case 9: return R.id.set9;
        }
        return 0;
    }
    private int getSRC(int i){
        switch (i){
            case 1: return R.drawable.ic_baseline_filter_1_24;
            case 2: return R.drawable.ic_baseline_filter_2_24;
            case 3: return R.drawable.ic_baseline_filter_3_24;
            case 4: return R.drawable.ic_baseline_filter_4_24;
            case 5: return R.drawable.ic_baseline_filter_5_24;
            case 6: return R.drawable.ic_baseline_filter_6_24;
            case 7: return R.drawable.ic_baseline_filter_7_24;
            case 8: return R.drawable.ic_baseline_filter_8_24;
            case 9: return R.drawable.ic_baseline_filter_9_24;
        }
        return 0;
    }


}
