package com.example.mygame.card;

import android.content.Context;
import android.content.res.Resources;
import com.example.mygame.R;
import java.util.ResourceBundle;

public enum CardType {
    Human(0),
    Vegetation(1),
    Monster(2),
    Robot(3),
    Undead(4),
    Abstract(5);
    //Every(6);
    private final int res;

    CardType(int id) {
        res = id;
    }

    public int get() {
        return res;
    }

    public int useCard() {
        switch (this) {
            case Human:
                return R.string.type0;
            case Vegetation:
                return R.string.type1;
            case Monster:
                return R.string.type2;
            case Robot:
                return R.string.type3;
            case Undead:
                return R.string.type4;
            case Abstract:
                return R.string.type5;
            //case Every:
            //    return R.drawable.type_6;
        }
        return 0;
    }
}