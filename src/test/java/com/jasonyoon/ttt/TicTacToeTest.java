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
    String newline = System.getProperty("line.separator");

    @Before
    public void setup() {
        ttt = new TicTacToe(System.in, new ConsoleBoard());
        stdin = System.in;
        stdout = System.out;
    }

    @After
    public void cleanup() {
        System.setOut(stdout);
        System.setIn(stdin);
    }

    @Test
    public void testDisplayBoardWhenGameStartsShouldDisplayEmptyBoard() {
        //GIVEN
        //redirect output so we can compare
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        String expectedOutput = "| 1 | 2 | 3 |" + newline + "| 4 | 5 | 6 |" + newline + "| 7 | 8 | 9 |" + newline + newline;
        //WHEN
        ttt.displayGrid();
        //THEN
        assertEquals(expectedOutput, consoleOutput.toString());
    }

    @Test
    public void testMakeMoveWhenXMakesMoveShouldPutX() {
        //GIVEN board is empty
        char[] expected = {'1', 'X', '3', '4', '5', '6', '7', '8', '9'};
        //WHEN
        ttt.makeMove('X', 2);
        //THEN
        assertTrue(Arrays.equals(expected, ttt.getGrid()));
    }

    @Test
    public void testMakeMoveWhenOMakesMoveShouldPutO() {
        //GIVEN board is empty
        char[] expected = {'O', '2', '3', '4', '5', '6', '7', '8', '9'};
        //WHEN
        ttt.makeMove('O', 1);
        //THEN
        assertTrue(Arrays.equals(expected, ttt.getGrid()));
    }

    @Test
    public void testDisplayBoardWhenWeFillBoardShouldShowCompletedBoard() {
        //GIVEN
        //redirect output so we can compare
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        String expectedOutput = "| X | O | X |" + newline + "| O | X | O |" + newline + "| X | O | X |" + newline + newline;
        fillBoard();
        //WHEN
        ttt.displayGrid();
        //THEN
        assertEquals(expectedOutput, consoleOutput.toString());
    }

    private void fillBoard() {
        ttt.makeMove('X', 1);
        ttt.makeMove('O', 2);
        ttt.makeMove('X', 3);
        ttt.makeMove('O', 4);
        ttt.makeMove('X', 5);
        ttt.makeMove('O', 6);
        ttt.makeMove('X', 7);
        ttt.makeMove('O', 8);
        ttt.makeMove('X', 9);
    }

    @Test
    public void testPlayGameWhenGameStartsShouldPlayAlternating() throws IOException{
        //GIVEN
        String userInputString = "1\n2\n3\n4\n5\n6\n7\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        char[] expected = {'X', 'O', 'X', 'O', 'X', 'O', 'X', '8', '9'};
        //WHEN
        ttt = new TicTacToe(is, new ConsoleBoard());
        ttt.playGame();
        //THEN
        assertTrue(Arrays.equals(expected, ttt.getGrid()));
    }

    @Test
    public void testMakeMoveWhenAlreadyFilledShouldMessage() {
        //GIVEN
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        ttt.makeMove('X', 3);
        //WHEN
        ttt.makeMove('O', 3);
        //THEN
        assertEquals(String.format("Position 3 is already filled.%n"), consoleOutput.toString());
    }

    @Test
    public void testPlayGameWhenThreeSingleRowShouldWin() throws IOException{
        //GIVEN
        String userInputString = "1\n7\n2\n8\n3\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        //WHEN
        ttt = new TicTacToe(is, new ConsoleBoard());
        ttt.playGame();
        //THEN
        assertEquals('X', ttt.getWinner());
    }

    @Test
    public void testPlayGameWhenThreeSingleColumnShouldWin() throws IOException{
        //GIVEN
        String userInputString = "1\n2\n4\n5\n7\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        //WHEN
        ttt = new TicTacToe(is, new ConsoleBoard());
        ttt.playGame();
        //THEN
        assertEquals('X', ttt.getWinner());
    }

    @Test
    public void testPlayGameWhenThreeSingleDiagonalShouldWin1() throws IOException{
        //GIVEN
        String userInputString = "1\n7\n5\n8\n9\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        //WHEN
        ttt = new TicTacToe(is, new ConsoleBoard());
        ttt.playGame();
        //THEN
        assertEquals('X', ttt.getWinner());
    }

    @Test
    public void testPlayGameWhenThreeSingleDiagonalShouldWin2() throws IOException{
        //GIVEN
        String userInputString = "3\n9\n5\n8\n7\n";
        InputStream is = new ByteArrayInputStream(userInputString.getBytes());
        //WHEN
        ttt = new TicTacToe(is, new ConsoleBoard());
        ttt.playGame();
        //THEN
        assertEquals('X', ttt.getWinner());
    }

    @Test
    public void testIsValidMoveWhenSafeIndexingShouldReturnTrue() {
        assertTrue(ttt.isValidMove(1));
    }

    @Test
    public void testIsValidMoveWhenOutOfBoundShouldReturnFalse() {
        assertFalse(ttt.isValidMove(10));
    }

    @Test
    public void testIsValidMoveWhenAlreadyFilledShouldReturnFalse() {
        //GIVEN
        ttt.makeMove('X', 1);
        //THEN
        assertFalse(ttt.isValidMove(1));
    }

    @Test
    public void testIsWinningMoveWhenRowIsFinishedShouldReturnTrue() {
        //GIVEN
        ttt.makeMove('X', 1);
        ttt.makeMove('X', 2);
        //WHEN
        ttt.makeMove('X', 3);
        //THEN
        assertTrue(ttt.isWinningMove('X', 3));
    }

    @Test
    public void testIsWinningMoveWhenNothingFinishedShouldReturnFalse() {
        //WHEN
        ttt.makeMove('X', 1);
        //THEN
        assertFalse(ttt.isWinningMove('X', 3));
    }


}
