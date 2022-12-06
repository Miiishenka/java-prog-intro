package game;

import java.util.Map;

public class TicTacPosition implements Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );
    private final int m;
    private final int n;
    private final int[][] obstacles;

    private final Cell[][] field;
    private Cell turn;

    public TicTacPosition(int n, int m, int[][] obstacles, Cell[][] field, Cell turn) {
        this.n = n;
        this.m = m;
        this.obstacles = obstacles;
        this.field = field;
        this.turn = turn;
    }
    public Cell getTurn() {
        return turn;
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

        sb.append('\n').append("Obstacles are:").append('\n');
        for (int[] obst: obstacles) {
            sb.append(obst[0] + 1).append(" ").append(obst[1] + 1).append('\n');
        }
        return sb.toString();
    }
}
