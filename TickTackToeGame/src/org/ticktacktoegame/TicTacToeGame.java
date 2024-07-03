package org.ticktacktoegame;
import java.util.Scanner;

public class TicTacToeGame {
    private char[][] board;
    private Player playerX, playerO;
    private Player currentPlayer;
    private boolean gameOver;

    public TicTacToeGame() {
        board = new char[3][3];
        playerX = new Player("Player X", 'X');
        playerO = new Player("Player O", 'O');
        currentPlayer = playerX; // Player X starts first
        gameOver = false;
        initializeBoard();
    }

    public void playGame() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        while (!gameOver) {
            printBoard();
            currentPlayer.makeMove(board);
            if (checkForWin(currentPlayer.getSymbol())) {
                printBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }
        }
        System.out.println("Game Over.");
    }

    private void initializeBoard() {
        // Initialize the board with empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
        // Print the board
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean checkForWin(char symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true; // Row win
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true; // Column win
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    }

    private boolean isBoardFull() {
        // Check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Found an empty space, board is not full
                }
            }
        }
        return true; // Board is full
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}

class Player {
    private String name;
    private char symbol;
    private Scanner scanner;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void makeMove(char[][] board) {
        // Prompt the player to enter their move
        boolean validMove = false;
        while (!validMove) {
            System.out.print(name + ", enter your move (row[1-3] column[1-3]): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = symbol;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
