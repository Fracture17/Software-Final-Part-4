public class ChessGame {
    public ChessGame(Player a, Player b) {
        this.a = a;
        this.b = b;
    }

    public void play() {
        display.update(board);
        while(true) {
            Move move = getNextMove();
            if(isMoveValid(move)) {
                makeMove(move);
                display.update(board);
            }
            else {
                System.out.println("Bad move");
            }
        }
    }

    public Move getNextMove() {
        return a.getNextMove(board, display);
    }

    public boolean isMoveValid(Move move) {
        return board.isMoveValid(move);
    }

    public void makeMove(Move move) {
        board.makeMove(move);
    }



    Board board = new Board();
    private Player a;
    private Player b;
    private Display display = new Display();
}
