package com.jasonyoon.ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class TicTacToe {

    private char[] board;

    public static void main( String[] args ) {

    }

    public TicTacToe() {
        this.board = new char[9];
        //logically unnecessary, but helps board display stay "in place" by
        //initializing as ' ' rather than leaving it as a null character
        Arrays.fill(board, ' ');
    }

    public void displayBoard() {
        for(int i = 0; i < 9; i += 3) {
            System.out.print("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |\n");
        }
        System.out.println();
    }

    public void makeMove(char mark, int position) {
        board[position - 1] = mark;
    }

    public void playGame(){
        for(int i = 1; i < 10; i++) {
            if(i % 2 == 1) {
                makeMove('X', i);
            } else {
                makeMove('O', i);
            }
            displayBoard();
        }
    }
    
}
