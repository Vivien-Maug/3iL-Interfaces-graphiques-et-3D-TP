package projet.Class;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;


public class BackgroundImage {

    private Image img;
    private String name;
    private int posX, posY; 

    public BackgroundImage(String imagePath, String imageName){
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = imageName;
        posX = 0;
        posY = 0;
    }

    public Image getImage(){
        return img;
    }

    @Override
    public String toString() {
        return name;
    }
}
