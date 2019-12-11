public class Knight extends Piece {
    public Knight(color playerColor) {
        super(playerColor);
    }

    @Override
    public type getType() {
        return type.KNIGHT;
    }
}
