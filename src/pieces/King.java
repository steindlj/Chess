package pieces;

import game.Game;

public class King extends AbstractPiece{
    public King(int[] spot, String symbol, String color) {
        super("King", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.willJump(from, to) || (Game.btnsPiece[to[0]][to[1]].hasPiece() && super.getColor().equals(Game.btnsPiece[to[0]][to[1]].piece.getColor()))) return false;
        if ((Math.abs(to[0]-from[0]) == 1 && Math.abs(to[1]-from[1]) == 0) || (Math.abs(to[0]-from[0]) == 0 && Math.abs(to[1]-from[1]) == 1)) {
            return true;
        } else {
            return false;
        }
    }
}
