public class Position {
    public Position(int r, int c) {
        if(!setRow(r) || !setCol(c)) {
            throw new RuntimeException("Position out of bounds");
        }
    }

    public boolean setRow(int r) {
        if(r < 0 || r >= 8) {
            return false;
        }
        this.r = r;
        return true;
    }

    public int getRow() {
        return r;
    }

    public boolean setCol(int c) {
        if(c < 0 || c >= 8) {
            return false;
        }
        this.c = c;
        return true;
    }

    public int getCol() {
        return c;
    }

    private int r;
    private int c;
}
