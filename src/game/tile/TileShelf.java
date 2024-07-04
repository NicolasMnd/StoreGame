package game.tile;

import game.property.PropertyPeer;
import util.Pos;
import util.texture.TextureLoader;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;
import util.texture.textureinformation.ShelfTextureInformation;

public class TileShelf extends GameTile {

    public TileShelf(Pos pos) {
        super(pos);
        this.getProperties().addProperty(new PropertyPeer(this));
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return textureLoader.loadTexture(this);
    }

    @Override
    public ITextureStrategy selectTexture() {
        return new ShelfTextureInformation(this);
    }

}
