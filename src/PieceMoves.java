import Pieces.Piece;

import java.util.ArrayList;

public class PieceMoves {
    public static ArrayList<Move> getValidMoves(Board board, Position position) {
        ArrayList<Move> potentialMoves = getPotentialMoves(board, position);
        ArrayList<Move> validMoves = new ArrayList<>();
        for(Move move: potentialMoves) {
            if(board.isMoveValid(move)) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }

    static ArrayList<Move> getPotentialMoves(Board board, Position position) {
        Piece piece = board.getPiece(position);

        switch (piece.getType()) {
            case PAWN:
                return getPawnMoves(board, position);
            case KING:
                return getKingMoves(board, position);
            case QUEEN:
                return getQueenMoves(board, position);
            case BISHOP:
                return getBishopMoves(board, position);
            case ROOK:
                return getRookMoves(board, position);
            case KNIGHT:
                return getKnightMoves(board, position);
            default:
                return new ArrayList<>();
        }
    }

    private static ArrayList<Move> getPawnMoves(Board board, Position position) {
        Piece.color pieceColor = board.getPiece(position).getColor();

        ArrayList<Move> moves = new ArrayList<>();

        int direction = pieceColor == Piece.color.BLACK ? 1 : -1;

        int r = position.getRow();
        int c = position.getCol();

        int doubleRow = pieceColor == Piece.color.BLACK ? 1 : 6;

        Position forward = board.getPosition(r + direction, c);
        if(board.getPiece(forward).getType() == Piece.type.NONE) {
            moves.add(new Move(position, forward));
            if(r == doubleRow) {
                Position doubleForward = board.getPosition(r + direction * 2, c);
                if(board.getPiece(doubleForward).getType() == Piece.type.NONE) {
                    moves.add(new Move(position, doubleForward));
                }
            }
        }

        Position diagonalLeft = board.getPosition(r + direction, c - 1);
        if(board.getPiece(diagonalLeft).getType() != Piece.type.NONE) {
            if(board.getPiece(diagonalLeft).getColor() != pieceColor) {
                moves.add(new Move(position, diagonalLeft));
            }
        }

        Position diagonalRight = board.getPosition(r + direction, c + 1);
        if(board.getPiece(diagonalRight).getType() != Piece.type.NONE) {
            if(board.getPiece(diagonalRight).getColor() != pieceColor) {
                moves.add(new Move(position, diagonalRight));
            }
        }

        return moves;
    }

    private static ArrayList<Move> getKingMoves(Board board, Position position) {
        int r = position.getRow();
        int c = position.getCol();

        ArrayList<Move> moves = new ArrayList<>();
        for(int y = -1; y <= 1; y++) {
            for(int x = -1; x <= 1; x++) {
                if(y != 0 || x != 0) {
                    moves.add(new Move(position, board.getPosition(r + y, c + x)));
                }
            }
        }
        return moves;
    }

    private static ArrayList<Move> getQueenMoves(Board board, Position position) {
        ArrayList<Move> moves = getBishopMoves(board, position);
        moves.addAll(getRookMoves(board, position));

        return moves;
    }

    private static ArrayList<Move> getBishopMoves(Board board, Position position) {
        ArrayList<Move> moves = new ArrayList<>();

        moves.addAll(getMovesInLine(board, position, 1, 1));
        moves.addAll(getMovesInLine(board, position, -1, 1));
        moves.addAll(getMovesInLine(board, position, 1, -1));
        moves.addAll(getMovesInLine(board, position, -1, -1));

        return moves;
    }

    private static ArrayList<Move> getMovesInLine(Board board, Position start, int directionR, int directionC) {
        Piece.color pieceColor = board.getPiece(start).getColor();

        ArrayList<Move> moves = new ArrayList<>();

        int r = start.getRow();
        int c = start.getCol();

        Position position = board.getPosition(r + directionR, c + directionC);

        while (board.isInBounds(position)) {
            if(board.getPiece(position).getType() == Piece.type.NONE) {
                moves.add(new Move(start, position));
            }
            else {
                if(board.getPiece(position).getColor() != pieceColor) {
                    moves.add(new Move(start, position));
                }
                break;
            }
            position = board.getPosition(position.getRow() + directionR, position.getCol() + directionC);
        }

        return moves;
    }

    private static ArrayList<Move> getRookMoves(Board board, Position position) {
        ArrayList<Move> moves = new ArrayList<>();

        moves.addAll(getMovesInLine(board, position, 1, 0));
        moves.addAll(getMovesInLine(board, position, -1, 0));
        moves.addAll(getMovesInLine(board, position, 0, 1));
        moves.addAll(getMovesInLine(board, position, 0, -1));

        return moves;
    }

    private static ArrayList<Move> getKnightMoves(Board board, Position position) {
        ArrayList<Move> moves = new ArrayList<>();

        int r = position.getRow();
        int c = position.getCol();

        moves.add(new Move(position, board.getPosition(r + 1, c + 2)));
        moves.add(new Move(position, board.getPosition(r + 1, c - 2)));
        moves.add(new Move(position, board.getPosition(r + 2, c + 1)));
        moves.add(new Move(position, board.getPosition(r + 2, c - 1)));
        moves.add(new Move(position, board.getPosition(r - 1, c + 2)));
        moves.add(new Move(position, board.getPosition(r - 1, c - 2)));
        moves.add(new Move(position, board.getPosition(r - 2, c + 1)));
        moves.add(new Move(position, board.getPosition(r - 2, c - 1)));

        return moves;
    }
}
