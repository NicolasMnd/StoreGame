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
        loadMap("map.csv");
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
