package model;

public enum Pos {
    A_8(1, 1), B_8(1, 2), C_8(1, 3), D_8(1, 4), E_8(1, 5), F_8(1, 6), G_8(1, 7), H_8(1, 8),
    A_7(2, 1), B_7(2, 2), C_7(2, 3), D_7(2, 4), E_7(2, 5), F_7(2, 6), G_7(2, 7), H_7(2, 8),
    A_6(3, 1), B_6(3, 2), C_6(3, 3), D_6(3, 4), E_6(3, 5), F_6(3, 6), G_6(3, 7), H_6(3, 8),
    A_5(4, 1), B_5(4, 2), C_5(4, 3), D_5(4, 4), E_5(4, 5), F_5(4, 6), G_5(4, 7), H_5(4, 8),
    A_4(5, 1), B_4(5, 2), C_4(5, 3), D_4(5, 4), E_4(5, 5), F_4(5, 6), G_4(5, 7), H_4(5, 8),
    A_3(6, 1), B_3(6, 2), C_3(6, 3), D_3(6, 4), E_3(6, 5), F_3(6, 6), G_3(6, 7), H_3(6, 8),
    A_2(7, 1), B_2(7, 2), C_2(7, 3), D_2(7, 4), E_2(7, 5), F_2(7, 6), G_2(7, 7), H_2(7, 8),
    A_1(8, 1), B_1(8, 2), C_1(8, 3), D_1(8, 4), E_1(8, 5), F_1(8, 6), G_1(8, 7), H_1(8, 8);

    private final int row;
    private final int col;

    public static final Pos[] ALL = values();

    Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int[] toArray() {
        return new int[]{row-1, col-1};
    }

    public static Pos get(int row, int col) {
        for (Pos pos : ALL) {
            if (pos.row == row && pos.col == col) {
                return pos;
            }
        }
        return null;
    }
}
