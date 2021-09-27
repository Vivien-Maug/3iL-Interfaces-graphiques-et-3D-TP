package projet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projet.Class.BackgroundImage;
import projet.Class.ImageFilter;
import projet.Class.AppMouseListener;

public class Main {

    public static final int FRAME_WIDTH = 1168;
    public static final int FRAME_HEIGHT = 800;
    public static final int MENU_HEIGHT = 500;
    public static final int MENU_WIDTH = 200;

    private JFrame frame;
    private AppMouseListener mouseListener;
    private JMenuBar menuBar;
    private ArrayList<BackgroundImage> imagesList;

    private JMenuItem closeMenuItem, helpMenuItem;
    private JButton addImageBtn, removeImageBtn;

    public Main() {
        createView();
    }

    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createView() {
        frame = new JFrame("TP1 - application Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        this.createHorizontalMenu();
        this.createVerticalMenu();

        imagesList = new ArrayList<BackgroundImage>();
        imagesList.add(new BackgroundImage("images/Fond.png", "Fond.png"));
        imagesList.get(0).setPos(0, 0);

        JPanel panelImage = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (BackgroundImage bgImage : imagesList) {
                    g.drawImage(bgImage.getImage(), bgImage.getPos().x, bgImage.getPos().y, this);
                }
            }
        };
        frame.add(panelImage);

        mouseListener = new AppMouseListener(imagesList, panelImage);
        frame.addMouseListener(mouseListener);
        frame.addMouseMotionListener(mouseListener);

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
                System.exit(0);
            }
        });

        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Contact :\n" + "Vivien : sample@email.fr");
            }
        });

        frame.setJMenuBar(menuBar);
    }

    private void createVerticalMenu() {
        JPanel menu1 = new JPanel();
        menu1.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menu1.setBorder(BorderFactory.createEtchedBorder());
        frame.add(menu1, BorderLayout.EAST);

        addImageBtn = new JButton("Add an image");
        menu1.add(addImageBtn);
        removeImageBtn = new JButton("Delete an image");
        menu1.add(removeImageBtn);

        addImageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new ImageFilter());
                fileChooser.setAcceptAllFileFilterUsed(false);

                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    String fileName = fileChooser.getSelectedFile().getName();
                    imagesList.add(new BackgroundImage(filePath, fileName));
                    frame.repaint();
                    removeImageBtn.setEnabled(true);
                }
            }
        });
        removeImageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (imagesList.size() > 0) {
                    ArrayList<String> choices = new ArrayList<String>();

                    for (BackgroundImage img : imagesList) {
                        int index = choices.size();
                        choices.add(index + " - " + img.toString());
                    }

                    String input = (String) JOptionPane.showInputDialog(null, "Choose the image to delete",
                            "The Choice of the image to be deleted", JOptionPane.QUESTION_MESSAGE, null,
                            choices.toArray(), // Array of choices
                            choices.get(0)); // Initial choice
                    int index = choices.indexOf(input);
                    if (index != -1) {
                        imagesList.remove(index);
                        frame.repaint();
                        if (imagesList.size() == 0) {
                            removeImageBtn.setEnabled(false);
                        }
                    }
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
