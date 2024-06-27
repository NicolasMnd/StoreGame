package util.texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Flyweight pattern applied here.
 */
public class Texture {

    private final BufferedImage image;

    public Texture(File location) {
        BufferedImage image1;
        try {
            image1 = ImageIO.read(location);
        } catch(IOException e) {
            System.out.println("Reading image with location " + location + " failed.");
            image1 = null;
        }
        this.image = image1;
    }

    /**
     * @return the image for this texture
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Returns the appropriate texture for a given name
     * @param name the name of the file
     * @return
     */
    public static Texture getTextureFor(String name) {

        // If we have a directed texture, we will create another object which will gather all relevant textures N, S, E, W for this object.
        if(isDirectedTexture(name)) {



        }

        //TODO
        return null;
    }

    private static boolean isDirectedTexture(String name) {
        return name.split("_")[1].isEmpty();
    }

}
