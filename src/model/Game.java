package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.pieces.AbstractPiece;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;
import events.*;

public class Game {
    private State state;
    public static Map<Pos, AbstractPiece> board;
    private final List<ChessListener> listeners;

    public Game() {
        state = State.WHITE_NEXT;
        listeners = new ArrayList<>();
        board = new HashMap<>();
        initPieces();
    }

    public State getState() {
        return state;
    }

    private void fireMovePiece(AbstractPiece piece, Pos from, Pos to) {
        for (ChessListener listener : listeners) {
            listener.pieceMoved(new ChessEvent(this, from, to, piece));
        }
    }

    public void addListener(ChessListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ChessListener listener) {
        listeners.remove(listener);
    }

    public boolean hasPiece(Pos pos) {
        return board.containsKey(pos);
    }

    public AbstractPiece getPiece(Pos pos) {
        return board.getOrDefault(pos, null);
    }

    private void removePiece(Pos pos) {
        board.remove(pos);
    }

    private void setPiece(Pos pos, AbstractPiece piece) {
        board.put(pos, piece);
    }

    public boolean isTurn(String color) {
        return color.equals("black") && state == State.BLACK_NEXT || color.equals("white") && state == State.WHITE_NEXT;
    }

    private void initPieces() {
        board.put(Pos.A_8, new Rook(Pos.A_8, "\u265C", "black"));
        board.put(Pos.B_8, new Knight(Pos.B_8, "\u265E", "black"));
        board.put(Pos.C_8, new Bishop(Pos.C_8, "\u265D", "black"));
        board.put(Pos.D_8, new Queen(Pos.D_8, "\u265B", "black"));
        board.put(Pos.E_8, new King(Pos.E_8, "\u265A", "black"));
        board.put(Pos.F_8, new Bishop(Pos.F_8, "\u265D", "black"));
        board.put(Pos.G_8, new Knight(Pos.G_8, "\u265E", "black"));
        board.put(Pos.H_8, new Rook(Pos.H_8, "\u265C", "black"));
        for (int i = 1; i <= 8; i++) {
            board.put(Pos.get(i, 2), new Pawn(Pos.get(i, 2), "\u265F", "black"));
        }
        for (int i = 1; i <= 8; i++) {
            board.put(Pos.get(i, 7), new Pawn(Pos.get(i, 7), "\u2659", "white"));
        }
        board.put(Pos.A_1, new Rook(Pos.A_1, "\u2656", "white"));
        board.put(Pos.B_1, new Knight(Pos.B_1, "\u2658", "white"));
        board.put(Pos.C_1, new Bishop(Pos.C_1, "\u2657", "white"));
        board.put(Pos.D_1, new Queen(Pos.D_1, "\u2655", "white"));
        board.put(Pos.E_1, new King(Pos.E_1, "\u2654", "white"));
        board.put(Pos.F_1, new Bishop(Pos.F_1, "\u2657", "white"));
        board.put(Pos.G_1, new Knight(Pos.G_1, "\u2658", "white"));
        board.put(Pos.H_1, new Rook(Pos.H_1, "\u2656", "white"));
    }

    public void placePiece(AbstractPiece piece, Pos pos) {
        if (validateMove(piece, piece.getPos(), pos)) {
            Pos from = piece.getPos();
            removePiece(from);
            piece.setPos(pos);
            setPiece(pos, piece);
            nextTurn();
            fireMovePiece(piece, from, pos);
        }
    }

    private void nextTurn() {
        state = state == State.BLACK_NEXT ? State.WHITE_NEXT : State.BLACK_NEXT;
    }

    private boolean willJump(int[] from, int[] to) {
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
                    if (hasPiece(Pos.get(from[0], x-i))) {
                        return true;
                    }
                }
            } else if (distance > 0) {
                distance -= 1;
                x = from[1]+1;
                for (int i = 0; i < distance; i++) {
                    if (hasPiece(Pos.get(from[0], x+i))) {
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
                    if (hasPiece(Pos.get(y-i, from[1]))) {
                        return true;
                    } 
                }
            } else if (distance > 0) {
                distance = Math.abs(distance)-1;
                y = from[0]+1;
                for (int i = 0; i < distance; i++) {
                    if (hasPiece(Pos.get(y+i, from[1]))) {
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
                        if (hasPiece(Pos.get(y-i, x-i))) {
                            return true;
                        }
                    }
                } else if (x > 0) {
                    x = from[1]+1;
                    for (int i = 0; i < distance; i++) {
                        if (hasPiece(Pos.get(y-i, x+i))) {
                            return true;
                        }
                    }
                }
            } else if (y > 0) {
                y = from[0]+1;
                if (x < 0) {
                    x = from[1]-1;
                    for (int i = 0; i < distance; i++) {
                        if (hasPiece(Pos.get(y+i, x-i))) {
                            return true;
                        }
                    }
                } else if (x > 0) {
                    x = from[1]+1;
                    for (int i = 0; i < distance; i++) {
                        if (hasPiece(Pos.get(y+i, x+i))) {
                            return true;
                        }
                    }
                } 
            }
        }
        return false;
    }

    public boolean validateMove(AbstractPiece piece, Pos from, Pos to) {
        return isMovePossible(piece, from.toArray(), to.toArray()) && piece.canMove(from.toArray(), to.toArray());
    }

    private boolean isMovePossible(AbstractPiece piece, int[] from, int[] to) {
        return !willJump(from, to) || hasPiece(Pos.get(to[0], to[1])) && !piece.getColor().equals(getPiece(Pos.get(to[0], to[1])).getColor());
    }
}
