package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );
    private final int m;
    private final int n;
    private final int k;
    private int count;
    private final int[][] obstacles;

    private final Cell[][] field;
    private Cell turn;

    public TicTacToeBoard(int n, int m, int k, int[][] obstacles) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.count = m * n;
        this.obstacles = obstacles;
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return new TicTacPosition(n, m, obstacles, field, turn);
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!getPosition().isValid(move)) {
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move.getCol(), move.getRow(), 1, 1)
                || checkWin(move.getCol(), move.getRow(), 1, 0)
                || checkWin(move.getCol(), move.getRow(), 0, 1)
                || checkWin(move.getCol(), move.getRow(), 1, -1)) {
            return GameResult.WIN;
        }
        count--;

        if (count - obstacles.length == 0) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    private boolean checkWin(int col, int row, int dx, int dy) {
        int maxLine = 1;
        for (int i = 1; i <= k; i++) {
            if (col + i * dy >= 0 && col + i * dy < m
                    && row + i * dx >= 0 && row + i * dx < n
                    && field[row + i * dx][col + i * dy] == turn) {
                maxLine++;
            } else {
                break;
            }
        }
        for (int i = 1; i <= k; i++) {
            if (col - i * dy >= 0 && col - i * dy < m
                    && row - i * dx >= 0 && row - i * dx < n
                    && field[row - i * dx][col - i * dy] == turn) {
                maxLine++;
            } else {
                break;
            }
        }
        return maxLine >= k;
    }
}
