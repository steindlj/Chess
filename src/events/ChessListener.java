package events;

import java.util.EventListener;

@FunctionalInterface
public interface ChessListener extends EventListener {
    void pieceMoved(ChessEvent evt);
}