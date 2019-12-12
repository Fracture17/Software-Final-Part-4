public class Position {
    public Position(int r, int c) {
        setRow(r);
        setCol(c);
    }

    private void setRow(int r) {
        this.r = r;
    }

    public int getRow() {
        return r;
    }

    private void setCol(int c) {
        this.c = c;
    }

    public int getCol() {
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        Position other = (Position) obj;
        return r == other.r && c == other.c;
    }

    private int r;
    private int c;
}
