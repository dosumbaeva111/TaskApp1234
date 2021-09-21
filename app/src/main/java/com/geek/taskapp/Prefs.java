package com.geek.taskapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        this.preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
        preferences.edit().putBoolean("boardIsShown", true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("boardIsShown", false);
    }

    public void saveImage(String image){
        preferences.edit().putString("image",image).apply();
    }

    public String getImage(){
        return preferences.getString("image",null);
    }

    public void deleteImg() {
        preferences.edit().remove("image").apply();
    }

    public void saveUserData(String userData){
        preferences.edit().putString("userName", userData).apply();
    }

    public String getUserName(){
        return preferences.getString("userName", null);
    }

    public void deleteUserName() {
        preferences.edit().remove("userName").apply();
    }

}
