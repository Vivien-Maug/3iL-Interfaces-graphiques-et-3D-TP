package projet;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {

    // CONSTANTES
    public static final int FRAME_WIDTH = 1168;
    public static final int FRAME_HEIGHT = 800;

    // ATTRIBUTS
    private JFrame frame; // La fenêtre
    // CONSTRUCTEUR
    public Main() {
        createView();
    }

    // COMMANDES
    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void createView() {
        frame = new JFrame("TP1 - application Swing");
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }

    // POINT D'ENTREE
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().display();
            }
        });
    }
}
