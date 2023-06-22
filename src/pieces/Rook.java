package pieces;

public class Rook extends AbstractPiece{
    public Rook(int[] spot, String symbol, String color) {
        super("Rook", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.isMovePossible(from, to)) return false;
        return from[0] == to[0] || from[1] == to[1];
    }
}
