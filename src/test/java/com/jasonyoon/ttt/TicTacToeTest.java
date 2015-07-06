package com.jasonyoon.ttt;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

/**
 * Created by jason.yoon on 7/2/2015.
 */
public class TicTacToeTest {
    TicTacToe ttt;
    PrintStream stdout;
    ByteArrayOutputStream consoleOutput;

    @Before
    public void setup() {
        ttt = new TicTacToe();
        //redirect output so we can compare
        stdout = System.out;
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
    }

    @After
    public void cleanup() {
        System.setOut(stdout);
    }

    @Test
    public void displaysEmptyBoard() {
        String expectedOutput = "|   |   |   |\n|   |   |   |\n|   |   |   |\n\n";
        ttt.displayBoard();
        assertEquals(expectedOutput, consoleOutput.toString());
    }

    @Test
    public void displaysCompletedBoard() {
        String expectedOutput = "| X | O | X |\n| O | X | O |\n| X | O | X |\n\n";
        fillBoard();
        ttt.displayBoard();
        assertEquals(expectedOutput, consoleOutput.toString());
    }

    private void fillBoard() {
        ttt.makeMove('X', 0, 0);
        ttt.makeMove('O', 0, 1);
        ttt.makeMove('X', 0, 2);
        ttt.makeMove('O', 1, 0);
        ttt.makeMove('X', 1, 1);
        ttt.makeMove('O', 1, 2);
        ttt.makeMove('X', 2, 0);
        ttt.makeMove('O', 2, 1);
        ttt.makeMove('X', 2, 2);
    }

    @Test
    public void playsAutomaticallyAlternating() {
        ttt.playGame();
        String expectedOutput =
                "| X |   |   |\n|   |   |   |\n|   |   |   |\n\n"
                + "| X | O |   |\n|   |   |   |\n|   |   |   |\n\n"
                + "| X | O | X |\n|   |   |   |\n|   |   |   |\n\n"
                + "| X | O | X |\n| O |   |   |\n|   |   |   |\n\n"
                + "| X | O | X |\n| O | X |   |\n|   |   |   |\n\n"
                + "| X | O | X |\n| O | X | O |\n|   |   |   |\n\n"
                + "| X | O | X |\n| O | X | O |\n| X |   |   |\n\n"
                + "| X | O | X |\n| O | X | O |\n| X | O |   |\n\n"
                + "| X | O | X |\n| O | X | O |\n| X | O | X |\n\n";
        assertEquals(expectedOutput, consoleOutput.toString());
    }




}
