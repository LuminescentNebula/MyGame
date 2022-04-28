package com.example.mygame.DBs;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class CardSet implements Serializable {
    @PrimaryKey @Exclude
    private int id;

    private String Name="";             //Наименование колоды
    private int CurrentSize = 0;        //Текущее количество карт в колоде
    private boolean Ready = false;
    @TypeConverters({HashMapConverter.class})
    HashMap<String,Integer> TheSet = new HashMap<>();
    @Ignore @Exclude
    final int MaxSize = 10;

    public CardSet(String name,HashMap<String,Integer> set) {
        this.Name=name;
        this.TheSet=set;
    }
    public CardSet(String name,ArrayList<Integer> set) {
        this.Name=name;
        HashMap<String,Integer> MapSet = new HashMap<>();
        for (int i =0; i<set.size();i++){
            if (MapSet.containsKey(String.valueOf(set.get(i)))){
                MapSet.put(String.valueOf(set.get(i)),MapSet.get(String.valueOf(set.get(i)))+1);
            } else {
                MapSet.put(String.valueOf(set.get(i)),1);
            }
        }
        this.TheSet=MapSet;
    }

    public boolean add(int Card) {
        if (CurrentSize < MaxSize) {
            if (TheSet.containsKey(String.valueOf(Card))) {
                TheSet.put(String.valueOf(Card), TheSet.get(Card) + 1);
            } else {
                TheSet.put(String.valueOf(Card), 1);
            }
            if (CurrentSize == MaxSize) {
                Ready = true;
            }
            CurrentSize++;
            return true;
        } else {
            return false;
        }
    }

//    public boolean del(int Card) {
//        if (CurrentSize > 0) {
//            if (TheSet.containsKey(String.valueOf(Card))) {
//                if (TheSet.get(String.valueOf(Card)) > 0) {
//                    TheSet.put(String.valueOf(Card), TheSet.get(Card) - 1);
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//            if (CurrentSize < MaxSize) {
//                Ready = false;
//            }
//            CurrentSize--;
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public boolean checkAllCards() {
//        //TODO
//        if (true) {
//            return true;
//        } else{
//            return  false;
//        }
//    }

//    public List<Card> get() {
//        //Для каждого ключа создаётся n объектов Card
//        List<Card> returnable = null;
//        String[] keys =(String[]) TheSet.keySet().toArray();
//        for (String i:keys) {
//            for (Card a:Card.values()) {
//                if(a.getId()==Long.parseLong(i)){
//                    for (int j=0;j<TheSet.get(i);j++){
//                        returnable.add(a);
//                    }
//                }
//            }
//        }
//        return returnable;
//    }

    public void setName(String name) {
        Name = name;
    }
    public CardSet(){}
    public String getName() {
        return Name;
    }
    public int getCurrentSize() {
        return CurrentSize;
    }
    public boolean getReady() {
        return Ready;
    }
    @Exclude
    public int getMaxSize() {
        return MaxSize;
    }
    @Exclude
    public ArrayList<Integer> getTheSetList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (String i:TheSet.keySet()){
            for (int j=0;j<TheSet.get(i);j++){
                list.add(Integer.parseInt(i));
            }
        }
        return list;
    }

    @Exclude
    public int getId() {
        return id;
    }
    @Exclude
    public void setId(int id) {
        this.id = id;
    }
    public void setCurrentSize(int currentSize) {
        CurrentSize = currentSize;
    }
    public void setReady(boolean ready) {
        Ready = ready;
    }
    public HashMap<String,Integer> getTheSet() {
        return TheSet;
    }
    public void setTheSet(HashMap<String, Integer> theSet) {
        TheSet = theSet;
    }
}
