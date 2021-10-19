package com.example.myapplication.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.R;

public class GameInfo {

    private static GameInfo instance;

    private int highscore;
    private int gamesplayed;
    
    public static GameInfo getInstance() {
        
        

        if (instance == null) {

            instance = new GameInfo();

        }
        return instance;


    }
    private GameInfo(){
        
        gamesplayed = 0;
        highscore = 0;
        
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public void setGamesplayed(int gamesplayed) {
        this.gamesplayed = gamesplayed;
    }

    public int getGamesplayed() {
        return gamesplayed;
    }

    public int getHighscore() {
        return highscore;
    }
}
