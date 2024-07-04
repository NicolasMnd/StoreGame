package util.texture.comp;

import util.Direction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DirectedTexture extends TextureHolder {

    private Map<Direction, Texture> textures;

    public DirectedTexture() {
        super();
        this.textures = new HashMap<>();
    }

    /**
     * Adds a {@link Texture} for a given {@link Direction} object
     * @param dir the {@link Direction}
     * @param texture the {@link Texture}
     */
    public void addTexture(Direction dir, Texture texture) {
        textures.put(dir, texture);
    }

    /**
     * Adds a {@link Texture} for a given {@link Direction} object
     * @param dir the {@link Direction}
     * @param file the path to the {@link Texture}
     */
    public void addTexture(Direction dir, File file) {
        textures.put(dir, new Texture(file));
    }


    /**
     * Get a {@link Texture} for a given {@link Direction} key
     * @param key the {@link Direction} of which we want the {@link Texture}
     * @return a {@link Texture} object.
     */
    public Texture getTexture(Direction key) {
        return textures.get(key);
    }

}
