package com.example.mygame.DBs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {Player.class,CardSet.class})
public abstract class RoomDB extends RoomDatabase {
    public abstract PlayerDao playerDao();
}
