package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createRadioButtons();
    }

    private void createRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);

        int[] board_Size = getResources().getIntArray(R.array.num_board_size);

        // Create the buttons
        for(int i = 0; i < board_Size.length; i++) {
            int boardSize = board_Size[1];

            RadioButton button = new RadioButton(this);
            button.setText(boardSize + "board size");

            // TODO: Set on-click callbacks

            // Add to radio group:
            group.addView(button);
        }
    }
}