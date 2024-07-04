package util.texture.comp;

import java.util.HashMap;
import java.util.Map;

/**
 * A multi texture consists of multiple textures for one tile:
 * example: shelf: LEFT MID* RIGHT
 * Other types of texture depending on a certain state: dirty or clean can also be supported.
 */
public class MultiTexture extends TextureHolder {

    /**
     * A map consisting of each part / variant of the texture.
     */
    private Map<String, TextureHolder> variants;

    public MultiTexture() {
        super();
        this.variants = new HashMap<>();
    }

    /**
     * Get an {@link TextureHolder} for a given string key
     * @param key the key of the multi-texture
     * @return a {@link TextureHolder}
     */
    public TextureHolder getTexture(String key) {
        return variants.get(key);
    }

    /**
     * Puts a {@link Texture} for a given key
     * @param key the name for that texture
     * @param texture the {@link TextureHolder} object
     */
    public void addTexture(String key, TextureHolder texture) {
        this.variants.put(key, texture);
    }

}
