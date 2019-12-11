public class King extends Piece {
    public King(color playerColor) {
        super(playerColor);
    }

    @Override
    public type getType() {
        return type.KING;
    }
}
