package game;

import game.tile.GameTile;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
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

    @Override
    public RenderStage renderStage(RenderStageSelector selector) {
        return null;
    }

}
