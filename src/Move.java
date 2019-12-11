public class Move {
    public Move(int sourceRow, int sourceCol, int destRow, int destCol) {
        this.sourceRow = sourceRow;
        this.sourceCol = sourceCol;
        this.destRow = destRow;
        this.destCol = destCol;
    }

    @Override
    public boolean equals(Object obj) {
        Move other = (Move) obj;
        return sourceRow == other.sourceRow && sourceCol == other.sourceCol && destRow == other.destRow && destCol == other.destCol;
    }

    public int sourceRow;
    public int sourceCol;
    public int destRow;
    public int destCol;
}
