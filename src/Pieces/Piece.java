package Pieces;

public abstract class Piece {
    public Piece(color playerColor) {
        this.playerColor = playerColor;
    }

    public color getColor() {
        return playerColor;
    }

    public abstract type getType();

    public enum color {
        WHITE,
        BLACK
    }

    public enum type {
        PAWN,
        ROOK,
        QUEEN,
        KING,
        BISHOP,
        KNIGHT
    }

    private color playerColor;
}
