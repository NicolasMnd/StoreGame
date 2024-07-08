package game.map;

import game.property.PropertyPeer;
import game.property.PropertyType;
import game.tile.GameTile;
import game.tile.TileShelf;
import util.ObjectLinker;

public class ShelfLinker extends ObjectLinker<GameTile> {

    public ShelfLinker(GameTile[][] matrix) {
        super(
                matrix,

                (tile) -> tile instanceof TileShelf,

                (parent, newPeer) -> {
                    ((PropertyPeer) parent.getProperties().getProperty(PropertyType.SHELF_PEER)).addPeer((TileShelf) newPeer);
                    return true;
                },

                (tile1, tile2) -> ((PropertyPeer) tile1.getProperties().getProperty(PropertyType.SHELF_PEER)).containsPeer(tile2),

                (tile1, tile2) -> tile1.getPosition().operation.isConnected1D(tile2.getPosition(), 4)
                        && tile1.getFacing().equals(tile2.getFacing())
                        && ((PropertyPeer) tile1.getProperties().getProperty(PropertyType.SHELF_PEER)).getPeers().size() < 4-1,

                (tile1, tile2) -> tile1.getPosition().x().equals(tile2.getPosition().x())

        );

    }

    @Override
    public int getLinkRowAmount() {
        return 4;
    }

}
