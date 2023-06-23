package model.pieces;

import model.Pos;

public class Queen extends AbstractPiece{
    public Queen(Pos pos, String symbol, String color) {
        super("Queen", pos, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        return from[0] == to[0] || from[1] == to[1] || Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
    }
}
