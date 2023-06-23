package model.pieces;

import model.Pos;

public class King extends AbstractPiece{
    public King(Pos pos, String symbol, String color) {
        super("King", pos, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        return Math.abs(to[0]-from[0]) <= 1 && Math.abs(to[1]-from[1]) <= 1;
    }
}
