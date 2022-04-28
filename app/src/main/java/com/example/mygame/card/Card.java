package com.example.mygame.card;

public class Card {
    //boolean minion;
    boolean gold = false;
    int cost;
    int hp;
    int power;
    int type;
    //int[] quirks = {0, 0};
    //TODO: Effects
    int[] effects = {0, 0};

    int Headline;
    int Lore;
    int Picture;
    int Feature;

    public Card(int cost, int power, int hp, CardType type, int Headline,int Lore, int Feature,  int Picture,boolean gold) {
        this.cost       = cost;
        this.hp         = hp;
        this.power      = power;
        this.type       = type.useCard();
        this.Headline   = Headline;
        this.Feature   = Feature;
        this.Lore       = Lore;
        this.Picture    = Picture;
        this.gold       = gold;
    }

    public void decreaseHp(int hp){
        this.hp-=hp;
        if (this.hp<0){
            this.hp=0;
        }
    }

    public void setHp(int hp){
        this.hp=hp;
    }

    public void setPower(int power){
        this.power=power;
    }

    public boolean isGold() {
        return gold;
    }

    public String getCost() {
        return String.valueOf(cost);
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public int[] getEffects() {
        return effects;
    }

    public int getHeadline() {
        return Headline;
    }

    public int getType() {
        return type;
    }

    public int getLore() {
        return Lore;
    }

    public int getPicture() {
        return Picture;
    }

    public int getFeature() {
        return Feature;
    }

}
