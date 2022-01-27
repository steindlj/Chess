package pieces;

public class Rook extends AbstractPiece{
    public Rook(int[] spot, String symbol, String color) {
        super("Rook", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.willJump(from, to)) return false;
        if (from[0] == to[0]) {
            return true;
        } else if (from[1] == to[1]){
            return true;
        } else {
            return false;
        }
    }
}
