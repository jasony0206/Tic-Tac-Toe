package com.jasonyoon.ttt;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by jason.yoon on 7/8/2015.
 */
public class ConsoleOutputSystemTest {
    private ConsoleOutputSystem b;

    @Before
    public void setup() {
        b = new ConsoleOutputSystem();
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
    public void testPrintGridGivenInitializedGridShouldPrintInitializedGrid() {
        //GIVEN
        Board board = new CharArrayBoard();
        //WHEN
        b.printGrid(board);
    }

    @Test
    public void testPrintGridGivenPartiallyFilledBoardShouldPrintIt() {
        //GIVEN
        Board board = new CharArrayBoard();
        //WHEN
        board.putMove('X', 1);
        b.printGrid(board);
    }
}
