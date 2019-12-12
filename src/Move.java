public class Move {
    public Move(Position sourcePos, Position destPos) {
        this.sourcePos = sourcePos;
        this.destPos = destPos;
    }

    @Override
    public boolean equals(Object obj) {
        Move other = (Move) obj;
        return sourcePos.equals(other.sourcePos) && destPos.equals(other.destPos);
    }

    public Position sourcePos;
    public Position destPos;
}
