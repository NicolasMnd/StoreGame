package game.item;

import util.texture.TextureLoader;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class ItemTomato extends GameItem {

    public ItemTomato() {
        super("tomato");
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return () -> null;
    }

    @Override
    public ITextureStrategy textureSelector() {
        return () -> null;
    }
}
