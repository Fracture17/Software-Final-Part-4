public class Grid {
    public Grid() {
        Piece.color x = Piece.color.BLACK;
        int c = 0;
        squares[0][c] = new Square(new Rook(x)); c++;
        squares[0][c] = new Square(new Knight(x)); c++;
        squares[0][c] = new Square(new Bishop(x)); c++;
        squares[0][c] = new Square(new Queen(x)); c++;
        squares[0][c] = new Square(new King(x)); c++;
        squares[0][c] = new Square(new Bishop(x)); c++;
        squares[0][c] = new Square(new Knight(x)); c++;
        squares[0][c] = new Square(new Rook(x)); c++;


        for(c = 0; c < WIDTH; c++) {
            squares[1][c] = new Square(new Pawn(x));
        }



        for(int r = 2; r < HEIGHT - 1 - 1; r++) {
            for(c = 0; c < WIDTH; c++) {
                squares[r][c] = new Square();
            }
        }



        for(c = 0; c < WIDTH; c++) {
            squares[HEIGHT - 1 - 1][c] = new Square(new Pawn(Piece.color.WHITE));
        }


        x = Piece.color.WHITE;
        c = 0;
        squares[HEIGHT - 1][c] = new Square(new Rook(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new Knight(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new Bishop(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new Queen(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new King(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new Bishop(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new Knight(x)); c++;
        squares[HEIGHT - 1][c] = new Square(new Rook(x)); c++;
    }

    public void movePiece(int sourceRow, int sourceCol, int destRow, int destCol) {
        //Any piece in the dest square is considered taken and removed
        setSquare(destRow, destCol, getPiece(sourceRow, sourceCol));
        setSquare(sourceRow, sourceCol, null);
    }

    private void setSquare(int r, int c, Piece piece) {
        getSquare(r, c).setPiece(piece);
    }

    public Piece getPiece(int r, int c) {
        return getSquare(r, c).getPiece();
    }

    private Square getSquare(int r, int c) {
        return squares[r][c];
    }


    public final int HEIGHT = 8;
    public final int WIDTH = 8;

    private Square[][] squares = new Square[HEIGHT][WIDTH];
}
