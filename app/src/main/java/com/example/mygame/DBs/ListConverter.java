package com.example.mygame.DBs;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ListConverter {
    @TypeConverter
    public String fromList(List<CardSet> sets) {
        Gson gson = new Gson();
        return gson.toJson(sets);
    }

    @TypeConverter
    public List<CardSet> toList(String sets) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(sets,CardSet[].class));
    }
}
