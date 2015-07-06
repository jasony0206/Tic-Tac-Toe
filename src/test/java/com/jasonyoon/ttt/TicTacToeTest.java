package com.jasonyoon.ttt;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jason.yoon on 7/2/2015.
 */

public class TicTacToeTest {
    TicTacToe ttt;
    PrintStream stdout;
    InputStream stdin;
    ByteArrayOutputStream consoleOutput;


    @Before
    public void setup() {
        ttt = new TicTacToe();
        stdin = System.in;
        stdout = System.out;
    }

    @After
    public void cleanup() {
        System.setOut(stdout);
        System.setIn(stdin);
    }

    @Test
    public void displaysEmptyBoard() {
        //redirect output so we can compare
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        String expectedOutput = "|   |   |   |\n|   |   |   |\n|   |   |   |\n\n";
        ttt.displayBoard();
        assertEquals(expectedOutput, consoleOutput.toString());
    }

    @Test
    public void makeMoveWorks() {
        char[][] expected = {{' ', 'X', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        ttt.makeMove('X', 0, 1);
        assertTrue(Arrays.deepEquals(expected, ttt.getBoard()));
        expected[0][0] = 'O';
        ttt.makeMove('O', 0, 0);
        assertTrue(Arrays.deepEquals(expected, ttt.getBoard()));
    }

    @Test
    public void displaysCompletedBoard() {
        //redirect output so we can compare
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

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
    public void playsAutomaticallyAlternating() throws IOException{
        //redirect output so we can compare
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        String userInputString = "0 0\n0 1\n0 2\n1 0\n1 1\n1 2\n2 0\n2 1\n2 2\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        char[][] expected = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
        assertTrue(Arrays.deepEquals(expected, ttt.playGame(is)));
    }

    @Test
    public void takesMockPlayerInput() throws IOException{
        String userInputString = "0 0\n1 2\n0 1\n2 0\n0 2\n2 1\n1 0\n2 2\n1 1\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        char[][] expected = {{'X', 'X', 'X'}, {'X', 'X', 'O'}, {'O', 'O', 'O'}};
        assertTrue(Arrays.deepEquals(expected, ttt.playGame(is)));
    }

    @Test
    public void ifAlreadyFilledOutputMessage() {
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        ttt.makeMove('X', 1, 1);
        ttt.makeMove('O', 1, 1);
        assertEquals("1, 1 is already filled.\n", consoleOutput.toString());
    }

    @Test
    public void threeInSingleRowWins() {
        ttt.makeMove('X', 0, 0);
        ttt.makeMove('X', 0, 1);
        ttt.makeMove('X', 0, 2);
        assertEquals('X', ttt.getWinner());
    }

    @Test
    public void threeInSingleColumnWins() {
        ttt.makeMove('Y', 0, 0);
        ttt.makeMove('Y', 1, 0);
        ttt.makeMove('Y', 2, 0);
        assertEquals('Y', ttt.getWinner());
    }

    @Test
    public void threeInSingleDiagonalWins1() {
        ttt.makeMove('Y', 0, 0);
        ttt.makeMove('Y', 1, 1);
        ttt.makeMove('Y', 2, 2);
        assertEquals('Y', ttt.getWinner());
    }

    @Test
    public void threeInSingleDiagonalWins2() {
        ttt.makeMove('Y', 0, 2);
        ttt.makeMove('Y', 1, 1);
        ttt.makeMove('Y', 2, 0);
        assertEquals('Y', ttt.getWinner());
    }
}
