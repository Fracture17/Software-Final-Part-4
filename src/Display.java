public class Display {
    public void update(Board board) {
        for(int r = 0; r < board.getHeight(); r++) {
            for(int c = 0; c < board.getWidth(); c++) {
                Piece piece = board.getPiece(r, c);
                if(piece == null) {
                    System.out.print("X\t");
                    continue;
                }

                if(piece.getColor() == Piece.color.BLACK) {
                    System.out.print("B_");
                }
                else {
                    System.out.print("W_");
                }

                switch (piece.getType()) {
                    case PAWN:
                        System.out.print("pawn");
                        break;
                    case KING:
                        System.out.print("king");
                        break;
                    case QUEEN:
                        System.out.print("queen");
                        break;
                    case BISHOP:
                        System.out.print("bishop");
                        break;
                    case ROOK:
                        System.out.print("rook");
                        break;
                    case KNIGHT:
                        System.out.print("knight");
                        break;
                }

                System.out.print("\t");

            }
            System.out.println();
        }
    }
}
