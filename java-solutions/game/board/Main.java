package game.board;

import game.TwoPlayerGame;
import game.players.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final int n = 11;
    public static final int m = 11;
    public static final int k = 3;
    public static void main(String[] args) {

        int[][] obstacles = new int[n + m][];
        int i = 0;
        while (i < m && i < n) {
            obstacles[i] = new int[]{i, i};
            i++;
        }
        while (i < 2 * m) {
            obstacles[i] = new int[]{i - m, 2 * m - i - 1};
            i++;
        }
        Player[] players = new Player[5];
        Arrays.fill(players, new RandomPlayer(m, n));
//        players[0] = new HumanPlayer(new Scanner(System.in));

        new Tournament(players, n, m, k, obstacles).toPlay();
    }
}
