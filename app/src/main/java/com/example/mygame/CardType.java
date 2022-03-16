package com.example.mygame;

public enum CardType {
    Vegetation(0),
    Monster(1),
    Robot(2),
    Undead(3),
    Abstract(4),
    Human(5);
    private final String resourceName;
    CardType(int id) {
        resourceName = "type" + id;
    }
    public String getResourceName(){
        return resourceName;
    }
}

class SomeClass {
    public void some() {
        useCard(CardType.Undead);
    }
    public void useCard(CardType value) {
        switch (value) {
            case Vegetation:
                break;
            case Monster:
                break;
            case Robot:
                break;
            case Undead:
                break;
            case Abstract:
                break;
            case Human:
                break;
        }
    }
}