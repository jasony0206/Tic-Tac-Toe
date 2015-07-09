package com.jasonyoon.ttt;

/**
 * Created by jason.yoon on 7/8/2015.
 */
public class ConsoleOutputSystem implements OutputSystem {

    public void print(String message) {
        System.out.println(message);
    }

    public void printGrid(Board board) {
        for(int i = 1; i < 10; i += 3) {
            System.out.print(String.format("| " + board.getCell(i) + " | " + board.getCell(i + 1) + " | " + board.getCell(i + 2) + " |%n"));
        }
        System.out.println();
    }
}
