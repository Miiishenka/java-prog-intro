package game;

import java.util.Arrays;
import java.util.Scanner;

public class Tournament {
    private final Player[] players;
    private final int n;
    private final int m;
    private final int k;
    private final int[][] obstacles;
    private final int[][] table;
    public Tournament(Player[] players, int n, int m, int k, int[][] obstacles) {
        this.players = players;
        this.n = n;
        this.m = m;
        this.k = k;
        this.obstacles = obstacles;
        this.table = new int[players.length][players.length];
        for (int[] tab: table) {
            Arrays.fill(tab, -1);
        }
    }
    public void toPlay() {
        for (int i = 0; i < players.length; i++) {
            for (int j = i + 1; j < players.length; j++) {
                int first = 0;
                int second = 0;
                System.out.println("Game: Player " + i + 1 + "- Player " + j + 1);
                int result = new TwoPlayerGame(
                        new TicTacToeBoard(n, m, k, obstacles),
                        players[i],
                        players[j]
                ).play(true);
                switch (result) {
                    case 1:
                        System.out.println(i + 1 + " player won");
                        first++;
                        break;
                    case 2:
                        System.out.println(j + 1 + " player won");
                        second++;
                        break;
                    case 0:
                        System.out.println("Draw");
                        break;
                    default:
                        throw new AssertionError("Unknown result " + result);
                }
                System.out.println("Game: Player " + j + 1 + "- Player " + i + 1);
                result = new TwoPlayerGame(
                        new TicTacToeBoard(n, m, k, obstacles),
                        players[j],
                        players[i]
                ).play(true);
                switch (result) {
                    case 1:
                        System.out.println(j + 1 + " player won");
                        second++;
                        break;
                    case 2:
                        System.out.println(i + 1 + " player won");
                        first++;
                        break;
                    case 0:
                        System.out.println("Draw");
                        break;
                    default:
                        throw new AssertionError("Unknown result " + result);
                }
                if (first > second) {
                    table[i][j] = 3;
                    table[j][i] = 0;
                } else if (second > first) {
                    table[i][j] = 0;
                    table[j][i] = 3;
                } else {
                    table[i][j] = 1;
                    table[j][i] = 1;
                }
            }
        }
        System.out.println("Table:");
        System.out.print("  ");
        for (int i = 1; i <= players.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println("Points");
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + 1 + " ");
            int sum = 0;
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == -1) {
                    System.out.print("X ");
                } else {
                    System.out.print(table[i][j] + " ");
                    sum += table[i][j];
                }
            }
            System.out.println(sum);
        }
    }
}
