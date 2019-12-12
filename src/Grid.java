import Pieces.*;

public class Grid {
    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        squares = new Square[height][width];

        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
                squares[r][c] = new Square(new Piece());
            }
        }

        positions = new Position[height][width];
        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
                positions[r][c] = new Position(r, c);
            }
        }
    }

    public void movePiece(Move move) {
        //Any piece in the dest square is considered taken and removed
        setSquare(move.destPos, getPiece(move.sourcePos));
        setSquare(move.sourcePos, new Piece());
    }

    public void setSquare(Position position, Piece piece) {
        getSquare(position).setPiece(piece);
    }

    public Piece getPiece(Position position) {
        Square square = getSquare(position);
        if(square == null) {
            return new Piece();
        }
        return square.getPiece();
    }

    public Square getSquare(Position position) {
        if(!isInBounds(position)) {
            return null;
        }
        return squares[position.getRow()][position.getCol()];
    }

    public boolean isInBounds(Position position) {
        if(position.getRow() < 0 || position.getRow() >= height) {
            return false;
        }
        if(position.getCol() < 0 || position.getCol() >= width) {
            return false;
        }
        return true;
    }

    //Flyweight Pattern
    //There is only one Position object per square, and they are all
    //contained and referenced through the Grid object.
    //There only needs to be a single object to represent out of bounds
    public Position getPosition(int r, int c) {
        try {
            return positions[r][c];
        }
        catch (Exception e) {
            return outOfBounds;
        }
    }

    private int height;
    private int width;
    private Square[][] squares;
    private Position[][] positions;
    private Position outOfBounds = new Position(-1, -1);

}
