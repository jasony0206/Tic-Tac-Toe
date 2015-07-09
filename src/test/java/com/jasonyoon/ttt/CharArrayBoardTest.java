package com.jasonyoon.ttt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jason on 7/9/15.
 */
public class CharArrayBoardTest {
    CharArrayBoard board;

    @Before
    public void setup() {
        board = new CharArrayBoard();
    }

    @Test
    public void testIsValidMoveGivenInvalidPositionShouldReturnFalse() {
        StringBuilder msg = new StringBuilder();
        assertFalse(board.isValidMove(0, msg));
    }

    @Test
    public void testIsValidMoveGivenInvalidPositionErrorMessageIsCorrect() {
        StringBuilder msg = new StringBuilder();
        board.isValidMove(0, msg);
        System.out.println(msg.toString());
    }

    @Test
    public void testIsValidMoveGivenAlreadyFilledErrorMessageIsCorrect() {
        StringBuilder msg = new StringBuilder();
        board.putMove('X', 1);
        board.isValidMove(1, msg);
        System.out.println(msg.toString());
    }

    @Test
    public void testPutMoveGivenPositionAndPlayerShouldFillItIn() {
        board.putMove('X', 2);
        assertEquals('X', board.getCell(2));
    }

    @Test
    public void testIsWinningMoveGivenCompletesRow() {
        board.putMove('X', 1);
        board.putMove('X', 2);
        board.putMove('X', 3);
        assertTrue(board.isWinningMove('X', 3));
    }

    @Test
    public void testIsWinningMoveGivenCompletesColumn() {
        board.putMove('X', 1);
        board.putMove('X', 4);
        board.putMove('X', 7);
        assertTrue(board.isWinningMove('X', 7));
    }


}
