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
        char row = ' ';
        char column = ' ';
        switch (this.spot[0]) {
            case 0:
                row = '8';
                break;
            case 1:
                row = '7';
                break;
            case 2:
                row = '6';
                break;
            case 3:
                row = '5';
                break;
            case 4:
                row = '4';
                break;
            case 5:
                row = '3';
                break;
            case 6:
                row = '2';
                break;
            case 7:
                row = '1';
                break;
        }
        switch (this.spot[1]) {
            case 0:
                column = '1';
                break;
            case 1:
                column = 'b';
                break;
            case 2:
                column = 'c';
                break;
            case 3:
                column = 'd';
                break;
            case 4:
                column = 'e';
                break;
            case 5:
                column = 'f';
                break;
            case 6:
                column = 'g';
                break;
            case 7:
                column = 'h';
                break;
        }
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
            distance =  to[1]-from[1];
            if (Math.abs(distance) == 1) return false;
            if (distance < 0) {
                distance = Math.abs(distance)-1;
                x = from[1]-1;
                for (int i = 0; i < distance; i++, x--) {
                    if (Game.btnsPiece[from[0]][x].hasPiece()) {
                        break;
                    }
                }
                if (Game.btnsPiece[from[0]][x].hasPiece()) return true;
                else return false;
            } else if (distance > 0) {
                distance = Math.abs(distance)-1;
                x = from[1]+1;
                for (int i = 0; i < distance; i++, x++) {
                    if (Game.btnsPiece[from[0]][x].hasPiece()) {
                        break;
                    }
                }
                if (Game.btnsPiece[from[0]][x].hasPiece()) return true;
            }
        } else if (from[1] == to[1]) {
            distance =  to[0]-from[0];
            if (Math.abs(distance) == 1) return false;
            if (distance < 0) {
                distance = Math.abs(distance)-1;
                y = from[0]-1;
                for (int i = 0; i < distance; i++, y--) {
                    if (Game.btnsPiece[y][from[1]].hasPiece()) {
                        break;
                    } 
                }
                if (Game.btnsPiece[y][from[1]].hasPiece()) return true;
                else return false;
            } else if (distance > 0) {
                distance = Math.abs(distance)-1;
                y = from[0]+1;
                for (int i = 0; i < distance; i++, y++) {
                    if (Game.btnsPiece[y][from[1]].hasPiece()) {
                        break;
                    } 
                }
                if (Game.btnsPiece[y][from[1]].hasPiece()) return true;
                else return false;
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
                    for (int i = 0; i < distance; i++, x--, y--) {
                        if (Game.btnsPiece[y][x].hasPiece()) {
                            break;
                        }
                    }
                    if (Game.btnsPiece[y+1][x+1].hasPiece()) return true;
                    else return false;
                } else if (x > 0) {
                    x = from[1]+1;
                    for (int i = 0; i < distance; i++, x++, y--) {
                        if (Game.btnsPiece[y][x].hasPiece()) {
                            break;
                        }
                    }
                    if (Game.btnsPiece[y+1][x-1].hasPiece()) return true;
                    else return false;
                }
            } else if (y > 0) {
                y = from[0]+1;
                if (x < 0) {
                    x = from[1]-1;
                    for (int i = 0; i < distance; i++, x--, y++) {
                        if (Game.btnsPiece[y][x].hasPiece()) {
                            break;
                        }
                    }
                    if (Game.btnsPiece[y-1][x+1].hasPiece()) return true;
                    else return false;
                } else if (x > 0) {
                    x = from[1]+1;
                    for (int i = 0; i < distance; i++, x++, y++) {
                        if (Game.btnsPiece[y][x].hasPiece()) {
                            break;
                        }
                    }
                    if (Game.btnsPiece[y-1][x-1].hasPiece()) return true;
                    else return false;
                } 
            }
        }
        return false;
    }
}