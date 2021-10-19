/*

Main menu screen

 */
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.model.GameInfo;
import com.example.myapplication.model.GameLogic;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    GameInfo gameInfo;
    GameLogic gl;

    private static final String GAME_PREF_NAME = "Games played";
    private static final String SCORE_PREF_NAME = "High score";
    private static final String PREFS_NAME = "OpFeatSets";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");

        gameInfo = GameInfo.getInstance();
        gameInfo.setGamesplayed(getGames(this));
        gameInfo.setHighscore(getHighScore(this));

        gl = GameLogic.getInstance();
        gl.setMines(OptionsActivity.getMines(this));
        if(OptionsActivity.getBoard(this).equals("4x6")) gl.setBoardsize(0);
        if(OptionsActivity.getBoard(this).equals("5x10")) gl.setBoardsize(1);
        if(OptionsActivity.getBoard(this).equals("6x15")) gl.setBoardsize(2);

        Button help = (Button) findViewById(R.id.btn_mm_help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(add);
            }
        });

        Button options = (Button) findViewById(R.id.btn_mm_options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(add);
            }
        });

        Button playGame = (Button) findViewById(R.id.btn_mm_playGame);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newGame = new Intent(MainActivity.this, GameActivity.class);
                startActivity(newGame);
            }
        });

    }

    protected void onStart() {

        refreshGP();
        super.onStart();

    }

    private void refreshGP() {

        gameInfo = GameInfo.getInstance();

        saveGames(gameInfo.getGamesplayed());
        saveHighScore(gameInfo.getHighscore());

        gl.setMines(OptionsActivity.getMines(this));
        if(OptionsActivity.getBoard(this).equals("4x6")) gl.setBoardsize(0);
        if(OptionsActivity.getBoard(this).equals("5x10")) gl.setBoardsize(1);
        if(OptionsActivity.getBoard(this).equals("6x15")) gl.setBoardsize(2);

        TextView g_played = (TextView) findViewById(R.id.txt_mm_gamesPlayed);
        String gp = "Games played: " + Integer.toString(getGames(this));
        g_played.setText(gp);

    }
    private void saveGames(int gamesPlayed) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(GAME_PREF_NAME, gamesPlayed);
        editor.apply();
    }

    static public int getGames(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        int defaultValue = context.getResources().getInteger(R.integer.default_games);
        return prefs.getInt(GAME_PREF_NAME, defaultValue);
    }

    private void saveHighScore(int score) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(SCORE_PREF_NAME, score);
        editor.apply();
    }


    static public Integer getHighScore(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        Integer defaultValue = context.getResources().getInteger(R.integer.default_score);
        return prefs.getInt(SCORE_PREF_NAME, defaultValue);
    }


}
