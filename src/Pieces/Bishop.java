package Pieces;

public class Bishop extends Piece {
    public Bishop(color playerColor) {
        super(playerColor);
    }

    @Override
    public type getType() {
        return type.BISHOP;
    }
}
