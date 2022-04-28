package com.example.mygame.card;

import com.example.mygame.R;

public enum Card {
    Viking(1,1, 3, 5, CardType.Human,
            R.string.headline001, R.string.lore001, R.string.features001,
            R.drawable.card_001_viking, false),
    Vampire(2,2, 2, 4, CardType.Undead,
            R.string.headline002, R.string.lore002, R.string.features002,
            R.drawable.card_002_vampire, false),
    Bird(3,3, 1, 3, CardType.Monster,
            R.string.headline003, R.string.lore003, R.string.features003,
            R.drawable.card_003_bird, false),
    Souleater(4,4, 2, 3, CardType.Human,
            R.string.headline004, R.string.lore004, R.string.features004,
            R.drawable.card_004_souleater, false),
    Mandrake(5,5, 3, 3, CardType.Vegetation,
            R.string.headline005, R.string.lore005, R.string.features005,
            R.drawable.card_005_mandrake, false),
    Instructor(6,6, 2, 2, CardType.Human,
            R.string.headline006, R.string.lore006, R.string.features006,
            R.drawable.card_006_instructor, false),
    Ghidorah(7,7, 1, 4, CardType.Monster,
            R.string.headline007, R.string.lore007, R.string.features007,
            R.drawable.card_007_ghidorah, false),
    Chad(8,8, 0, 3, CardType.Human,
            R.string.headline008, R.string.lore008, R.string.features008,
            R.drawable.card_008_chad, false),
    Hackerman(9,9, 5, 1, CardType.Human,
            R.string.headline009, R.string.lore009, R.string.features009,
            R.drawable.card_009_hackerman, false),
    Cleaner(10,2,2,2,CardType.Robot,R.string.headline010, R.string.lore010, R.string.features010,
            R.drawable.card_010_cleaner,false),
    Demon(11,3,3,3,CardType.Monster,R.string.headline011, R.string.lore011, R.string.features011,
            R.drawable.card_011_demon,false),
    Hamster(12,1,1,1,CardType.Monster,R.string.headline012, R.string.lore012, R.string.features012,
            R.drawable.card_012_hamster,false),
    Inquisition(13,3,1,6,CardType.Human,R.string.headline013, R.string.lore013, R.string.features013,
            R.drawable.card_013_inquisition,false),
    Kosar(14,3,0,6,CardType.Undead,R.string.headline014, R.string.lore014, R.string.features014,
            R.drawable.card_014_kosar,false),
    Printer(15,2,2,1,CardType.Robot,R.string.headline015, R.string.lore015, R.string.features015,
            R.drawable.card_015_printer,false),
    Raven(16,2,2,1,CardType.Monster,R.string.headline016, R.string.lore016, R.string.features016,
            R.drawable.card_016_raven,false),
    Chupacabra(17,2,2,1,CardType.Monster,R.string.headline017, R.string.lore017, R.string.features017,
            R.drawable.card_017_what,false),
    Aztec(18,2,2,1,CardType.Human,R.string.headline018, R.string.lore018, R.string.features018,
            R.drawable.card_018_aztec,true);

    public long id;
    //boolean minion;
    boolean gold;
    int cost;
    int hp;
    int power;
    CardType type;
    //int[] quirks = {0, 0};
    //TODO: Effects
    int effect1;
    int effect2;

    int Headline;
    int Lore;
    int Picture;
    int Feature;

    Card(int id,int cost, int power, int hp, CardType type, int Headline, int Lore, int Feature, int Picture, boolean gold) {
        this.id = id;
        this.cost = cost;
        this.hp = hp;
        this.power = power;
        this.type = type;
        this.Headline = Headline;
        this.Feature = Feature;
        this.Lore = Lore;
        this.Picture = Picture;
        this.gold = gold;
    }

    Card(Card card){
        this.id = card.id;
        this.cost = card.cost;
        this.hp = card.hp;
        this.power = card.power;
        this.type = card.type;
        this.Headline = card.Headline;
        this.Feature = card.Feature;
        this.Lore = card.Lore;
        this.Picture = card.Picture;
        this.gold = card.gold;
    }

    public long getId() {
        return id;
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

    public int getHeadline() {
        return Headline;
    }

    public int getType() {
        return type.useCard();
    }
    public int getTypeSym(){
        return type.symCard();
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
