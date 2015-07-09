package com.jasonyoon.ttt;


import java.io.*;

public class TicTacToe {

    private char winner = '?';
    private int turnsPlayed = 0;
    private InputStream is;
    private OutputSystem outputSystem;
    private Board board;

    public static void main(String[] args) throws IOException{
        TicTacToe t = new TicTacToe(System.in, new ConsoleOutputSystem());
        t.playGame();
    }

    public TicTacToe(InputStream is, OutputSystem outputSystem) {
        board = new CharArrayBoard();
        this.is = is;
        this.outputSystem = outputSystem;
    }

    public Board getBoard(){
        return board;
    }

    public char getWinner() {
        return winner;
    }

    public void displayBoard() {
        outputSystem.printGrid(board);
    }

    public boolean isValidMove(int position) {
        StringBuilder errorMsg = new StringBuilder();
        if(board.isValidMove(position, errorMsg)) {
            return true;
        } else {
            outputSystem.print(errorMsg.toString());
            return false;
        }
    }

    public boolean isWinningMove(char player, int position) {
        return board.isWinningMove(player, position);
    }

    public boolean makeMove(char player, int position) {
        if(!isValidMove(position)) {
            return false;
        }
        board.putMove(player, position);
        if(turnsPlayed >= 4 && isWinningMove(player, position)) {
            winner = player;
        }
        return true;
    }

    public Board playGame() throws IOException{
        String newline = System.getProperty("line.separator");
        outputSystem.print("Please make a move by typing in the position specified on the grid." + newline + "Any numbers following the first number will be ignored.");
        BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
        this.displayBoard();

        String input;
        char player = 'X';

        //play at most 9 valid moves
        while(turnsPlayed < 9){
            if (player == 'X') {
                outputSystem.print("Player X's turn: What's your move?");
            } else {
                outputSystem.print("Player O's turn: What's your move?");
            }

            if((input = br.readLine()) != null && input.length() > 0) {
                if(makeMove(player, Character.getNumericValue(input.charAt(0)))) {
                    displayBoard();
                    if(winner != '?') {
                        //game finished, announce winner and return
                        outputSystem.print(winner + " won!");
                        return this.board;
                    }
                    player = (player == 'X') ? 'O' : 'X';
                    turnsPlayed++;
                }
            } else {
                outputSystem.print("Please make a move");
            }
        }
        outputSystem.print("It's a tie. BOOOOO");
        return this.board;
    }
}
