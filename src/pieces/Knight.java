package pieces;

public class Knight extends AbstractPiece{
    public Knight(int[] spot, String symbol, String color) {
        super("Knight", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        int y = to[0]-from[0];
        int x = to[1]-from[1];
        if (Math.abs(y) == 1) {
            if (Math.abs(x) == 2) {
                return true;
            } else {
                return false;
            }
        } else if (Math.abs(y) == 2) {
            if (Math.abs(x) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
