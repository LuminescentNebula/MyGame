package com.example.mygame.DBs;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Player implements Serializable {
    @NonNull @PrimaryKey @Exclude
    private String UID;

    private String Name;
    private int Money;
    private int Avatar;
    @PropertyName("allCards") @TypeConverters({HashMapConverter.class})
    private HashMap<String, Integer> AllCards = new HashMap<>();
    @PropertyName("sets") @TypeConverters({ListConverter.class})
    private List<CardSet> Sets = new ArrayList<>();
    @Ignore @Exclude
    private final int MaxSets = 9;

    public Player(String name, int avatar,int money,HashMap<String,Integer> allCards,List<CardSet> sets,String UID) {
        this.Name = name;
        this.Avatar = avatar;
        this.Money = money;
        this.AllCards = allCards;
        this.Sets = sets;
        this.UID = UID;
    }

    public void setMoney(int money) {
        Money = money;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setAvatar(int avatar) {
        Avatar = avatar;
    }
    public void addCards(HashMap<String,Integer> moreCards){
        for (String i: moreCards.keySet()){
            if (AllCards.containsKey(i)){
                int a =AllCards.get(i)+moreCards.get(i);
                if (a>9) a=9;
                AllCards.put(i,a);
            } else {
                AllCards.put(i,moreCards.get(i));
            }
        }
    }
    public void addMoney(int Money){
        this.Money+=Money;
    }
    public void addSet(CardSet cardSet){
        if (Sets== null){
            Sets =  new ArrayList<>();
        }
        Sets.add(cardSet);
    }
    @Exclude
    public int getSetsAmount() {
        if (Sets!=null) {
            return Sets.size();
        } else {
            return 0;
        }
    }
    public CardSet getSet(int i){
        return Sets.get(i);
    }
    @Exclude
    public String getUID() {
        return UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
    public Player() { }
    public String getName() {
        return Name;
    }
    public int getMoney() {
        return Money;
    }
    public int getAvatar() {
        return Avatar;
    }
    public HashMap<String, Integer> getAllCards() {
        return AllCards;
    }
    public List<CardSet> getSets() {
        return Sets;
    }
    public void setAllCards(HashMap<String, Integer> allCards) {
        AllCards = allCards;
    }
    public void setSets(List<CardSet> sets) {
        Sets = sets;
    }
    @Exclude
    public int getMaxSets() {
        return MaxSets;
    }
}

