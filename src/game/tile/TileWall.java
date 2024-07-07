package game.tile;

import util.Pos;
import util.texture.TextureLoader;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class TileWall extends GameTile {

    public TileWall(Pos pos) {
        super(pos);
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return null;
    }

    @Override
    public ITextureStrategy textureSelector() {
        return null;
    }

}
