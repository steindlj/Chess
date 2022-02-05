package pieces;

import game.Game;

public class Pawn extends AbstractPiece{
    public Pawn(int[] spot, String symbol, String color) {
        super("Pawn", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.willJump(from, to) || (Game.btnsPiece[to[0]][to[1]].hasPiece() && super.getColor().equals(Game.btnsPiece[to[0]][to[1]].piece.getColor()))) return false;
        if (from[1] == to[1]) {
            if (super.getColor().equals("white") && to[0]-from[0] > -3 && to[0]-from[0] < 0) {
                if(to[0]-from[0] == -2 && from[0] == 6) {
                    return true;
                } else if (to[0]-from[0] == -1 && !Game.btnsPiece[to[0]][to[1]].hasPiece()) {
                    return true;
                } else {
                    return false;
                }
            } else if (super.getColor().equals("black") && to[0]-from[0] < 3 && to[0]-from[0] > 0) {
                if(to[0]-from[0] == 2 && from[0] == 1) {
                    return true;
                } else if (to[0]-from[0] == 1 && !Game.btnsPiece[to[0]][to[1]].hasPiece()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (Math.abs(to[1]-from[1]) == 1 && Game.btnsPiece[to[0]][to[1]].hasPiece()) {
            if (super.getColor().equals("white") && to[0]-from[0] == -1) {
                return true;
            } else if (super.getColor().equals("black") && to[0]-from[0] == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
