package com.example.mygame.card;

import java.util.ArrayList;

public class CardSet {
    final int MaxSize = 12;
    String Name;
    ArrayList<Card> Set = new ArrayList<Card>();

    public CardSet(String name) {
        Name = name;
    }
    //TODO: Boolean?
    public void addCard(Card card){
        Set.add(card);
    }

    //Int or Card
    public void delCard(){
        //Set.remove()
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}