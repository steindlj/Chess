package events;

import java.util.EventObject;
import model.*;

public class ChessEvent extends EventObject{
    private final Pos from;
    private final Pos to;
    private final Piece piece;

    public ChessEvent(Game game, Pos from, Pos to, Piece piece) {
        super(game);
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Pos getFrom() {
        return from;
    }

    public Pos getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }
}
