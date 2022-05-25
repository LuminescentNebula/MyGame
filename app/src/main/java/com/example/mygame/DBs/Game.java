package com.example.mygame.DBs;

import com.example.mygame.card.Card;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;

public class Game {
    @Exclude
    final static int  CardAmount=4;

    private String id;
    private String Player;
    private String Enemy=null;
    ArrayList<Card> FieldPlayerCards = new ArrayList<>();
    ArrayList<Card> FieldEnemyCards  = new ArrayList<>();

    public Game(String id, String player) {
        this.id = id;
        Player = player;
    }

    public Game() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPlayer() {
        return Player;
    }
    public void setPlayer(String player) {
        Player = player;
    }
    public String getEnemy() {
        return Enemy;
    }
    public void setEnemy(String enemy) {
        Enemy = enemy;
    }
    public void change(){
        String buff = Enemy;
        Enemy = Player;
        Player =buff;
    }
    public ArrayList<Card> getFieldPlayerCards() {
        return FieldPlayerCards;
    }
    public void setFieldPlayerCards(ArrayList<Card> fieldPlayerCards) {
        FieldPlayerCards = fieldPlayerCards;
    }
    public ArrayList<Card> getFieldEnemyCards() {
        return FieldEnemyCards;
    }
    public void setFieldEnemyCards(ArrayList<Card> fieldEnemyCards) {
        FieldEnemyCards = fieldEnemyCards;
    }
    public boolean addFieldPlayerCards(Card card){
        if (FieldPlayerCards.size()<CardAmount){
            FieldPlayerCards.add(card);
            return true;
        }
        return  false;
    }
    public boolean addFieldEnemyCards(Card card){
        if (FieldEnemyCards.size()<CardAmount){
            FieldEnemyCards.add(card);
            return true;
        }
        return  false;
    }

}
