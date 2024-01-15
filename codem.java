public class codem {
    public static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number is already in the same row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if the number is already in the same column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the same 3x3 box
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;  // Backtrack if the solution is not valid
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        System.out.println("╔═══════╦═══════╦═══════╗");
        for (int i = 0; i < 9; i++) {
            if (i != 0 && i % 3 == 0) {
                System.out.println("╠═══════╬═══════╬═══════╣");
            }
            for (int j = 0; j < 9; j++) {
                if (j != 0 && j % 3 == 0) {
                    System.out.print("║ ");
                } else if (j == 0) {
                    System.out.print("║ ");
                }
                if (board[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println("║");
        }
        System.out.println("╚═══════╩═══════╩═══════╝");
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] board = {
            {9, 4, 3, 2, 7, 1, 6, 5, 8},
            {5, 7, 8, 6, 4, 9, 1, 2, 3},
            {1, 2, 6, 3, 5, 8, 4, 9, 7},
            {6, 9, 5, 1, 8, 7, 3, 4, 2},
            {3, 8, 7, 4, 2, 5, 9, 6, 1},
            {4, 1, 2, 9, 6, 3, 5, 8, 7},
            {8, 6, 1, 7, 9, 2, 4, 3, 5},
            {2, 3, 9, 5, 1, 4, 0, 0, 0},
            {7, 5, 4, 8, 3, 6, 0, 0, 0}
        };

        System.out.println("Sudoku puzzle:");
        printBoard(board);

        System.out.println("Solving Sudoku puzzle step by step:\n");

        boolean solved = solveSudokuStepByStep(board);

        if (solved) {
            System.out.println("Sudoku solution:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    public static boolean solveSudokuStepByStep(int[][] board) {
        return solveSudokuStepByStepHelper(board, 0, 0);
    }

    public static boolean solveSudokuStepByStepHelper(int[][] board, int row, int col) {
        if (row == 9) {
            return true;  // Reached the end, puzzle solved
        }

        if (col == 9) {
            return solveSudokuStepByStepHelper(board, row + 1, 0);
        }

        if (board[row][col] != 0) {
            return solveSudokuStepByStepHelper(board, row, col + 1);  // Skip filled cells
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                printBoard(board);
                System.out.println("Placed " + num + " at position (" + (row + 1) + ", " + (col + 1) + ")");
                System.out.println();

                // Explain the current placement
                System.out.println("Checking if " + num + " is valid at position (" + (row + 1) + ", " + (col + 1) + ")");
                System.out.println("Validating against row, column, and 3x3 box constraints...");
                System.out.println("Placement is valid!");

                if (solveSudokuStepByStepHelper(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;  // Backtrack if the solution is not valid

                System.out.println("Removed " + num + " from position (" + (row + 1) + ", " + (col + 1) + ")");
                System.out.println("Invalid solution detected with " + num + " at position (" + (row + 1) + ", " + (col + 1) + ")");
                System.out.println();
            }
        }

        return false;
    }
}
