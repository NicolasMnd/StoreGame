package game.tile;

import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

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

    @Override
    public RenderStage renderStage(RenderStageSelector selector) {
        return selector.getRenderStage(this);
    }

}
