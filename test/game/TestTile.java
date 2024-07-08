package game;

import game.tile.GameTile;
import util.Pos;
import util.texture.TextureLoader;
import util.texture.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class TestTile extends GameTile {

    public TestTile() {
        super(null);
    }

    public TestTile(Pos p) {
        super(p);
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return () -> null;
    }

    @Override
    public ITextureStrategy textureSelector(TextureSelector selector) {
        return () -> null;
    }

}
