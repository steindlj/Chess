package events;

import java.util.EventObject;
import model.*;
import model.pieces.AbstractPiece;

public class ChessEvent extends EventObject{
    private final Pos from;
    private final Pos to;
    private final AbstractPiece piece;

    public ChessEvent(Game game, Pos from, Pos to, AbstractPiece piece) {
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

    public AbstractPiece getPiece() {
        return piece;
    }
}
