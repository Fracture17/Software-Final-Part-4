package Pieces;

public class Rook extends Piece {
    public Rook(color playerColor) {
        super(playerColor);
    }

    @Override
    public type getType() {
        return type.ROOK;
    }
}
