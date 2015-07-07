package com.jasonyoon.ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TicTacToe {

    private char[] board;
    private char winner = '?';
    private int turnsPlayed = 0;

    public static void main( String[] args ) throws IOException{
        TicTacToe t = new TicTacToe();
        t.playGame(System.in);
    }

    public char[] getBoard(){
        return board;
    }

    public char getWinner() {
        return winner;
    }

    public TicTacToe() {
        board = new char[9];
        //logically unnecessary, but helps board interface stay "in place" by
        //initializing as ' ' rather than leaving it as a null character
        for(int i = 0; i < 9; i++) {
            board[i] = Character.forDigit(i + 1, 10);
        }
    }

    public void displayBoard() {
        for(int i = 0; i < 9; i += 3) {
            System.out.print("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |\n");
        }
        System.out.print("\n");
    }

    public boolean isValidMove(int position) {
        if (position < 1 || position > 9) {
            System.out.println("Position must be between 1 and 9 inclusive.");
            return false;
        } else if(board[position - 1] != Character.forDigit(position, 10)) {
            System.out.print("Position " + (position) + " is already filled.\n");
            return false;
        }
        return true;
    }

    public boolean isWinningMove(char player, int position) {
        return (completesRow(player, position) || completesCol(player, position) || completesDiagonal(player));
    }

    private boolean completesRow(char player, int position) {
        int row = (position - 1) / 3;
        return board[row] == player && board[row + 1] == player && board[row + 2] == player;
    }

    private boolean completesCol(char player, int position) {
        int col = (position - 1) % 3;
        return board[col] == player && board[col + 3] == player && board[col + 6] == player;
    }

    private boolean completesDiagonal(char player) {
        return (board[0] == player && board[4] == player && board[8] == player)
                || (board[2] == player && board[4] == player && board[6] == player);
    }

    public boolean makeMove(char player, int position) {
        if(!isValidMove(position)) {
            return false;
        }
        board[position - 1] = player;
        if(turnsPlayed >= 4 && isWinningMove(player, position)) {
            winner = player;
        }
        return true;
    }

    public char[] playGame(InputStream is) throws IOException{
        System.out.println("Please make a move by typing in the position specified on the grid.\nAny numbers following the first number will be ignored.");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        this.displayBoard();

        String input;
        char player = 'X';

        //play at most 9 valid moves
        while(turnsPlayed < 9){
            if (player == 'X') {
                System.out.println("Player X's turn: What's your move?");
            } else {
                System.out.println("Player O's turn: What's your move?");
            }

            if((input = br.readLine()) != null && input.length() > 0) {
                if(makeMove(player, Character.getNumericValue(input.charAt(0)))) {
                    displayBoard();
                    if(winner != '?') {
                        //game finished, announce winner and return
                        System.out.println(winner + " won!");
                        return this.board;
                    }
                    player = (player == 'X') ? 'O' : 'X';
                    turnsPlayed++;
                }
            } else {
                System.out.println("Please make a move");
            }
        }
        System.out.println("It's a tie. BOOOOO");
        return this.board;
    }
}
