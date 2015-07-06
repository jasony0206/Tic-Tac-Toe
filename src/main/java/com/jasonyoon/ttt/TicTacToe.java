package com.jasonyoon.ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TicTacToe {

    private char[][] board;
    private char winner = '?';
    private int turnsPlayed = 0;

    public static void main( String[] args ) throws IOException{
        TicTacToe t = new TicTacToe();
        t.playGame(System.in);
    }

    public char[][] getBoard(){
        return board;
    }

    public char getWinner() {
        return winner;
    }

    public TicTacToe() {
        board = new char[3][3];
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

    public boolean makeMove(char player, int row, int col) {
        //System.out.println("row:" + row + "col:" + col);
        if(row < 0 || 2 < row || col < 0 || 2 < col) {
            System.out.println("Row and column must be between 0 and 2 inclusive.");
            return false;
        } else if(board[row][col] != ' ') {
            System.out.print(row + ", " + col + " is already filled.\n");
            return false;
        }

        board[row][col] = player;

        //check if this move finishes the game
        if((board[row][0] == player && board[row][1] == player && board[row][2] == player)
            || (board[0][col] == player && board[1][col] == player && board[2][col] == player)
            || (board[0][col] == player && board[1][col] == player && board[2][col] == player)
            || (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            || (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            winner = player;
        }
        return true;
    }

    public char[][] playGame(InputStream is) throws IOException{
        System.out.println("Please specify the row and column in the following format: 'ROW' 'COL'\nRow and column must be between 0 and 2 inclusive.\nExample: 1 2\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        this.displayBoard();

        String input;
        char player = 'X';
        int row, col;

        while(turnsPlayed < 9){
            if (player == 'X') {
                System.out.println("Player X's turn: What's your move?");
            } else {
                System.out.println("Player O's turn: What's your move?");
            }

            if((input = br.readLine()) != null && input.length() >= 3) {
                row = Character.getNumericValue(input.charAt(0));
                col = Character.getNumericValue(input.charAt(2));
                if(makeMove(player, row, col)) {
                    displayBoard();
                    if(winner != '?') {
                        break;
                    }
                    player = (player == 'X') ? 'O' : 'X';
                    turnsPlayed++;
                }
            } else {
                System.out.println("Please specify row and column in the following format: 'ROW' 'COL'");
            }
        }
        System.out.println(winner + " won!");
        return this.board;
    }
}
