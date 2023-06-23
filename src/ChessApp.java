import javax.swing.SwingUtilities;

import ui.ChessUI;

public class ChessApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessUI::new);
    }
}
