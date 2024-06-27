package game.map;

import game.tile.GameTile;
import game.tile.TileShelf;
import util.ObjectLinker;

import java.util.function.Predicate;

public class TileLinker extends ObjectLinker<GameTile> {

    public TileLinker(GameTile[][] matrix) {
        super(
                matrix,

                (tile) -> tile instanceof TileShelf,

                (parent, newPeer) -> {
                    parent.addPeer(newPeer);
                    newPeer.addPeer(newPeer);
                    return true;
                },

                (tile1, tile2) -> tile1.getPeers().contains(tile2),

                (tile1, tile2) -> tile1.getPosition().operation.isConnected1D(tile2.getPosition(), 4) && tile1.getFacing().equals(tile2.getFacing())

        );

    }

    @Override
    public int getLinkRowAmount() {
        return 4;
    }

}
