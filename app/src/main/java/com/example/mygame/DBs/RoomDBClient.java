package com.example.mygame.DBs;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class RoomDBClient {
    private RoomDB db;
    private Context context;
    private static RoomDBClient instance;

    private RoomDBClient(Context context){
        this.context=context;
        db = Room.databaseBuilder(context,RoomDB.class,"Player").build();
    }

    public static RoomDBClient getInstance(Context context){
        if (instance == null){
            instance =  new RoomDBClient(context);
        }
        return instance;
    }

    public RoomDB getDB(){
        return db;
    }

    public void updateDB(Player player){
        class updating extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                getDB().playerDao().update(player);
                return null;
            }
        }
        updating up = new updating();
        up.execute();
    }

//    public Player getPlayer(Player player){
//         class updating extends AsyncTask<Void,Void,Player> {
//            @Override
//            protected Player doInBackground(Void... voids) {
//                RoomDBClient.getInstance(instance.context).getPlayer();
//                return null;
//            }
//
//             @Override
//             protected void onPostExecute(Player player) {
//                 super.onPostExecute(player);
//             }
//         }
//        updating up = new updating();
//        return up.execute();
//    }
//    private Player getPlayer(){
//        return getDB().playerDao().getPlayer();
//    }
}
