public class Pawn extends Piece {
    public Pawn(color playerColor) {
        super(playerColor);
    }

    @Override
    public type getType() {
        return type.PAWN;
    }
}
