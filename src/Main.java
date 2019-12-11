import Pieces.Piece;

public class Main {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(new HumanPlayer(Piece.color.WHITE), new HumanPlayer(Piece.color.BLACK));
        chessGame.play();

    }
}
