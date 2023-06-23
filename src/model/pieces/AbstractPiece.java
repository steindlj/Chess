package model.pieces;

import model.Pos;

abstract public class AbstractPiece{
    private final String name;
    private Pos pos;
    private final String symbol;
    private final String color;

    AbstractPiece(String name, Pos pos, String symbol, String color) {
        this.name = name;
        this.pos = pos;
        this.symbol = symbol;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public Pos getPos() {
        return this.pos;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getColor() {
        return this.color;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public String getAbbreviation() {
        return String.valueOf(this.getName().charAt(0)) + String.valueOf(this.getColor().charAt(0));
    }

    abstract public boolean canMove(int[] from, int[] to);
}
