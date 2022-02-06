package game;

import javax.swing.*;
import pieces.*;
import java.awt.*;

public class Game {
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font BUTTON_FONT = new Font("Arial Unicode MS", Font.PLAIN, 65);
    public static final Color LIGHT_COLOR = new Color(213, 168, 119);
    public static final Color DARK_COLOR = new Color(147, 73, 25);
    public static final Color MOVE_COLOR = new Color(173, 251, 133);

    public static AbstractPiece movingPiece = null;
    public static int[] from = null;
    public static int[] to = null;
    public static Button[][] btnsPiece;
    public static String Player;
    public static AbstractPiece[] pieces; 
    public static JFrame frame = new JFrame("Chess");
    public static JTextArea logText;
    public static JLabel turnLabel;
    public static void main(String[] args) {
        createBoardGUI(frame);
    }

    static void createBoardGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Player = "white";
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8)); 
        turnLabel = new JLabel("Turn: " + Player);
        turnLabel.setFont(TEXT_FONT);
        RestartButton restart = new RestartButton();
        JPanel Menu = new JPanel(new BorderLayout());
        logText = new JTextArea("Logs:");
        logText.setEditable(false);
        logText.setFont(TEXT_FONT);
        logText.setBackground(new Color(193, 193, 193));
        JScrollPane logScrollScreen = new JScrollPane(logText);
        logScrollScreen.setPreferredSize(new Dimension(160, 700));
        logScrollScreen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Menu.add(logScrollScreen, BorderLayout.PAGE_START);
        Menu.add(restart, BorderLayout.PAGE_END);
        btnsPiece = new Button[8][8];
        pieces = new AbstractPiece[32];
        Menu.add(turnLabel);
        frame.add(panel, BorderLayout.LINE_START);
        frame.add(Menu, BorderLayout.LINE_END);
        setPieces(pieces);
        setBtns(btnsPiece, pieces, panel);
        frame.pack();
        frame.setVisible(true);
    }

    static void setBtns(Button[][] btns, AbstractPiece[] pieces, JPanel panel) {
        Color bg = LIGHT_COLOR;
        for (int i = 0, y = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 1 || i == 6 || i == 7) {
                    Button btn = new Button(pieces[y], new int[]{i,j}, bg);
                    btns[i][j] = btn;
                    y++;
                } else {
                    Button btn = new Button(new int[]{i,j}, bg);
                    btns[i][j] = btn;
                }
                panel.add(btns[i][j]);
                bg = bg == LIGHT_COLOR ? DARK_COLOR : LIGHT_COLOR;
            }
            bg = bg == LIGHT_COLOR ? DARK_COLOR : LIGHT_COLOR;
        }
    }

    static void setPieces(AbstractPiece[] pieces) {
        pieces[0] = new Rook(new int[]{0,0}, "\u265C", "black");
        pieces[1] = new Knight(new int[]{0,1}, "\u265E", "black");
        pieces[2] = new Bishop(new int[]{0,2}, "\u265D", "black");
        pieces[3] = new Queen(new int[]{0,3}, "\u265B", "black");
        pieces[4] = new King(new int[]{0,4}, "\u265A", "black");
        pieces[5] = new Bishop(new int[]{0,5}, "\u265D", "black");
        pieces[6] = new Knight(new int[]{0,6}, "\u265E", "black");
        pieces[7] = new Rook(new int[]{0,7}, "\u265C", "black");
        for (int i = 8, j = 0; i < 16; i++, j++) {
            pieces[i] = new Pawn(new int[]{1,j}, "\u265F", "black");
        }
        for (int i = 16, j = 0; i < 24; i++, j++) {
            pieces[i] = new Pawn(new int[]{6,j}, "\u2659", "white");
        }
        pieces[24] = new Rook(new int[]{7,0}, "\u2656", "white");
        pieces[25] = new Knight(new int[]{7,1}, "\u2658", "white");
        pieces[26] = new Bishop(new int[]{7,2}, "\u2657", "white");
        pieces[27] = new Queen(new int[]{7,3}, "\u2655", "white");
        pieces[28] = new King(new int[]{7,4}, "\u2654", "white");
        pieces[29] = new Bishop(new int[]{7,5}, "\u2657", "white");
        pieces[30] = new Knight(new int[]{7,6}, "\u2658", "white");
        pieces[31] = new Rook(new int[]{7,7}, "\u2656", "white");
    }
}
