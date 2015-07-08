package com.jasonyoon.ttt;

/**
 * Created by jason.yoon on 7/8/2015.
 */
public class ConsoleBoard implements Board {

    public void print(String message) {
        System.out.println(message);
    }

    public void printGrid(char[] grid) {
        for(int i = 0; i < 9; i += 3) {
            System.out.print(String.format("| " + grid[i] + " | " + grid[i + 1] + " | " + grid[i + 2] + " |%n"));
        }
        System.out.println();
    }
}
