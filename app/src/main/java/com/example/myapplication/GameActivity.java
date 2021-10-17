package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameActivity extends AppCompatActivity {

    private static final int ROW_NUM = 4;
    private static final int COL_NUM = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        populateButtons();
    }

    private void populateButtons() {

        TableLayout table = (TableLayout) findViewById(R.id.GameTable);

        for (int row = 0; row < ROW_NUM; row++){

            TableRow tRow = new TableRow(this);
            table.addView(tRow);

            for (int col = 0; col < COL_NUM; col++){

                Button button = new Button(this);
                tRow.addView(button);

            }
        }

    }
}