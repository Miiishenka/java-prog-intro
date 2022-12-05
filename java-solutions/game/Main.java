package game;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter m, n, k");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println("Choose obstacles: [d]iagonal, [c]ustom");
        String obst = scanner.next();
        int countObstacles;
        int[][] obstacles;
        if (obst.equals("c")) {
            System.out.println("Enter the number of obstacles");
            countObstacles = scanner.nextInt();
            obstacles = new int[countObstacles][];
            System.out.println("Enter " + countObstacles + " pairs of obstacle coordinates in different lines");
            for (int i = 0; i < countObstacles; i++) {
                int y = scanner.nextInt();
                int x = scanner.nextInt();
                obstacles[i] = new int[]{y - 1, x - 1};
            }
        } else {
            countObstacles = 2 * Math.max(m, n);
            obstacles = new int[countObstacles][];
            for (int i = 0; i < countObstacles / 2; i++) {
                obstacles[i] = new int[]{i, i};
            }
            for (int i = 0; i < countObstacles / 2; i++) {
                obstacles[i + countObstacles / 2] = new int[]{n - i - 1, i};
            }
        }
        System.out.println("Choose option: [t]ournment, [g]ame");
        String option = scanner.next();
        int playersCount;
        if (option.equals("t")) {
            System.out.println("Enter number of players");
            playersCount = scanner.nextInt();
        } else {
            playersCount = 2;
        }
        Player[] players = new Player[playersCount];
        System.out.println("Choose " + playersCount + " players in different lines: " +
                "[R]andom player, [H]uman player, [S]equential player");
        for (int i = 0; i < playersCount; i++) {
            String player = scanner.next();
            if (player.equals("R")) {
                players[i] = new RandomPlayer(m, n);
            } else if (player.equals("H")) {
                players[i] = new HumanPlayer(scanner);
            } else {
                players[i] = new SequentialPlayer(n, m);
            }
        }
        if (option.equals("t")) {
            new Tournament(players, n, m, k, obstacles).toPlay();
        } else {
            final int result = new TwoPlayerGame(
                    new TicTacToeBoard(n, m, k, obstacles),
                    players[0], players[1]
            ).play(true);
            switch (result) {
                case 1:
                    System.out.println("First player won");
                    break;
                case 2:
                    System.out.println("Second player won");
                    break;
                case 0:
                    System.out.println("Draw");
                    break;
                default:
                    throw new AssertionError("Unknown result " + result);
            }
        }
    }
}
