import Pieces.Piece;

import java.util.ArrayList;

public class Board {
    public Board() {
        //Builder design pattern
        //Setup of the chess pieces is left to the Board object, rather than the Grid implementation

        setupPlayerPieces(1, 0, Piece.color.BLACK);

        setupPlayerPieces(HEIGHT - 1 - 1, HEIGHT - 1, Piece.color.WHITE);
    }

    private void setupPlayerPieces(int pawnRow, int otherPiecesRow, Piece.color color) {
        for(Position position: getRowPositions(pawnRow)) {
            grid.setSquare(position, new Piece(color, Piece.type.PAWN));
        }

        ArrayList<Position> positions = getRowPositions(otherPiecesRow);

        int c = 0;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.ROOK)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.KNIGHT)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.BISHOP)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.QUEEN)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.KING)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.BISHOP)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.KNIGHT)); c++;
        grid.setSquare(positions.get(c), new Piece(color, Piece.type.ROOK)); c++;
    }

    public Piece getPiece(Position position) {
        return grid.getPiece(position);
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public void makeMove(Move move) {
        grid.movePiece(move);
    }

    public boolean isMoveValid(Move move) {
        Square source = grid.getSquare(move.sourcePos);
        Square dest = grid.getSquare(move.destPos);

        if(source == null || dest == null) {
            return false;
        }

        if(source.getPiece() == null) {
            return false;
        }

        if(!PieceMoves.getPotentialMoves(this, move.sourcePos).contains(move)) {
            return false;
        }

        if(dest.getPiece() != null) {
            return source.getPiece().getColor() != dest.getPiece().getColor();
        }

        return true;
    }

    public ArrayList<Position> getAllPositions() {
        ArrayList<Position> positions = new ArrayList<>();
        for(int r = 0; r < getHeight(); r++) {
            positions.addAll(getRowPositions(r));
        }
        return positions;
    }

    public ArrayList<Position> getRowPositions(int r) {
        ArrayList<Position> positions = new ArrayList<>();
        for(int c = 0; c < WIDTH; c++) {
            positions.add(getPosition(r, c));
        }
        return positions;
    }

    public boolean isInBounds(Position position) {
        return grid.isInBounds(position);
    }


    public Position getPosition(int r, int c) {
        return grid.getPosition(r, c);
    }

    public final int HEIGHT = 8;
    public final int WIDTH = 8;

    private Grid grid = new Grid(HEIGHT, WIDTH);
}
