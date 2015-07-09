package com.jasonyoon.ttt;

/**
 * Created by jason on 7/9/15.
 */
public class CharArrayBoard implements Board {

    private char[] grid;

    public CharArrayBoard() {
        grid = new char[9];
        //logically unnecessary, but helps outputSystem interface stay "in place" by
        //initializing as ' ' rather than leaving it as a null character
        for(int i = 0; i < 9; i++) {
            grid[i] = Character.forDigit(i + 1, 10);
        }
    }

    public boolean putMove(char player, int position) {
        grid[position - 1] = player;
        return true;
    }

    public char getCell(int position) {
        return grid[position - 1];
    }

    public boolean isValidMove(int position, StringBuilder errorMessage) {
        if (position < 1 || position > 9) {
            errorMessage.append("Position must be between 1 and 9 inclusive.");
            return false;
        } else if(grid[position - 1] != Character.forDigit(position, 10)) {
            errorMessage.append("Position " + (position) + " is already filled.");
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
}
