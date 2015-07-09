package com.jasonyoon.ttt;

/**
 * Created by jason on 7/8/15.
 */
public interface Board {

    boolean putMove(char player, int position);

    char getCell(int position);

    boolean isValidMove(int position, StringBuilder errorMessage);

    boolean isWinningMove(char player, int position);
}
