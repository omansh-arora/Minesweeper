package com.example.myapplication.model;

public class GameLogic {

    private static GameLogic instance;

    private int boardsize;
    private int mines;

    public static GameLogic getInstance() {



        if (instance == null) {

            instance = new GameLogic();

        }
        return instance;


    }

    public int getBoardsize(){

        return boardsize;
    }

    public void setBoardsize(int boardsize) {
        this.boardsize = boardsize;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mine) {

        this.mines = mine;

    }
}
