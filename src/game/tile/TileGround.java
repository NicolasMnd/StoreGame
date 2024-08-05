package game.tile;

import render.game.RenderStage;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class TileGround extends GameTile {

    public TileGround(Pos pos) {
        super(pos);
        setRenderOrder(RenderStage.BACKGROUND);
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
