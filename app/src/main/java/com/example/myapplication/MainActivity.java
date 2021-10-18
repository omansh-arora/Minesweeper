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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");

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

        TextView g_played = (TextView) findViewById(R.id.txt_mm_gamesPlayed);
        String gp = "Games played: " + Integer.toString(GameActivity.getGames(this));
        g_played.setText(gp);

    }


}
