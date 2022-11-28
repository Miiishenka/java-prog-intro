package game.board;

import game.*;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
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
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move.getCol(), move.getRow())) {
            return GameResult.WIN;
        }
        count--;

        if (count == 0) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    private boolean checkWin(int col, int row) {
        int mainDiagonal = 1;
        int secondaryDiagonal = 1;
        int line = 1;
        int vertical = 1;
        for (int i = 1; i <= k; i++) {
            if (col + i < m && field[row][col + i] == turn) {
                vertical++;
            } else {
                break;
            }
        }
        for (int i = 1; i <= k; i++) {
            if (col - i >= 0 && field[row][col - i] == turn) {
                vertical++;
            } else {
                break;
            }
        }
        if (vertical >= k) {
            return true;
        }
        for (int i = 1; i <= k; i++) {
            if (row + i < n && field[row + i][col] == turn) {
                line++;
            } else {
                break;
            }
        }
        for (int i = 1; i <= k; i++) {
            if (row - i >= 0 && field[row - i][col] == turn) {
                line++;
            } else {
                break;
            }
        }
        if (line >= k) {
            return true;
        }
        for (int i = 1; i <= k; i++) {
            if (col + i < m && row + i < n && field[row + i][col + i] == turn) {
                secondaryDiagonal++;
            } else {
                break;
            }
        }
        for (int i = 1; i <= k; i++) {
            if (col - i >= 0 && row - i >= 0 && field[row - i][col - i] == turn) {
                secondaryDiagonal++;
            } else {
                break;
            }
        }
        if (secondaryDiagonal >= k) {
            return true;
        }
        for (int i = 1; i <= k; i++) {
            if (col + i < m && row - i >= 0 && field[row - i][col + i] == turn) {
                mainDiagonal++;
            } else {
                break;
            }
        }
        for (int i = 1; i <= k; i++) {
            if (col - i >= 0 && row + i < n && field[row + i][col - i] == turn) {
                mainDiagonal++;
            } else {
                break;
            }
        }
        if (mainDiagonal >= k) {
            return true;
        }
        return false;
    }

    public boolean isValid(final Move move) {
        for (int[] obst : obstacles) {
            if (move.getRow() == obst[0] && move.getCol() == obst[1]) {
                return false;
            }
        }
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < m; i++) {
            sb.append(i + 1);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < n; r++) {
            sb.append(r + 1);
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
