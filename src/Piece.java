public abstract class Piece {
    public Piece(color playerColor) {
        this.playerColor = playerColor;
    }

    public abstract color getColor();

    public abstract type getType();

    enum color {
        WHITE,
        BLACK
    }

    enum type {
        PAWN,
        ROOK,
        QUEEN,
        KING,
        BISHOP,
        KNIGHT
    }

    private color playerColor;
}
