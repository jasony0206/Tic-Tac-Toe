package com.jasonyoon.ttt;

import org.junit.Before;
import org.junit.Test;

import java.io.Console;

/**
 * Created by jason.yoon on 7/8/2015.
 */
public class ConsoleBoardTest {
    private ConsoleBoard b;

    @Before
    public void setup() {
        b = new ConsoleBoard();
    }

    @Test
    public void testPrintWhenGivenStringLiteralShouldPrintToConsole() {
        //GIVEN
        String message = "Hello world";
        //WHEN
        b.print(message);
    }

    @Test
    public void testPrintWhenGivenFormattedStringShouldPrintToConsole() {
        //GIVEN
        int num = 3;
        //WHEN
        b.print("Printing " + num + " to console...");
    }

    @Test
    public void testPrintGridGivenEmptyGridShouldPrintEmptyGrid() {
        //GIVEN
        char[] emptyGrid = new char[9];
        //WHEN
        b.printGrid(emptyGrid);
    }

    @Test
    public void testPrintGridGivenInitializedGridShouldPrintInitializedGrid() {
        //GIVEN
        char[] initializedGrid = {'1', '2', '3', '4', '5', '6', '7', '8' ,'9'};
        //WHEN
        b.printGrid(initializedGrid);
    }

    @Test
    public void testPrintGridGivenPartiallyFilledBoardShouldPrintIt() {
        //GIVEN
        char[] partiallyFilledBoard = {'1', 'X', 'O', '4', '5', 'X', '7', '8' ,'O'};
        //WHEN
        b.printGrid(partiallyFilledBoard);
    }
}
