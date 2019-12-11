import Pieces.Piece;

public class Display {
    public void update(Board board) {
        for(int r = 0; r < board.getHeight(); r++) {
            for(int c = 0; c < board.getWidth(); c++) {
                Piece piece = board.getPiece(r, c);

                if(piece == null) {
                    displayEmpty();
                }
                else {
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
        char sourceRowChar = (char) (move.sourceRow + 'a');
        char sourceColChar = (char) (move.sourceCol + '1');

        char destRowChar = (char) (move.destRow + 'a');
        char destColChar = (char) (move.destCol + '1');

        System.out.print("" + sourceRowChar + sourceColChar + "->" + destRowChar + destColChar);
    }
}
