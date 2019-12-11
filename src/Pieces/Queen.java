package Pieces;

public class Queen extends Piece {
    public Queen(color playerColor) {
        super(playerColor);
    }

    @Override
    public type getType() {
        return type.QUEEN;
    }
}
