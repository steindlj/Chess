package model.pieces;

import model.Pos;

public class Bishop extends AbstractPiece{
    public Bishop(Pos pos, String symbol, String color) {
        super("Bishop", pos, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        return Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
    }
}
