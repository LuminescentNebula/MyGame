package com.example.mygame.DBs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player LIMIT 1")
    Player getPlayer();

//    @Query("SELECT UID, Name, Avatar FROM player LIMIT 1")
//    Player getPlayerData();

    @Query("SELECT Sets FROM player LIMIT 1")
    String getSets();

    @Update
    void update(Player p);

    @Insert
    void insert(Player p);

    @Delete
    void delete(Player p);
}
