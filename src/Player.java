import Pieces.Piece;

public interface Player {
    Move getNextMove(Board board, Display display);

    Piece.color getColor();
}
