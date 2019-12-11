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

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private Piece piece;
}
