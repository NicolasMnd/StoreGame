package game;

import game.tile.GameTile;
import util.texture.TextureLoader;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class TestTile extends GameTile {

    public TestTile() {
        super(null);
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return null;
    }

    @Override
    public ITextureStrategy selectTexture() {
        return null;
    }

}
