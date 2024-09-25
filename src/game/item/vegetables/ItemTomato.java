package game.item.vegetables;

import game.item.GameItem;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class ItemTomato extends GameItem {

    public ItemTomato() {
        super("tomato");
    }

    @Override
    public int getYStart() {
        return -11;
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
