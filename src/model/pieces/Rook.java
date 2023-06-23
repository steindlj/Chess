package model.pieces;

import model.Pos;

public class Rook extends AbstractPiece{
    public Rook(Pos pos, String symbol, String color) {
        super("Rook", pos, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        return from[0] == to[0] || from[1] == to[1];
    }
}
