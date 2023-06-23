package model.pieces;

import model.Game;
import model.Pos;

public class Pawn extends AbstractPiece{
    public Pawn(Pos pos, String symbol, String color) {
        super("Pawn", pos, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (from[1] == to[1]) {
            if (super.getColor().equals("white") && to[0]-from[0] > -3 && to[0]-from[0] < 0) {
                return to[0]-from[0] == -2 && from[0] == 6 || to[0]-from[0] == -1 && !Game.board.containsKey(Pos.get(to[0], to[1]));
            } else if (super.getColor().equals("black") && to[0]-from[0] < 3 && to[0]-from[0] > 0) {
                return to[0]-from[0] == 2 && from[0] == 1 || to[0]-from[0] == 1 && !Game.board.containsKey(Pos.get(to[0], to[1]));
            }
        } else if (Math.abs(to[1]-from[1]) == 1 && !Game.board.containsKey(Pos.get(to[0], to[1]))) {
            return super.getColor().equals("white") && to[0]-from[0] == -1 || super.getColor().equals("black") && to[0]-from[0] == 1;
        } 
        return false;
    }
}
