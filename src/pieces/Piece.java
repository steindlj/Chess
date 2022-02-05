package pieces;

public interface Piece {
    String getName();
    int[] getSpot();
    String getSymbol();
    String getColor();
    boolean canMove(int[] from, int[] to);
    boolean willJump(int[] from, int[] to);
    String getChessSpot();
    String getAbbreviation();
}
