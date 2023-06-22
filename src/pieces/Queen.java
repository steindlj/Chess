package pieces;

public class Queen extends AbstractPiece{
    public Queen(int[] spot, String symbol, String color) {
        super("Queen", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.isMovePossible(from, to)) return false;
        return from[0] == to[0] || from[1] == to[1] || Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
    }
}
