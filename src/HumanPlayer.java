import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {

    @Override
    public Move getNextMove(Board board, Display display) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter source pos: ");
        String sourcePos = scanner.nextLine();

        int sourceRow = sourcePos.charAt(0) - 'a';
        int sourceCol = sourcePos.charAt(1) - '1';


        for(Move move: getValidMoves(board, sourceRow, sourceCol)) {
            display.displayMove(move);
            System.out.print("\t");
        }


        System.out.println("Enter dest pos: ");
        String destPos = scanner.nextLine();

        int destRow = destPos.charAt(0) - 'a';
        int destCol = destPos.charAt(1) - '1';

        return new Move(sourceRow, sourceCol, destRow, destCol);
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
