import Pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {
    HumanPlayer(Piece.color color) {
        this.color = color;
    }

    @Override
    public Move getNextMove(Board board, Display display) {
        ArrayList<Move> validMoves = new ArrayList<>();
        Position source = board.getPosition(-1, -1);
        while (validMoves.size() == 0) {
            display.askForPieceToMove(board, getColor());
            source = display.getSourcePiecePosition(board, getColor());

            validMoves = PieceMoves.getValidMoves(board, source);
        }

        display.askWhereToMove(board, getColor(), validMoves);
        Position dest = display.getDestinationPosition(board, getColor());

        return new Move(source, dest);
    }

    @Override
    public Piece.color getColor() {
        return color;
    }

    private Piece.color color;
}
