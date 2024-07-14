package game.tile;

import game.property.PropertyContainer;
import game.property.PropertyPeer;
import listeners.IContainerInteraction;
import util.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class TileShelf extends GameTile {

    public TileShelf(Pos pos, IContainerInteraction containerNotifier) {
        super(pos);
        this.getProperties().addProperty(new PropertyPeer(this));
        this.getProperties().addProperty(new PropertyContainer(this, containerNotifier));
        setHeight(128);
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
