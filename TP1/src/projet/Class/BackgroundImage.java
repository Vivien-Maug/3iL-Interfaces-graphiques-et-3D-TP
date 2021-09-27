package projet.Class;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class BackgroundImage {

    private Image img;
    private String name;
    private int posX, posY;

    public BackgroundImage(String imagePath, String imageName) {
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = imageName;
        posX = 0;
        posY = 0;
    }

    public Image getImage() {
        return img;
    }

    public boolean setPos(int x, int y) {
        posX = x;
        posY = y;
        return true;
    }

    public boolean isClicked(Point pos) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        if (posX <= pos.x && (posX + width) >= pos.x && 
            posY <= pos.y && (posY + height) >= pos.y) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
