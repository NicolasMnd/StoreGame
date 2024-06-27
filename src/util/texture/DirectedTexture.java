package util.texture;

import util.Direction;

import java.awt.image.BufferedImage;
import java.io.File;

public class DirectedTexture extends Texture {

    private final Direction direction;
    private final Texture[] textures;

    public DirectedTexture(File location, Direction dir) {
        super(location);
        this.direction = dir;
        this.textures = retrieveAllTextures(location);
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }

    /**
     * Retrieves the textures of all orientations using a name.
     * @param location the location of the file
     * @return an array of textures related to the name
     */
    private Texture[] retrieveAllTextures(File location) {

        File[] texturesInParent = location.getParentFile().listFiles();

        for(File text : texturesInParent) {

            //if(text.getName().split("_"))

        }

        //TODO
        return null;

    }

}
