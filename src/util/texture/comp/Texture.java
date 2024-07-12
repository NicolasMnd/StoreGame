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
    private final String location;

    public Texture(File location) {
        this.image = readImage(location);
        this.location = location.getPath();
    }

    public Texture(BufferedImage image) {
        this.image = image;
        this.location = null;
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

    public String getLocation() {
        return this.location;
    }

    BufferedImage getImage() {
        return this.image;
    }

    public static Texture getBackground(int tileSize) {
        return new Texture(new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_3BYTE_BGR));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Texture t) {
            return location.equalsIgnoreCase(t.location);
        }
        return false;
    }
}
