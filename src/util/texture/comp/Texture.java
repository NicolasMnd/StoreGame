package util.texture.comp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Flyweight pattern applied here.
 */
public class Texture extends TextureHolder {

    private final BufferedImage image;

    public Texture(File location) {
        this.image = readImage(location);
    }

    BufferedImage readImage(File location) {
        BufferedImage image1;
        try {
            image1 = ImageIO.read(location);
        } catch(IOException e) {
            System.out.println("Reading image with location " + location + " failed.");
            image1 = null;
        }
        return image1;
    }

    //@Override
    public BufferedImage getImage() {
        return this.image;
    }

}
