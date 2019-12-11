import Pieces.Piece;

import java.util.ArrayList;

public class PieceMoves {
    static ArrayList<Move> getPotentialMoves(Board board, int r, int c) {
        Piece piece = board.getPiece(r, c);

        switch (piece.getType()) {
            case PAWN:
                return getPawnMoves(board, r, c);
            case KING:
                return getKingMoves(board, r, c);
            case QUEEN:
                return getQueenMoves(board, r, c);
            case BISHOP:
                return getBishopMoves(board, r, c);
            case ROOK:
                return getRookMoves(board, r, c);
            case KNIGHT:
                return getKnightMoves(board, r, c);
            default:
                return new ArrayList<>();
        }
    }

    private static ArrayList<Move> getPawnMoves(Board board, int r, int c) {
        Piece.color pieceColor = board.getPiece(r, c).getColor();

        ArrayList<Move> moves = new ArrayList<>();

        if(pieceColor == Piece.color.BLACK) {
            if(board.getPiece(r + 1, c) == null) {
                moves.add(new Move(r, c, r + 1, c));
                if(r == 1) {
                    if(board.getPiece(r + 2, c) == null) {
                        moves.add(new Move(r, c, r + 2, c));
                    }
                }
            }

            if(board.getPiece(r + 1, c + 1) != null) {
                if(board.getPiece(r + 1, c + 1).getColor() != pieceColor) {
                    moves.add(new Move(r, c, r + 1, c + 1));
                }
            }

            if(board.getPiece(r + 1, c - 1) != null) {
                if(board.getPiece(r + 1, c - 1).getColor() != pieceColor) {
                    moves.add(new Move(r, c, r + 1, c - 1));
                }
            }
        }

        if(pieceColor == Piece.color.WHITE) {
            if(board.getPiece(r - 1, c) == null) {
                moves.add(new Move(r, c, r - 1, c));
                if(r == 7) {
                    if(board.getPiece(r - 2, c) == null) {
                        moves.add(new Move(r, c, r - 2, c));
                    }
                }
            }

            if(board.getPiece(r - 1, c + 1) != null) {
                if(board.getPiece(r - 1, c + 1).getColor() != pieceColor) {
                    moves.add(new Move(r, c, r - 1, c + 1));
                }
            }

            if(board.getPiece(r - 1, c - 1) != null) {
                if(board.getPiece(r - 1, c - 1).getColor() != pieceColor) {
                    moves.add(new Move(r, c, r - 1, c - 1));
                }
            }
        }

        return moves;
    }

    private static ArrayList<Move> getKingMoves(Board board, int r, int c) {
        //TODO: check for "check"
        ArrayList<Move> moves = new ArrayList<>();
        for(int y = -1; y <= 1; y++) {
            for(int x = -1; x <= 1; x++) {
                if(y != 0 || x != 0) {
                    moves.add(new Move(r, c, r + y, c + x));
                }
            }
        }
        return moves;
    }

    private static ArrayList<Move> getQueenMoves(Board board, int r, int c) {
        ArrayList<Move> moves = getBishopMoves(board, r, c);
        moves.addAll(getRookMoves(board, r, c));

        return moves;
    }

    private static ArrayList<Move> getBishopMoves(Board board, int r, int c) {
        Piece.color pieceColor = board.getPiece(r, c).getColor();

        ArrayList<Move> moves = new ArrayList<>();

        int y = 1;
        int x = 1;
        for(int newR = r + y, newC = c + x; isInBounds(board, newR, newC); newR += y, newC += x) {
            if(board.getPiece(newR, newC) == null) {
                moves.add(new Move(r, c, newR, newC));
            }
            else {
                if(board.getPiece(newR, newC).getColor() != pieceColor) {
                    moves.add(new Move(r, c, newR, newC));
                }
                break;
            }
        }

        y = -1;
        x = 1;
        for(int newR = r + y, newC = c + x; isInBounds(board, newR, newC); newR += y, newC += x) {
            if(board.getPiece(newR, newC) == null) {
                moves.add(new Move(r, c, newR, newC));
            }
            else {
                if(board.getPiece(newR, newC).getColor() != pieceColor) {
                    moves.add(new Move(r, c, newR, newC));
                }
                break;
            }
        }

        y = 1;
        x = -1;
        for(int newR = r + y, newC = c + x; isInBounds(board, newR, newC); newR += y, newC += x) {
            if(board.getPiece(newR, newC) == null) {
                moves.add(new Move(r, c, newR, newC));
            }
            else {
                if(board.getPiece(newR, newC).getColor() != pieceColor) {
                    moves.add(new Move(r, c, newR, newC));
                }
                break;
            }
        }

        y = -1;
        x = -1;
        for(int newR = r + y, newC = c + x; isInBounds(board, newR, newC); newR += y, newC += x) {
            if(board.getPiece(newR, newC) == null) {
                moves.add(new Move(r, c, newR, newC));
            }
            else {
                if(board.getPiece(newR, newC).getColor() != pieceColor) {
                    moves.add(new Move(r, c, newR, newC));
                }
                break;
            }
        }

        return moves;
    }

    private static boolean isInBounds(Board board, int r, int c) {
        return r >= 0 && r < board.getHeight() && c >= 0 && c < board.getWidth();
    }

    private static ArrayList<Move> getRookMoves(Board board, int r, int c) {
        Piece.color pieceColor = board.getPiece(r, c).getColor();

        ArrayList<Move> moves = new ArrayList<>();

        for(int y = r + 1; y < board.getHeight(); y++) {
            if(board.getPiece(y, c) == null) {
                moves.add(new Move(r, c, y, c));
            }
            else {
                if(board.getPiece(y, c).getColor() != pieceColor) {
                    moves.add(new Move(r, c, y, c));
                }
                break;
            }
        }

        for(int y = r - 1; y >= 0; y--) {
            if(board.getPiece(y, c) == null) {
                moves.add(new Move(r, c, y, c));
            }
            else {
                if(board.getPiece(y, c).getColor() != pieceColor) {
                    moves.add(new Move(r, c, y, c));
                }
                break;
            }
        }

        for(int x = c + 1; x < board.getWidth(); x++) {
            if(board.getPiece(r, x) == null) {
                moves.add(new Move(r, c, r, x));
            }
            else {
                if(board.getPiece(r, x).getColor() != pieceColor) {
                    moves.add(new Move(r, c, r, x));
                }
                break;
            }
        }

        for(int x = c - 1; x >= 0; x--) {
            if(board.getPiece(r, x) == null) {
                moves.add(new Move(r, c, r, x));
            }
            else {
                if(board.getPiece(r, x).getColor() != pieceColor) {
                    moves.add(new Move(r, c, r, x));
                }
                break;
            }
        }

        return moves;
    }

    private static ArrayList<Move> getKnightMoves(Board board, int r, int c) {
        ArrayList<Move> moves = new ArrayList<>();

        moves.add(new Move(r, c, r + 1, c + 2));
        moves.add(new Move(r, c, r + 1, c - 2));
        moves.add(new Move(r, c, r + 2, c + 1));
        moves.add(new Move(r, c, r + 2, c - 1));
        moves.add(new Move(r, c, r - 1, c + 2));
        moves.add(new Move(r, c, r - 1, c - 2));
        moves.add(new Move(r, c, r - 2, c + 1));
        moves.add(new Move(r, c, r - 2, c - 1));

        return moves;
    }
}
