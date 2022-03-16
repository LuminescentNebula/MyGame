package com.example.mygame;


public class Card {
    //boolean minion;
    boolean gold = false;
    int cost;
    int hp;
    int power;
    private final CardType type;
    int[] quirks = {0, 0};
    int[] effects = {0, 0};
    //Почему R.string и R.drawable - это int?

    int Headline;
    int Lore;
    int Picture;

    public Card(int cost, int power, int hp, CardType type, int Headline, int Lore, int Picture) {
        this.cost = cost;
        this.hp = hp;
        this.power = power;
        this.type = type;
        this.Headline = Headline;
        this.Lore = Lore;
        this.Picture = Picture;
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

    public int[] getQuirks() {
        return quirks;
    }

    public int[] getEffects() {
        return effects;
    }


    public int getHeadline() {
        return Headline;
    }

    public CardType getType() {
        return type;
    }

    public int getLore() {
        return Lore;
    }

    public int getPicture() {
        return Picture;
    }
}

