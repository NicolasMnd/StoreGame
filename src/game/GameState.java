package game;

import game.entity.Entity;
import game.map.MapHandler;
import game.tile.GameTile;

public class GameState {

    private GameTile[][] tiles;
    private Entity[] entities;

    public GameState() {}

    /**
     * Starts up the game.
     */
    public void init() {
        loadMap("map/map.csv");
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
        this.tiles = new MapHandler().readMap(mapName);
    }

    private void loadEntities() {

    }

}
