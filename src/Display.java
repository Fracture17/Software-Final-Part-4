import Pieces.Piece;

import java.util.ArrayList;

public interface Display {
    void update(Board board);

    void displayMove(Move move);

    void askForPieceToMove(Board board, Piece.color playerColor);

    Position getSourcePiecePosition(Board board, Piece.color playerColor);

    void askWhereToMove(Board board, Piece.color playerColor, ArrayList<Move> validMoves);

    Position getDestinationPosition(Board board, Piece.color playerColor);
}
