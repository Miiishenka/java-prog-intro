package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final int m;
    private final int n;
    private final Random random;
    public RandomPlayer(int m, int n) {
        this.random = new Random();
        this.m = m;
        this.n = n;
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(n),
                    random.nextInt(m),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
