package com.example.mygame.card;

import com.example.mygame.R;

public enum CardQuirks {
    Brotherly_Help(0),
    Self_will(1);
    private final int res;

    CardQuirks(int id) {
        res = id;
    }

    public int get() {
        return res;
    }

    public int useCard() {
        switch (this) {
        }
        return 0;
    }
}