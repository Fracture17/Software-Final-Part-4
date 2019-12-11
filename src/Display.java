import Pieces.Piece;

import java.util.ArrayList;

public interface Display {
    void update(Board board, Piece.color currentPlayerColor);

    void displayMove(Move move);

    void askForPieceToMove(Board board, Piece.color currentPlayerColor);

    Position getSourcePiecePosition(Board board, Piece.color currentPlayerColor);

    void askWhereToMove(Board board, Piece.color currentPlayerColor, ArrayList<Move> validMoves);

    Position getDestinationPosition(Board board, Piece.color currentPlayerColor);
}
