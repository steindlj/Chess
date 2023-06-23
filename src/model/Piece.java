package model;

public enum Piece {
    ROOK_WHITE("Rook", "\u2656", "white") {
        @Override
        public boolean canMove(int[] from, int[] to) {
        return from[0] == to[0] || from[1] == to[1];
        }
    },
    ROOK_BLACK("Rook", "\u265C", "black") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return from[0] == to[0] || from[1] == to[1];
        }
    },
    QUEEN_WHITE("Queen", "\u2655", "white") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return from[0] == to[0] || from[1] == to[1] || Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
        }
    },
    QUEEN_BLACK("Queen", "\u265B", "black") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return from[0] == to[0] || from[1] == to[1] || Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
        }
    },
    PAWN_WHITE("Pawn", "\u2659", "white") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            if (from[1] == to[1]) {
                if (to[0]-from[0] > -3 && to[0]-from[0] < 0) {
                    return (to[0]-from[0] == -2 && from[0] == 6 || to[0]-from[0] == -1) && !Game.board.containsKey(Pos.get(to[0]+1, to[1]+1));
                } 
            } else if (Math.abs(to[1]-from[1]) == 1 && Game.board.containsKey(Pos.get(to[0]+1, to[1]+1))) {
                return to[0]-from[0] == -1;
            } 
            return false;
        }
    },
    PAWN_BLACK("Pawn", "\u265F", "black") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            if (from[1] == to[1]) {
                if (to[0]-from[0] < 3 && to[0]-from[0] > 0) {
                    return (to[0]-from[0] == 2 && from[0] == 1 || to[0]-from[0] == 1) && !Game.board.containsKey(Pos.get(to[0]+1, to[1]+1));
                }
            } else if (Math.abs(to[1]-from[1]) == 1 && Game.board.containsKey(Pos.get(to[0]+1, to[1]+1))) {
                return to[0]-from[0] == 1;
            } 
            return false;
        }
    },
    KNIGHT_WHITE("Knight", "\u2658", "white") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            int x = to[0]-from[0];
            int y = to[1]-from[1];
            return Math.abs(y) == 1 && Math.abs(x) == 2 || Math.abs(y) == 2 && Math.abs(x) == 1;
        }
    },
    KNIGHT_BLACK("Knight", "\u265E", "black") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            int x = to[0]-from[0];
            int y = to[1]-from[1];
            return Math.abs(y) == 1 && Math.abs(x) == 2 || Math.abs(y) == 2 && Math.abs(x) == 1;
        }
    },
    KING_WHITE("King", "\u2654", "white") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return Math.abs(to[0]-from[0]) <= 1 && Math.abs(to[1]-from[1]) <= 1;
        }
    },
    KING_BLACK("King", "\u265A", "black") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return Math.abs(to[0]-from[0]) <= 1 && Math.abs(to[1]-from[1]) <= 1;
        }
    },
    BISHOP_WHITE("Bishop", "\u2657", "white") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
        }
    },
    BISHOP_BLACK("Bishop", "\u265D", "black") {
        @Override
        public boolean canMove(int[] from, int[] to) {
            return Math.abs(to[0]-from[0]) == Math.abs(to[1]-from[1]);
        }
    };

    private final String name;
    private final String symbol;
    private final String color;

    Piece(String name, String symbol, String color) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMove(int[] from, int[] to);

    public String getAbbreviation() {
        return String.valueOf(name.charAt(0)) + String.valueOf(color.charAt(0));
    }

}