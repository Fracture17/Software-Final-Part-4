import Pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {

    @Override
    public Move getNextMove(Board board, Display display) {
        display.askForPieceToMove(board, Piece.color.WHITE);
        Position source = display.getSourcePiecePosition(board, Piece.color.WHITE);

        ArrayList<Move> validMoves = PieceMoves.getValidMoves(board, source.getRow(), source.getCol());

        display.askWhereToMove(board, Piece.color.WHITE, validMoves);
        Position dest = display.getDestinationPosition(board, Piece.color.WHITE);

        return new Move(source.getRow(), source.getCol(), dest.getRow(), dest.getCol());
    }

    private ArrayList<Move> getValidMoves(Board board, int r, int c) {
        ArrayList<Move> potentialMoves = PieceMoves.getPotentialMoves(board, r, c);
        ArrayList<Move> validMoves = new ArrayList<>();
        for(Move move: potentialMoves) {
            if(board.isMoveValid(move)) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }
}
