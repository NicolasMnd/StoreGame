package util.texture.textureinformation;

import game.property.PropertyPeer;
import game.property.PropertyType;
import game.tile.TileShelf;
import util.Direction;
import util.texture.comp.MultiTexture;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;

public class ShelfTextureInformation extends TextureInformation {

    private final TileShelf shelf;

    public ShelfTextureInformation(TileShelf shelf) {
        this.shelf = shelf;
    }

    @Override
    public TextureHolder loadTexture() {
        /*
        DirectedTexture leftSide = new DirectedTexture();
        leftSide.addTexture(NORTH, new File("/resources/tiles/shelf_n_left.png"));
        leftSide.addTexture(SOUTH, new File("/resources/tiles/shelf_s_left.png"));
        leftSide.addTexture(WEST, new File("/resources/tiles/shelf_w_left.png"));
        leftSide.addTexture(EAST, new File("/resources/tiles/shelf_e_left.png"));

        DirectedTexture rightSide = new DirectedTexture();
        rightSide.addTexture(NORTH, new File("/resources/tiles/shelf_n_right.png"));
        rightSide.addTexture(SOUTH, new File("/resources/tiles/shelf_s_right.png"));
        rightSide.addTexture(WEST, new File("/resources/tiles/shelf_w_right.png"));
        rightSide.addTexture(EAST, new File("/resources/tiles/shelf_e_right.png"));

        DirectedTexture mid = new DirectedTexture();
        mid.addTexture(NORTH, new File("/resources/tiles/shelf_n_mid.png"));
        mid.addTexture(SOUTH, new File("/resources/tiles/shelf_s_mid.png"));
        mid.addTexture(WEST, new File("/resources/tiles/shelf_w_mid.png"));
        mid.addTexture(EAST, new File("/resources/tiles/shelf_e_mid.png"));*/

        MultiTexture multiTexture = new MultiTexture();
        /*
        multiTexture.addTexture("right", rightSide);
        multiTexture.addTexture("mid", mid);
        multiTexture.addTexture("left", leftSide);*/

        return multiTexture;
    }

    @Override
    public BufferedImage retrieveTexture(Direction viewDirection, TextureHolder holder) {

        PropertyPeer propertyPeer = (PropertyPeer) shelf.getProperties().getProperty(PropertyType.SHELF_PEER);


        return null;

    }

}
