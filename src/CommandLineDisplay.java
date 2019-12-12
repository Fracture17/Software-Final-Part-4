import Pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineDisplay implements Display {
    public void update(Board board, Piece.color currentPlayerColor) {
        for(int r = 0; r < board.getHeight(); r++) {
            ArrayList<Position> row = board.getRowPositions(r);
            for (Position position : row) {
                Piece piece = board.getPiece(position);

                if (piece == null) {
                    displayEmpty();
                } else {
                    displayPiece(piece);
                }

                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private void displayPiece(Piece piece) {
        displayColor(piece.getColor());
        displayType(piece.getType());
    }

    private void displayColor(Piece.color color) {
        if(color == Piece.color.BLACK) {
            System.out.print("B_");
        }
        else {
            System.out.print("W_");
        }
    }

    private void displayType(Piece.type type) {
        switch (type) {
            case PAWN:
                displayPawn();
                break;
            case KING:
                displayKing();
                break;
            case QUEEN:
                displayQueen();
                break;
            case BISHOP:
                displayBishop();
                break;
            case ROOK:
                displayRook();
                break;
            case KNIGHT:
                displayKnight();
                break;
        }
    }

    private void displayPawn() {
        System.out.print("pawn");
    }

    private void displayKing() {
        System.out.print("king");
    }

    private void displayQueen() {
        System.out.print("queen");
    }

    private void displayBishop() {
        System.out.print("bishop");
    }

    private void displayRook() {
        System.out.print("rook");
    }

    private void displayKnight() {
        System.out.print("knight");
    }

    private void displayEmpty() {
        System.out.print("X");
    }

    public void displayMove(Move move) {
        String sourcePos = posToStr(move.sourcePos);
        String destPos = posToStr(move.destPos);

        System.out.print(sourcePos + "->" + destPos);
    }

    @Override
    public void askForPieceToMove(Board board, Piece.color currentPlayerColor) {
        System.out.println("Enter source pos: ");
    }

    @Override
    public Position getSourcePiecePosition(Board board, Piece.color currentPlayerColor) {
        Scanner scanner = new Scanner(System.in);
        String sourcePos = scanner.nextLine();

        return strToPos(sourcePos, board);
    }

    @Override
    public void askWhereToMove(Board board, Piece.color currentPlayerColor, ArrayList<Move> validMoves) {
        for(Move move: validMoves) {
            displayMove(move);
            System.out.print(", ");
        }

        System.out.println("Enter dest pos: ");
    }

    @Override
    public Position getDestinationPosition(Board board, Piece.color currentPlayerColor) {
        Scanner scanner = new Scanner(System.in);
        String destPos = scanner.nextLine();

        return strToPos(destPos, board);
    }

    @Override
    public void displayWinner(Piece.color winningColor) {
        if(winningColor == Piece.color.WHITE) {
            System.out.println("White wins!");
        }
        else {
            System.out.println("Black wins!");
        }
    }

    private Position strToPos(String pos, Board board) {
        int r = '8' - pos.charAt(1);
        int c = pos.charAt(0) - 'a';

        return board.getPosition(r, c);
    }

    private String posToStr(Position pos) {
        char r = (char) ('8' - pos.getRow());
        char c = (char) (pos.getCol() + 'a');

        return "" + c + r;
    }
}
