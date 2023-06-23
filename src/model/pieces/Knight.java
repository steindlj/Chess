package model.pieces;

import model.Pos;

public class Knight extends AbstractPiece{
    public Knight(Pos pos, String symbol, String color) {
        super("Knight", pos, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        int y = to[0]-from[0];
        int x = to[1]-from[1];
        if (Math.abs(y) == 1) {
            return Math.abs(x) == 2;
        } else if (Math.abs(y) == 2) {
            return Math.abs(x) == 1;
        } 
        return false;
    }
}
