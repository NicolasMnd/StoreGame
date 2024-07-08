package game;

import game.container.Container;
import game.map.MapHandler;
import game.tile.GameTile;

public class GameState {

    private GameTile[][] tiles;
    private Container[] container;
    //private Entity[] entities;

    public GameState() {}

    /**
     * Starts up the game.
     */
    public void init() {
        loadMap("resources/map/map.csv");
    }

    /**
     * Returns the map representation in {@link GameTile} objects
     * @return the game map
     */
    public GameTile[][] getTiles() {
        return this.tiles.clone();
    }

    /**
     * Reads the map of a specific name.
     * @param mapName the name on disk
     */
    private void loadMap(String mapName) {
        MapHandler handler = new MapHandler(mapName);
        this.tiles = handler.readMap();
        this.container = handler.getContainers();
    }

    private void loadEntities() {

    }

}
