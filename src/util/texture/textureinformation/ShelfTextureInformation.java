package util.texture.textureinformation;

import game.property.PropertyPeer;
import game.property.PropertyType;
import game.tile.TileShelf;
import util.Direction;
import util.texture.comp.DirectedTexture;
import util.texture.comp.MultiTexture;
import util.texture.comp.Texture;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;
import java.io.File;

import static util.Direction.*;

public class ShelfTextureInformation extends TextureInformation {

    private final TileShelf shelf;

    public ShelfTextureInformation(TileShelf shelf) {
        this.shelf = shelf;
    }

    @Override
    public TextureHolder loadTexture() {

        DirectedTexture leftSide = new DirectedTexture();
        leftSide.addTexture(NORTH, new File("resources/tiles/shelf_n_left.png"));
        leftSide.addTexture(SOUTH, new File("resources/tiles/shelf_s_left.png"));
        leftSide.addTexture(WEST, new File("resources/tiles/shelf_e_left.png"));
        leftSide.addTexture(EAST, new File("resources/tiles/shelf_e_left.png"));

        DirectedTexture rightSide = new DirectedTexture();
        rightSide.addTexture(NORTH, new File("resources/tiles/shelf_n_right.png"));
        rightSide.addTexture(SOUTH, new File("resources/tiles/shelf_s_right.png"));
        rightSide.addTexture(WEST, new File("resources/tiles/shelf_e_right.png"));
        rightSide.addTexture(EAST, new File("resources/tiles/shelf_e_right.png"));

        DirectedTexture mid = new DirectedTexture();
        mid.addTexture(NORTH, new File("resources/tiles/shelf_n_mid.png"));
        mid.addTexture(SOUTH, new File("resources/tiles/shelf_s_mid.png"));
        mid.addTexture(WEST, new File("resources/tiles/shelf_e_mid.png"));
        mid.addTexture(EAST, new File("resources/tiles/shelf_e_mid.png"));

        MultiTexture multiTexture = new MultiTexture();

        multiTexture.addTexture("right", rightSide);
        multiTexture.addTexture("mid", mid);
        multiTexture.addTexture("left", leftSide);

        return multiTexture;
    }

    @Override
    public BufferedImage retrieveTexture() {

        PropertyPeer propertyPeer = (PropertyPeer) shelf.getProperties().getProperty(PropertyType.SHELF_PEER);
        MultiTexture textuur = (MultiTexture) shelf.getTexture();

        if(propertyPeer.isLeftSide()) {
            return selectDirection(shelf.getFacing(), (DirectedTexture) textuur.getTexture("left")).getImage();
        }
        else if(propertyPeer.isMiddle()) {
            return selectDirection(shelf.getFacing(), (DirectedTexture) textuur.getTexture("mid")).getImage();
        }
        else if(propertyPeer.isRightSide()) {
            return selectDirection(shelf.getFacing(), (DirectedTexture) textuur.getTexture("right")).getImage();
        }

        return null;

    }

    private Texture selectDirection(Direction shelfDirection, DirectedTexture textuur) {
        return (Texture) textuur.getTexture(shelfDirection);
    }

}
