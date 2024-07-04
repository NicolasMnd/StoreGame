package game.tile;

import game.property.PropertyPeer;
import util.Direction;
import util.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureHolder;
import util.texture.textureinformation.ShelfTextureInformation;

import java.awt.image.BufferedImage;

public class TileShelf extends GameTile {

    public TileShelf(Pos pos) {
        super(pos);
        this.getProperties().addProperty(new PropertyPeer(this));
    }

    @Override
    public TextureHolder loadTexture(TextureLoader textureLoader) {
        return textureLoader.loadTexture(this);
    }

    @Override
    public BufferedImage selectTexture(Direction viewDirection) {
        return new ShelfTextureInformation(this).retrieveTexture(viewDirection, getTexture());
    }

}
