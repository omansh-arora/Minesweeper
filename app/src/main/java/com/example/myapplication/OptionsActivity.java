package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createRadioButtons();
    }

    private void createRadioButtons() {
        RadioGroup board_group = (RadioGroup) findViewById(R.id.radio_group_board_size);
        RadioGroup mines_group = (RadioGroup) findViewById(R.id.radio_group_mines);

        int[] board_Size = getResources().getIntArray(R.array.num_board_size);
        int[] number_of_mines = getResources().getIntArray(R.array.num_mines_size);

        // Create the buttons
        for(int i = 0; i < board_Size.length; i++) {
            final int boardSize = board_Size[i];

            RadioButton board_button = new RadioButton(this);
            board_button.setText(getString(R.string.board_size, boardSize));

            // Set on-click callbacks
            board_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, "You clicked " + boardSize,
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Add to radio group:
            board_group.addView(board_button);
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
                }
            });

            mines_group.addView(mine_button);


        }
    }
}