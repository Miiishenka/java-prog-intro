package game.players;

import game.*;
import game.board.TicTacToeBoard;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) { // :NOTE: ни у кого не должен быть доступ к доске
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        do {
            String first = in.next();
            String second = in.next();
            try {
                Move move = new Move(Integer.parseInt(first) - 1, Integer.parseInt(second) - 1, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
                System.out.println("Your move is invalid. Enter new move");
            }
            catch (NumberFormatException e) {
                System.out.println("This is not a move. Enter new move");
            }
        } while (true);
    }
}
