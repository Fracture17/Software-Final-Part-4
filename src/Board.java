public class Board {
    public Piece getPiece(int r, int c) {
        return grid.getPiece(r, c);
    }

    public int getHeight() {
        return grid.HEIGHT;
    }

    public int getWidth() {
        return grid.WIDTH;
    }

    public void makeMove(Move move) {
        grid.movePiece(move);
    }

    public boolean isMoveValid(Move move) {
        Square source = grid.getSquare(move.sourceRow, move.sourceCol);
        Square dest = grid.getSquare(move.destRow, move.destCol);

        if(source == null || dest == null) {
            return false;
        }

        if(source.getPiece() == null) {
            return false;
        }

        if(!PieceMoves.getPotentialMoves(this, move.sourceRow, move.sourceCol).contains(move)) {
            return false;
        }

        if(dest.getPiece() != null) {
            if (source.getPiece().getColor() == dest.getPiece().getColor()) {
                return false;
            }
        }

        return true;
    }

    private Grid grid = new Grid();
}
