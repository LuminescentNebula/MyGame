package com.example.mygame.card;

import com.example.mygame.R;

import java.util.ArrayList;

//Everything should be get from here
public class AllCards {
    public static Card[] PreGen={ //Массив карт для демонстрации
            new Card(1,3,5, CardType.Human,
                    R.string.headline001,R.string.lore001,R.string.features001,
                    R.drawable.card_001_viking,false),
            new Card(2,2,4,CardType.Undead,
                    R.string.headline002,R.string.lore002,R.string.features002,
                    R.drawable.card_002_vampire,false),
            new Card(3,1,3,CardType.Monster,
                    R.string.headline003,R.string.lore003,R.string.features003,
                    R.drawable.card_003_bird,false),
            new Card(4,2,3,CardType.Human,
                    R.string.headline004,R.string.lore004,R.string.features004,
                    R.drawable.card_004_souleater,false),
            new Card(5,3,3,CardType.Vegetation,
                    R.string.headline005,R.string.lore005,R.string.features005,
                    R.drawable.card_005_mandrake,false),
            new Card(6,2,2,CardType.Human,
                    R.string.headline006,R.string.lore006,R.string.features006,
                    R.drawable.card_006_instructor,false),
            new Card(7,1,4,CardType.Monster,
                    R.string.headline007,R.string.lore007,R.string.features007,
                    R.drawable.card_007_ghidorah,false),
            new Card(8,0,7,CardType.Human,
                    R.string.headline008,R.string.lore008,R.string.features008,
                    R.drawable.card_008_chad,false),
            new Card(9,5,1,CardType.Human,
                    R.string.headline009,R.string.lore009,R.string.features009,
                    R.drawable.card_009_hackerman,false)
    };

    ArrayList<Card> Cards = new ArrayList<Card>();  //Pregen
    //ArrayList<Quirk> Quirks =  new ArrayList<Quirk>();
    //ArrayList<Effect> Effects =  new ArrayList<Effect>();
    ArrayList<Spell> Spells =  new ArrayList<Spell>();
}
//TODO: Getters