package util.texture;

import game.tile.TileShelf;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ShelfTextureInformation;

/**
 * Instances of {@link game.GameObject} will call upon {@link game.GameObject#textureLoader(TextureLoader)} and double dispatch here.
 *
 * This class brings all texture loading together. It makes sure that the texture information is stored.
 */
public class TextureLoader {

    public ITextureLoader loadTexture(TileShelf shelf) {
        return new ShelfTextureInformation(shelf);
    }

}
