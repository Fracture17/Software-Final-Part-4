package Pieces;

//State design pattern
//The type and color of the piece determines its behavior
//Additionally, the Pawn can be promoted to a queen without replacing the object
public class Piece {
    public Piece(color playerColor, type pieceType) {
        this.playerColor = playerColor;
        this.TYPE = pieceType;
    }

    public Piece() {
        this.playerColor = color.NONE;
        this.TYPE = type.NONE;
    }

    public color getColor() {
        return playerColor;
    }

    public type getType() {
        return TYPE;
    }

    public void upgrade() {
        TYPE = type.QUEEN;
    }

    public enum color {
        WHITE,
        BLACK,
        NONE
    }

    public enum type {
        PAWN,
        ROOK,
        QUEEN,
        KING,
        BISHOP,
        KNIGHT,
        NONE
    }

    private color playerColor;
    private type TYPE;
}
