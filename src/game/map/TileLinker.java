package game.map;

import game.map.linkers.ShelfLinker;
import game.tile.GameTile;

public class TileLinker {

    private GameTile[][] map;

    public TileLinker(GameTile[][] map) {
        this.map = map;
    }

    /**
     * Will do all link operations using the {@link util.ObjectLinker} class.
     */
    public GameTile[][] getLinkedTiles() {

        return map;
    }

    private GameTile[][] linkShelves() {
        ShelfLinker shelfLinker = new ShelfLinker(map);
        shelfLinker.findRelations();
        return null;
    }

}
