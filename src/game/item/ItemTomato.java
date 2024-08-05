package game.item;

import game.entity.Entity;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class ItemTomato extends GameItem {

    Entity holder;

    public ItemTomato() {
        super("tomato");
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return () -> null;
    }

    @Override
    public ITextureStrategy textureSelector(TextureSelector selector) {
        return selector.selectTexture(this);
    }

    @Override
    public RenderStage renderStage(RenderStageSelector selector) {
        return null;
    }

}
