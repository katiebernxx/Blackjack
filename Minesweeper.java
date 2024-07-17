
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    /**
     * The dimension of the board.
     */
    private int n;

    /**
     * This value is true only when the game has finished.
     */
    private boolean finished;

    /**
     * The number of unique guesses made by the player.
     */
    private int guesses;

    /**
     * The private board, not shown to user.
     * A true value denotes a mine at the corresponding location.
     */
    private boolean[][] board;

    /**
     * The initial number of mines created in construction.
     */
    private int numberOfMines;

    /**
     * The board as seen by the user. The chars denote how many mines are adjacent
     * to each cell.
     */
    protected char[][] viewingBoard;

    public Minesweeper(int size, double density) {
        n = size;
        finished = false;
        guesses = 0;

        // This creates a boolean grid with n rows and n columns. In general,
        // the number of columns does not have to be the same as the number of rows.
        board = new boolean[n][n];
        viewingBoard = new char[n][n];

        // This method can create a 2-d grid with a varying number of columns
        // per row
        // board = new boolean[n][];
        // for(int i = 0; i < n; i++){
        // board[i] = new boolean[i];
        // }
        // TBD

        Random rand = new Random();
        // Let's fill the board with the appropriate number of mines:
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // I want to put a mine at location (r, c) with probability
                // whatever density is.
                board[r][c] = rand.nextDouble() < density;
                if (board[r][c] == true) {
                    numberOfMines++;
                }
                // Note that the fraction of numbers that are between 0 and 1
                // and are less than density is precisely density.

                viewingBoard[r][c] = ' ';
            }
        }
    }

    /**
     * Enters a guess into the board and updates viewingBoard to reflect this
     * change. Returns true if and only if the guess was collocated with a mine.
     * 
     * @param row The row to check
     * @param col The column to check
     * @return true if the guess was collocated with a mine, otherwise false
     */
    public boolean guess(int row, int col) {
        if (viewingBoard[row][col] != ' ') {
            return board[row][col];
        }
        guesses++;
        if (board[row][col] == true) {
            finished = true;
            viewingBoard[row][col] = 'X';
            return true;
        } else {
            // we need to calculate the number of mines around this location
            int localMines = 0;
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = col - 1; c < col + 2; c++) {
                    if (r >= 0 && r < n && c >= 0 && c < n && board[r][c] == true) {
                        localMines++;
                    }
                }
            } // See https://www.alpharithms.com/ascii-table-512119/
            viewingBoard[row][col] = (char) (localMines + 48);
            return false;
        }
    }

    /**
     * Returns whether the game has finished.
     * 
     * @return true if the game has finished, otherwise false
     */
    public boolean finished() {
        return finished;
    }

    public String toString() {
        String out = " -" + "--".repeat(n) + "\n";
        for (int i = 0; i < n; i++) {
            out += "| ";
            for (int j = 0; j < n; j++) {
                out += viewingBoard[i][j] + " ";
            }
            out += "|\n";
        }
        return out + " -" + "--".repeat(n);
    }

    public static void main(String[] args) {
        // the following for loop would print out the command line arguments
        // for(String str : args){
        // System.out.println(str);
        // }

        // A scanner is a way of getting input from the terminal/cmd
        Scanner scanner = new Scanner(System.in);

        Minesweeper ms = new Minesweeper(10, .1);
        System.out.println(ms);

        while (!ms.finished()) {
            System.out.print("Pick a row and col (eg: 1 5): ");
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            ms.guess(r, c);
            System.out.println(ms);
        }
        scanner.close();
    }

}
