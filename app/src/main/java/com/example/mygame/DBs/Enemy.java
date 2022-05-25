package com.example.mygame.DBs;

public class Enemy {
    private String UID;
    private String Name;
    private int Avatar;

    public Enemy(Enemy e) {
        this.UID    = e.getUID();
        this.Name   = e.getName();
        this.Avatar = e.getAvatar();
    }
    public Enemy() {
    }
    public String getUID() {
        return UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getAvatar() {
        return Avatar;
    }
    public void setAvatar(int avatar) {
        Avatar = avatar;
    }
}
