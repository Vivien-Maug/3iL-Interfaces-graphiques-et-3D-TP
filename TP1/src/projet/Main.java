package projet;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Main {

    // CONSTANTES
    public static final int FRAME_WIDTH = 1168;
    public static final int FRAME_HEIGHT = 800;
    public static final int MENU_HEIGHT = 500;
    public static final int MENU_WIDTH = 200;

    // ATTRIBUTS
    private JFrame frame; // La fenêtre
    private JMenuBar menuBar; // Menu en haut horizontal
    private Image imgFond; // Image de fond du graph


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

        JPanel menu1 = new JPanel();
        menu1.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menu1.setBorder(BorderFactory.createEtchedBorder());
        
        frame.add(menu1, BorderLayout.EAST);

        menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        // menuFichier.add(fermer);
        menuBar.add(menuFichier);

        JMenu menuEdition = new JMenu("A propos");
        // menuEdition.add(aide);
        menuBar.add(menuEdition);
        
        
        frame.setJMenuBar(menuBar);

        try {
            imgFond = ImageIO.read(new File("TP1/images/Fond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JPanel panelImage = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgFond, 0, 0, this); // see javadoc for more info on the parameters            
            }
        };
        frame.add(panelImage);
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
