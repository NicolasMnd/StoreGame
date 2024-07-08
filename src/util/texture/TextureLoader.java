package util.texture;

import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import util.texture.comp.Texture;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ShelfTextureInformation;

import java.io.File;

/**
 * Instances of {@link game.GameObject} will call upon {@link game.GameObject#textureLoader(TextureLoader)} and double dispatch here.
 *
 * This class brings all texture loading together. It makes sure that the texture information is stored.
 */
public class TextureLoader {

    public ITextureLoader loadTexture(TileShelf shelf) {
        return new ShelfTextureInformation(shelf);
    }

    public ITextureLoader loadTexture(TileWall wall) {
        return () -> {
            return null;
        };
    }

    public ITextureLoader loadTexture(TileGround ground) {
        return () -> new Texture(new File("resources/tiles/floor.png"));
    }

}
