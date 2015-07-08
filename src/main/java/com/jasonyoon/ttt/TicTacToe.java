package com.jasonyoon.ttt;


import java.io.*;

public class TicTacToe {

    private char[] grid;
    private char winner = '?';
    private int turnsPlayed = 0;
    private InputStream is;
    private Board board;

    public static void main(String[] args) throws IOException{
        TicTacToe t = new TicTacToe(System.in, new ConsoleBoard());
        t.playGame();
    }

    public TicTacToe(InputStream is, Board board) {
        grid = new char[9];
        //logically unnecessary, but helps board interface stay "in place" by
        //initializing as ' ' rather than leaving it as a null character
        for(int i = 0; i < 9; i++) {
            grid[i] = Character.forDigit(i + 1, 10);
        }
        this.is = is;
        this.board = board;
    }

    public char[] getGrid(){
        return grid;
    }

    public char getWinner() {
        return winner;
    }

    public void displayGrid() {
        board.printGrid(grid);
    }

    public boolean isValidMove(int position) {
        if (position < 1 || position > 9) {
            board.print("Position must be between 1 and 9 inclusive.");
            return false;
        } else if(grid[position - 1] != Character.forDigit(position, 10)) {
            board.print("Position " + (position) + " is already filled.");
            return false;
        }
        return true;
    }

    public boolean isWinningMove(char player, int position) {
        return (completesRow(player, position) || completesCol(player, position) || completesDiagonal(player));
    }

    private boolean completesRow(char player, int position) {
        int row = (position - 1) / 3;
        return grid[row] == player && grid[row + 1] == player && grid[row + 2] == player;
    }

    private boolean completesCol(char player, int position) {
        int col = (position - 1) % 3;
        return grid[col] == player && grid[col + 3] == player && grid[col + 6] == player;
    }

    private boolean completesDiagonal(char player) {
        return (grid[0] == player && grid[4] == player && grid[8] == player)
                || (grid[2] == player && grid[4] == player && grid[6] == player);
    }

    public boolean makeMove(char player, int position) {
        if(!isValidMove(position)) {
            return false;
        }
        grid[position - 1] = player;
        if(turnsPlayed >= 4 && isWinningMove(player, position)) {
            winner = player;
        }
        return true;
    }

    public char[] playGame() throws IOException{
        String newline = System.getProperty("line.separator");
        board.print("Please make a move by typing in the position specified on the grid." + newline + "Any numbers following the first number will be ignored.");
        BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
        this.displayGrid();

        String input;
        char player = 'X';

        //play at most 9 valid moves
        while(turnsPlayed < 9){
            if (player == 'X') {
                board.print("Player X's turn: What's your move?");
            } else {
                board.print("Player O's turn: What's your move?");
            }

            if((input = br.readLine()) != null && input.length() > 0) {
                if(makeMove(player, Character.getNumericValue(input.charAt(0)))) {
                    displayGrid();
                    if(winner != '?') {
                        //game finished, announce winner and return
                        board.print(winner + " won!");
                        return this.grid;
                    }
                    player = (player == 'X') ? 'O' : 'X';
                    turnsPlayed++;
                }
            } else {
                board.print("Please make a move");
            }
        }
        board.print("It's a tie. BOOOOO");
        return this.grid;
    }
}
