package model;

public enum Pos {
    A_8(1, 1), B_8(2, 1), C_8(3, 1), D_8(4, 1), E_8(5, 1), F_8(6, 1), G_8(7, 1), H_8(8, 1),
    A_7(1, 2), B_7(2, 2), C_7(3, 2), D_7(4, 2), E_7(5, 2), F_7(6, 2), G_7(7, 2), H_7(8, 2),
    A_6(1, 3), B_6(2, 3), C_6(3, 3), D_6(4, 3), E_6(5, 3), F_6(6, 3), G_6(7, 3), H_6(8, 3),
    A_5(1, 4), B_5(2, 4), C_5(3, 4), D_5(4, 4), E_5(5, 4), F_5(6, 4), G_5(7, 4), H_5(8, 4),
    A_4(1, 5), B_4(2, 5), C_4(3, 5), D_4(4, 5), E_4(5, 5), F_4(6, 5), G_4(7, 5), H_4(8, 5),
    A_3(1, 6), B_3(2, 6), C_3(3, 6), D_3(4, 6), E_3(5, 6), F_3(6, 6), G_3(7, 6), H_3(8, 6),
    A_2(1, 7), B_2(2, 7), C_2(3, 7), D_2(4, 7), E_2(5, 7), F_2(6, 7), G_2(7, 7), H_2(8, 7),
    A_1(1, 8), B_1(2, 8), C_1(3, 8), D_1(4, 8), E_1(5, 8), F_1(6, 8), G_1(7, 8), H_1(8, 8);

    public final int col;
    public final int row;

    public static final Pos[] ALL = values();

    Pos(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int[] toArray() {
        return new int[]{col-1, row-1};
    }

    public static Pos get(int col, int row) {
        for (Pos pos : ALL) {
            if (pos.col == col && pos.row == row) {
                return pos;
            }
        }
        return null;
    }
}
