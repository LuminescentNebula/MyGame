package com.example.mygame.DBs;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Player implements Serializable {

    private String UID;

    private String Name;
    private int Money;
    private int Avatar;
    @PropertyName("allCards")
    private HashMap<String, Integer> AllCards = new HashMap<>();
    private List<CardSet> Sets;
    @Exclude
    private final int MaxSets = 9;

    public Player(String name, int avatar,int money,HashMap<String,Integer> allCards,ArrayList<CardSet> sets,String UID) {
        this.Name = name;
        this.Avatar = avatar;
        this.Money = money;
        this.AllCards = allCards;
        this.Sets = sets;
        this.UID=UID;
    }

    public void setMoney(int money) {
        Money = money;
    }
    public void setName(String name) {
        if (name != null) {
            if (name.length() > 25) {
                name = name.substring(0, 24);
            }
            Name = name;
        } else {
            Name = "NoName";
        }
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


    public String getUID() {
        return UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
    public CardSet getSet(int i){
        return Sets.get(i);
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

