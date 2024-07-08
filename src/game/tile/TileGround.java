package game.tile;

import util.Pos;
import util.texture.TextureLoader;
import util.texture.TextureSelector;
import util.texture.comp.Texture;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

import java.io.File;

public class TileGround extends GameTile {

    public TileGround(Pos pos) {
        super(pos);
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return textureLoader.loadTexture(this);
    }

    @Override
    public ITextureStrategy textureSelector(TextureSelector selector) {
        return selector.selectTexture(this);
    }

}
