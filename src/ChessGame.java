import Pieces.Piece;

//State design pattern
//The types of Players and the Display the game uses are determined at runtime
//They are also passed in from the calling code
public class ChessGame {
    public ChessGame(Player a, Player b, Display display) {
        this.playerA = a;
        this.playerB = b;
        this.display = display;
    }

    public void play() {
        display.update(board, currentPlayerColor);
        while(takeTurn()) {
        }
    }

    private boolean takeTurn() {
        Move move = getNextMove();
        if(isMoveValid(move)) {
            makeMove(move);
            if(currentPlayerColor == Piece.color.WHITE) {
                currentPlayerColor = Piece.color.BLACK;
            }
            else {
                currentPlayerColor = Piece.color.WHITE;
            }

            if(isKingTaken(currentPlayerColor)) {
                if(currentPlayerColor == Piece.color.WHITE) {
                    display.displayWinner(Piece.color.BLACK);
                    return false;
                }
                else {
                    display.displayWinner(Piece.color.WHITE);
                    return false;
                }
            }
            upgradePawns();

            display.update(board, currentPlayerColor);
        }

        return true;
    }

    public Move getNextMove() {
        if(currentPlayerColor == playerA.getColor()) {
            return playerA.getNextMove(board, display);
        }
        return playerB.getNextMove(board, display);
    }

    public boolean isMoveValid(Move move) {
        return board.isMoveValid(move);
    }

    public void makeMove(Move move) {
        board.makeMove(move);
    }

    private boolean isKingTaken(Piece.color color) {
        for(Position position: board.getAllPositions()) {
            if(board.getPiece(position).getType() == Piece.type.KING) {
                if(board.getPiece(position).getColor() == color) {
                    return false;
                }
            }
        }

        return true;
    }

    private void upgradePawns() {
        for(Position position: board.getRowPositions(0)) {
            if(board.getPiece(position).getType() == Piece.type.PAWN) {
                board.getPiece(position).upgrade();
            }
        }

        for(Position position: board.getRowPositions(board.getHeight() - 1)) {
            if(board.getPiece(position).getType() == Piece.type.PAWN) {
                board.getPiece(position).upgrade();
            }
        }
    }

    Board board = new Board();
    private Player playerA;
    private Player playerB;
    private Display display;
    private Piece.color currentPlayerColor = Piece.color.WHITE;
}
