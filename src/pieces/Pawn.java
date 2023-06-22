package pieces;

import game.Game;

public class Pawn extends AbstractPiece{
    public Pawn(int[] spot, String symbol, String color) {
        super("Pawn", spot, symbol, color);
    }

    @Override
    public boolean canMove(int[] from, int[] to) {
        if (super.isMovePossible(from, to)) return false;
        if (from[1] == to[1]) {
            if (super.getColor().equals("white") && to[0]-from[0] > -3 && to[0]-from[0] < 0) {
                return to[0]-from[0] == -2 && from[0] == 6 || to[0]-from[0] == -1 && !Game.btnsPiece[to[0]][to[1]].hasPiece();
            } else if (super.getColor().equals("black") && to[0]-from[0] < 3 && to[0]-from[0] > 0) {
                return to[0]-from[0] == 2 && from[0] == 1 || to[0]-from[0] == 1 && !Game.btnsPiece[to[0]][to[1]].hasPiece();
            }
        } else if (Math.abs(to[1]-from[1]) == 1 && Game.btnsPiece[to[0]][to[1]].hasPiece()) {
            return super.getColor().equals("white") && to[0]-from[0] == -1 || super.getColor().equals("black") && to[0]-from[0] == 1;
        } 
        return false;
    }
}
