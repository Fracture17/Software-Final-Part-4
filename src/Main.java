public class Main {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(new HumanPlayer(), new HumanPlayer());
        chessGame.play();


        Board board = new Board();

        Display display = new CommandLineDisplay();

        display.update(board);
    }
}
