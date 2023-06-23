package ui;

import javax.swing.*;

import model.Game;
import java.awt.*;

public class ChessUI {
    private JFrame frame;
    private Game game;
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 16);

    public ChessUI() {
        frame = new JFrame("Chess");
        createGUI();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void createGUI() {
        game = new Game();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        setUpContent();
        frame.add(new ChessBoardPanel(game), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void setUpContent() {
        JLabel turnLabel = new JLabel("Turn: " + game.getState());
        game.addListener(evt -> turnLabel.setText("Turn: " + game.getState()));
        turnLabel.setFont(TEXT_FONT);
        JPanel menu = new JPanel(new BorderLayout());
        menu.add(turnLabel);
        JTextArea logText = new JTextArea("Logs:");
        logText.setEditable(false);
        logText.setFont(TEXT_FONT);
        logText.setBackground(new Color(193, 193, 193));
        game.addListener(evt -> logText.setText(logText.getText() + game.getLogText()));
        JScrollPane logScrollScreen = new JScrollPane(logText);
        logScrollScreen.setPreferredSize(new Dimension(160, 700));
        logScrollScreen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        menu.add(logScrollScreen, BorderLayout.PAGE_START);
        JButton restartBtn = new JButton();
        restartBtn.addActionListener(ae -> {
            frame.getContentPane().removeAll();
            createGUI();
        });
        JLabel label = new JLabel("RESTART");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(ChessUI.TEXT_FONT);
        restartBtn.setPreferredSize(new Dimension(160, 30));
        restartBtn.add(label);
        menu.add(restartBtn, BorderLayout.PAGE_END);
        frame.add(menu, BorderLayout.LINE_END);
    }
}
