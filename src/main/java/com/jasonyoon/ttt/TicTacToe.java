package com.jasonyoon.ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TicTacToe {

    private char[][] board;

    public static void main( String[] args ) {

    }

    public TicTacToe() {
        this.board = new char[3][3];
        //logically unnecessary, but helps board interface stay "in place" by
        //initializing as ' ' rather than leaving it as a null character
        for(char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public void displayBoard() {
        for(int i = 0; i < 3; i++) {
            System.out.print("| " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " |\n");
        }
        System.out.print("\n");
    }

    public void makeMove(char player, int row, int col) {
        board[row][col] = player;
    }

    public void playGame(){
        boolean playersTurn = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (playersTurn) {
                    makeMove('X', i, j);
                } else {
                    makeMove('O', i, j );
                }
                playersTurn = !playersTurn;
                displayBoard();
            }
        }
    }

}
