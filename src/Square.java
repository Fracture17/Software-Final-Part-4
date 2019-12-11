public class Square {
    public Square(Piece piece) {
        this.piece = piece;
    }

    public Square() {
        this.piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    private Piece piece;
}
