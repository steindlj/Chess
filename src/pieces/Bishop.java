package pieces;

import game.Game;

public class Bishop extends AbstractPiece{
    public Bishop(int[] spot, String symbol, String color) {
        super("Bishop", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.willJump(from, to) || (Game.btnsPiece[to[0]][to[1]].hasPiece() && super.getColor().equals(Game.btnsPiece[to[0]][to[1]].piece.getColor()))) return false;
        if (Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1])) {
            return true;
        } else {
            return false;
        }
    }
}
