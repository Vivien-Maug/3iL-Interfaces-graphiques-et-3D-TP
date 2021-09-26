package projet;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    private JMenuItem fermer, aide;

    
    public Main() {
    // CONSTRUCTEUR
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        JPanel menu1 = new JPanel();
        menu1.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menu1.setBorder(BorderFactory.createEtchedBorder());
        frame.add(menu1, BorderLayout.EAST);
        
        this.createHorizontalMenu();
        
        try {
            imgFond = ImageIO.read(new File("images/Fond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JPanel panelImage = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = (this.getWidth() - imgFond.getWidth(null))/2;
                int y = (this.getHeight() - imgFond.getHeight(null))/2;
                g.drawImage(imgFond, x, y, this);           
            }
        };
        frame.add(panelImage);
    }

    private void createHorizontalMenu(){
        menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("File");
        fermer = new JMenuItem("Close");
        menuFichier.add(fermer);
        menuBar.add(menuFichier);

        JMenu menuEdition = new JMenu("About");
        aide = new JMenuItem("Help");
        menuEdition.add(aide);
        menuBar.add(menuEdition);

        fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        aide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Contact :\n" + "Vivien : sample@email.fr");
            }
        });

        frame.setJMenuBar(menuBar);
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
