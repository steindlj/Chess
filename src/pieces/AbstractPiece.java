package pieces;

import game.Game;

abstract public class AbstractPiece implements Piece{
    String name;
    public int[] spot = new int[2];
    String symbol;
    String color;

    AbstractPiece(String name, int[] spot, String symbol, String color) {
        this.name = name;
        this.spot = spot;
        this.symbol = symbol;
        this.color = color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int[] getSpot() {
        return this.spot;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String getAbbreviation() {
        return String.valueOf(this.getName().charAt(0)) + String.valueOf(this.getColor().charAt(0));
    }

    @Override
    public String getChessSpot() {
        int row = 8-this.spot[0];
        char column = (char) (this.spot[1]+97);
        return String.valueOf(column) + String.valueOf(row);
    }

    @Override
    abstract public boolean canMove(int[] from, int[] to);

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean willJump(int[] from, int[] to) {
        int distance;
        int y = 0;
        int x = 0;
        if (from[0] == to[0]) {
            distance = to[1]-from[1];
            if (Math.abs(distance) == 1) return false;
            if (distance < 0) {
                distance = Math.abs(distance)-1;
                x = from[1]-1;
                for (int i = 0; i < distance; i++) {
                    if (Game.btnsPiece[from[0]][x-i].hasPiece()) {
                        return true;
                    }
                }
            } else if (distance > 0) {
                distance -= 1;
                x = from[1]+1;
                for (int i = 0; i < distance; i++) {
                    if (Game.btnsPiece[from[0]][x+i].hasPiece()) {
                        return true;
                    }
                }
            }
        } else if (from[1] == to[1]) {
            distance =  to[0]-from[0];
            if (Math.abs(distance) == 1) return false;
            if (distance < 0) {
                distance = Math.abs(distance)-1;
                y = from[0]-1;
                for (int i = 0; i < distance; i++) {
                    if (Game.btnsPiece[y-i][from[1]].hasPiece()) {
                        return true;
                    } 
                }
            } else if (distance > 0) {
                distance = Math.abs(distance)-1;
                y = from[0]+1;
                for (int i = 0; i < distance; i++) {
                    if (Game.btnsPiece[y+i][from[1]].hasPiece()) {
                        return true;
                    } 
                }
            }
        } else if (Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1])) {
            distance = Math.abs(to[0]-from[0])-1;
            if (Math.abs(to[0]-from[0]) == 1) return false;
            y = to[0]-from[0];
            x = to[1]-from[1];
            if (y < 0) {
                y = from[0]-1;
                if (x < 0) {
                    x = from[1]-1;
                    for (int i = 0; i < distance; i++) {
                        if (Game.btnsPiece[y-i][x-i].hasPiece()) {
                            return true;
                        }
                    }
                } else if (x > 0) {
                    x = from[1]+1;
                    for (int i = 0; i < distance; i++) {
                        if (Game.btnsPiece[y-i][x+i].hasPiece()) {
                            return true;
                        }
                    }
                }
            } else if (y > 0) {
                y = from[0]+1;
                if (x < 0) {
                    x = from[1]-1;
                    for (int i = 0; i < distance; i++) {
                        if (Game.btnsPiece[y+i][x-i].hasPiece()) {
                            return true;
                        }
                    }
                } else if (x > 0) {
                    x = from[1]+1;
                    for (int i = 0; i < distance; i++) {
                        if (Game.btnsPiece[y+i][x+i].hasPiece()) {
                            return true;
                        }
                    }
                } 
            }
        }
        return false;
    }

    public boolean isMovePossible(int[] from, int[] to) {
        return willJump(from, to) || (Game.btnsPiece[to[0]][to[1]].hasPiece() && getColor().equals(Game.btnsPiece[to[0]][to[1]].piece.getColor()));
    }
}