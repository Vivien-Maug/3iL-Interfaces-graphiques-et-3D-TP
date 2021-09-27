package projet.Class;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Point;
import javax.swing.event.MouseInputListener;

public class AppMouseListener implements MouseInputListener {

    ArrayList<BackgroundImage> imgList;
    JPanel pannelToRepaint;
    Point previousClickPoint;

    public AppMouseListener(ArrayList<BackgroundImage> imagesList, JPanel pannelToRepaint) {
        super();
        imgList = imagesList;
        this.pannelToRepaint = pannelToRepaint;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        previousClickPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        previousClickPoint = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (previousClickPoint == null) {
            previousClickPoint = new Point(e.getX(), e.getY());
            return;
        }
        boolean needRepaint = false;
        for (BackgroundImage img : imgList) {
            if (img.isClicked(e.getPoint())) {
                Point delta = new Point(e.getX() - previousClickPoint.x, e.getY() - previousClickPoint.y);
                // Point delta = new Point(previousClickPoint.x - e.getX(), previousClickPoint.y - e.getY());
                System.out.println(delta.getX() + " - " + delta.getY());

                img.setPos(img.getPos().x + delta.x, img.getPos().y + delta.y);
                needRepaint = true;
            }
        }
        previousClickPoint = new Point(e.getX(), e.getY());
        if (needRepaint) {
            pannelToRepaint.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
