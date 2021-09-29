package projet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;


public class Main {

    public static final int FRAME_WIDTH = 1168;
    public static final int FRAME_HEIGHT = 800;
    public static final int MENU_HEIGHT = 500;
    public static final int MENU_WIDTH = 200;

    private JFrame frame;
    private JMenuBar menuBar;

    private JMenuItem closeMenuItem, helpMenuItem;
    private Animator animator;

    public Main() {
        createView();
    }

    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createView() {
        frame = new JFrame("3iL - Graphic interfaces & 3D - TP2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        this.createHorizontalMenu();
        this.createVerticalMenu();

        // Set the OpenGL zone :
        GLCanvas canvas = new GLCanvas();
        
        canvas.addGLEventListener(new Cube());
        canvas.requestFocus();
        frame.add(canvas, BorderLayout.CENTER);

        // Set an animator manager :
        animator = new Animator(canvas);
        animator.start();

    }

    private void createHorizontalMenu() {
        menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("File");
        closeMenuItem = new JMenuItem("Close");
        menuFichier.add(closeMenuItem);
        menuBar.add(menuFichier);

        JMenu menuEdition = new JMenu("About");
        helpMenuItem = new JMenuItem("Help");
        menuEdition.add(helpMenuItem);
        menuBar.add(menuEdition);

        closeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "This is the TP2 about OpenGL.");
            }
        });

        frame.setJMenuBar(menuBar);
    }

    private void createVerticalMenu() {
        JPanel lateralMenu = new JPanel();
        lateralMenu.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        lateralMenu.setBorder(BorderFactory.createEtchedBorder());
        frame.add(lateralMenu, BorderLayout.EAST);

        // Set buttons in lateral menu :
        JButton button1 = new JButton("Start/Stop rotation");
        lateralMenu.add(button1);

        // Set listeners on lateral menu buttons :
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (animator.isStarted()) {
                    animator.stop();
                } else {
                    animator.start();
                }
            }
        });
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
