/*

Activity that plays the game

 */


package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.GameInfo;
import com.example.myapplication.model.GameLogic;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    int BOARD;
    private static int MINES;
    private int rows_f;
    private int cols_f;

    int[][] buttonArr;
    Button[][] buttons;

    public int MINES_LEFT;
    public int SCANS_USED;

    GameInfo gameInfo;
    GameLogic gl;

    int gamesPlayed;

    MediaPlayer explode;
    MediaPlayer scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameInfo = GameInfo.getInstance();
        gamesPlayed  = gameInfo.getGamesplayed();

        gl = GameLogic.getInstance();
        MINES = gl.getMines();
        BOARD = gl.getBoardsize();


        String score = "High score: " + Integer.toString(gameInfo.getHighscore());
        TextView hScore = (TextView) findViewById(R.id.txt_highScore);
        if (gameInfo.getHighscore()==100){

            hScore.setText("No high score set");
        }
        else hScore.setText(score);


        SCANS_USED = 0;
        MINES_LEFT = MINES;

        TextView scansText = (TextView) findViewById(R.id.scans_used);
        TextView minesText = (TextView) findViewById(R.id.mines_left);

        String scans = "Scans used: " + SCANS_USED + "";
        scansText.setText(scans);

        explode = MediaPlayer.create(this,R.raw.sample);
        scan = MediaPlayer.create(this,R.raw.scan);

        String minesLeftT = "Mines left: " + MINES_LEFT + "";
        minesText.setText(minesLeftT);
        populateButtons();


    }

    protected void onStart() {

        gameInfo = GameInfo.getInstance();
        gamesPlayed  = gameInfo.getGamesplayed();

        gl = GameLogic.getInstance();
        MINES = gl.getMines();
        BOARD = gl.getBoardsize();



        String score = "High score: " + Integer.toString(gameInfo.getHighscore());
        super.onStart();
    }


    private void populateButtons() {

        TableLayout table = (TableLayout) findViewById(R.id.GameTable);
        int row_num =2;
        int col_num=4;

        if(BOARD == 0){

            row_num = 4;
            col_num = 6;
            rows_f = 4;
            cols_f = 6;

            int[][] butArr = new int[4][6];
            buttons = new Button[4][6];

            buttonArr = butArr;
        }
        if(BOARD == 1){

            row_num = 5;
            col_num = 10;
            rows_f = 5;
            cols_f = 10;

            int[][] butArr = new int[5][10];
            buttons = new Button[5][10];

            buttonArr = butArr;

        }
        if(BOARD == 2){

            row_num = 6;
            col_num = 15;
            rows_f = 6;
            cols_f = 15;

            int[][] butArr = new int[6][15];
            buttons = new Button[6][15];

            buttonArr = butArr;

        }

        randomizeMines(buttonArr);

        for (int row = 0; row < row_num; row++){

            TableRow tRow = new TableRow(this);
            tRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
            ));
            table.addView(tRow);

            for (int col = 0; col < col_num; col++){

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                int finalRow = row;
                int finalCol = col;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(finalRow, finalCol, button);
                    }
                });
                tRow.addView(button);
                buttons[row][col] = button;
                button.setPadding(0,0,0,0);

            }
        }

    }

    @SuppressLint("SetTextI18n")
    private void gridButtonClicked(int row, int col, Button button) {
        explode = MediaPlayer.create(this,R.raw.sample);
        scan = MediaPlayer.create(this,R.raw.scan);
        TextView scansText = (TextView) findViewById(R.id.scans_used);
        TextView minesText = (TextView) findViewById(R.id.mines_left);

        lockButtonSizes();

        if (buttonArr[row][col] == 1 && button.getText()!="MINE"){
            explode.start();
            button.setText("MINE");
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.landmine);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            MINES_LEFT--;
            if (MINES_LEFT==0){
                Toast.makeText(this,"Game won! Your score was "+SCANS_USED,Toast.LENGTH_LONG).show();
                if(SCANS_USED < gameInfo.getHighscore()) gameInfo.setHighscore(SCANS_USED);
                gamesPlayed++;
                gameInfo.setGamesplayed(gamesPlayed);
                finish();
            }
            minesText.setText("Mines left: " + MINES_LEFT);
            return;
        } if(button.getText()=="MINE"){
            scan.start();
            button.setText(Integer.toString((scanForMines(buttonArr,row,col)-2)));
            SCANS_USED++;
            scansText.setText("Scans used: " + SCANS_USED);
            return;}
        lockButtonSizes();
        scan.start();
        SCANS_USED++;
        scansText.setText("Scans used: " + SCANS_USED);
        button.setText(Integer.toString(scanForMines(buttonArr,row,col)));

    }

    private void lockButtonSizes() {
        for (int row = 0; row < rows_f; row++) {
            for (int col = 0; col < cols_f; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    //Place mines in array
    private void randomizeMines(int [][] arr){

        //Initialize array
        for (int[] ints : arr) {
            Arrays.fill(ints, 0);
        }

        //Add mines
        int rows = arr.length - 1;
        int cols = arr[0].length - 1;
        for (int i = 0; i < MINES; i++){

            int ranRows = (int)((Math.random() * (rows) - 0) + 0);
            int ranCols = (int)((Math.random() * (cols) - 0) + 0);

            while(arr[ranRows][ranCols] != 0){

                ranRows = (int)((Math.random() * (rows)) + 0);
                ranCols = (int)((Math.random() * (cols)) + 0);

            }
            arr[ranRows][ranCols] = 1;

        }

    }

    private int scanForMines(int [][] arr, int indexRow,int indexCol){

        int total = 0;
        for (int row = 0; row < arr.length; row++){

            if (arr[row][indexCol] == 1) total++;
        }
        for (int col = 0; col < arr[0].length; col++){

            if (arr[indexRow][col] == 1) total++;

        }
        return total;

    }
}