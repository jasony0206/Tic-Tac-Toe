package com.jasonyoon.ttt;

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

    @Before
    public void setup() {
        ttt = new TicTacToe();
    }

    @Test
    public void runsUnitTest() {
        //System.out.println("Running unit tests...");
    }

    @Test
    public void displaysEmptyBoarD() {
        System.out.println("displays an empty board");
        ttt.displayBoard();
    }

//    private String getActualOutput(TicTacToe t) {
//        PrintStream stdout = System.out;
//        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(actualOutput));
//        t.displayBoard();
//        System.setOut(stdout);
//        return actualOutput.toString();
//    }

    @Test
    public void displaySCompletedBoard() {
        System.out.println("displays a completed board filled with X and O");
        fillBoard();
        ttt.displayBoard();
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
    public void playsAutomaticallyAlternating() {
        System.out.println("plays a game automatically alternating between X and O");
        ttt.playGame();
    }



}
