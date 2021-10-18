package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    private static final String MINE_PREF_NAME = "Num mines";
    private static final String BOARD_PREF_NAME = "Board size";
    private static final String PREFS_NAME = "AppPrefs";
    public static int MINES;
    public static int BOARDSIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        MINES = getMines(this);
        if(getBoard(this).equals("4x6")) BOARDSIZE = 0;
        if(getBoard(this).equals("5x10")) BOARDSIZE = 1;
        if(getBoard(this).equals("6x15")) BOARDSIZE = 2;

        createRadioButtons();

    }

    private void createRadioButtons() {
        RadioGroup board_group = (RadioGroup) findViewById(R.id.radio_group_board_size);
        RadioGroup mines_group = (RadioGroup) findViewById(R.id.radio_group_mines);

        String[] board_Size = getResources().getStringArray(R.array.num_board_size);
        int[] number_of_mines = getResources().getIntArray(R.array.num_mines_size);

        // Create the buttons
        for(int i = 0; i < board_Size.length; i++) {
            final String boardSize = board_Size[i];

            RadioButton board_button = new RadioButton(this);
            board_button.setText(boardSize);

            // Set on-click callbacks
            int finalI = i;
            board_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, "Size " + boardSize,
                            Toast.LENGTH_SHORT).show();
                    saveBoard(boardSize);
                    BOARDSIZE = finalI;
                }
            });

            // Add to radio group:
            board_group.addView(board_button);

            // Set default button:
            if (boardSize.equals(getBoard(this))) {
                board_button.setChecked(true);
            }

        }

        for(int j = 0; j < number_of_mines.length; j++) {
            final int numOfMines = number_of_mines[j];

            RadioButton mine_button = new RadioButton(this);
            mine_button.setText(getString(R.string.num_of_mines, numOfMines));


            mine_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, numOfMines + " mines",
                            Toast.LENGTH_SHORT).show();
                    saveMines(numOfMines);
                    MINES = numOfMines;

                }
            });

            // Add to radio group:
            mines_group.addView(mine_button);

            // Select default button:
            if (numOfMines == getMines(this)) {
                mine_button.setChecked(true);
            }
        }
    }

    private void saveMines(int numOfMines) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        MINES = numOfMines;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(MINE_PREF_NAME, numOfMines);
        editor.apply();
    }

    static public int getMines(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        int defaultValue = context.getResources().getInteger(R.integer.default_mines);
        return prefs.getInt(MINE_PREF_NAME, defaultValue);
    }

    private void saveBoard(String boardSize) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(BOARD_PREF_NAME, boardSize);
        editor.apply();
    }

    static public String getBoard(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String defaultValue = context.getResources().getString(R.string.default_boardSize);
        return prefs.getString(BOARD_PREF_NAME, defaultValue);
    }
}