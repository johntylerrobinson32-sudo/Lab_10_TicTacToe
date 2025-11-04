import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Lab 10: TicTacToe - IT1090C / IT6090C\n");
        // Add SafeInput.java to project and use SafeInput.getRangedInt / getYNConfirm
        boolean playAgain;
        do {
            clearBoard();
            String player = "X";   // X always goes first
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();
                System.out.println(player + "'s turn.");
                int rowChoice = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3);
                int colChoice = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3);

                // convert to 0-based indices
                int r = rowChoice - 1;
                int c = colChoice - 1;

                while (!isValidMove(r, c)) {
                    System.out.println("That cell is already occupied. Choose another.");
                    rowChoice = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3);
                    colChoice = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3);
                    r = rowChoice - 1;
                    c = colChoice - 1;
                }

                // record move
                board[r][c] = player;
                moveCount++;

                // check win or tie
                if (isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (isTie(moveCount)) {
                    display();
                    System.out.println("The game is a tie!");
                    gameOver = true;
                } else {
                    // toggle player
                    player = (player.equals("X")) ? "O" : "X";
                }
            } // end single game loop

            playAgain = SafeInput.getYNConfirm(console, "Play again (y/n)? ");
        } while (playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    // Helper Methods (private static)
    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();
        System.out.println("   1   2   3");
        for (int r = 0; r < ROWS; r++) {
            System.out.print((r + 1) + "  ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c]);
                if (c < COLS - 1) System.out.print(" | ");
            }
            System.out.println();
            if (r < ROWS - 1) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return false;
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            boolean rowWin = true;
            for (int c = 0; c < COLS; c++) {
                if (!board[r][c].equals(player)) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            boolean colWin = true;
            for (int r = 0; r < ROWS; r++) {
                if (!board[r][c].equals(player)) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        // main diagonal
        boolean mainDiag = true;
        for (int i = 0; i < ROWS; i++) {
            if (!board[i][i].equals(player)) {
                mainDiag = false;
                break;
            }
        }
        if (mainDiag) return true;

        // anti-diagonal
        boolean antiDiag = true;
        for (int i = 0; i < ROWS; i++) {
            if (!board[i][ROWS - 1 - i].equals(player)) {
                antiDiag = false;
                break;
            }
        }
        return antiDiag;
    }

    private static boolean isTie(int moveCount) {
        // Simple tie detection: all spots filled and no winner
        return moveCount >= ROWS * COLS && !isWin("X") && !isWin("O");
    }
}

