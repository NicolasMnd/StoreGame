package game;

import game.container.Container;
import game.map.MapHandler;
import game.tile.GameTile;
import render.Camera;
import util.Dimension;
import util.Pos;

public class GameState {

    private Camera camera;
    private GameTile[][] tiles;
    private Container[] container;
    private final int tileSize;
    private final Dimension windowSize;
    //private Entity[] entities;

    public GameState(int tileSize, Dimension windowsSize) {
        this.tileSize = tileSize;
        this.windowSize = windowsSize;
        init();
    }

    /**
     * Starts up the game.
     */
    public void init() {
        loadMap("resources/map/map.csv", tileSize);
        // First argument is a 'starting position', center of the camera
        this.camera = new Camera(new Pos(3*tileSize, 3*tileSize), windowSize.getWidth(), windowSize.getHeight(), tileSize);
    }

    /**
     * Returns the map representation in {@link GameTile} objects
     * @return the game map
     */
    public GameTile[][] getTiles() {
        return this.tiles;
    }

    /**
     * Reads the map of a specific name.
     * @param mapName the name on disk
     */
    private void loadMap(String mapName, int tileSize) {
        MapHandler handler = new MapHandler(mapName, tileSize);
        this.tiles = handler.readMap();
        this.container = handler.getContainers();
    }

    private void loadEntities() {

    }

}
