public class Pawn extends Piece{
    public Pawn(color playerColor) {
        super(playerColor);
    }

    @Override
    public color getColor() {
        return null;
    }

    @Override
    public type getType() {
        return TYPE;
    }

    private static final type TYPE = type.PAWN;
}
