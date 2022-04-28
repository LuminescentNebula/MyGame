package com.example.mygame.DBs;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import java.util.HashMap;

public class HashMapConverter {

    @TypeConverter
    public String fromMap(HashMap<String,Integer> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @TypeConverter
    public HashMap<String,Integer> toMap(String map) {
        Gson gson = new Gson();
        return gson.fromJson(map,HashMap.class);
    }

}