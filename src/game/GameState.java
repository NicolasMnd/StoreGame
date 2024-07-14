package game;

import game.container.Container;
import game.entity.Entity;
import game.entity.EntityLoader;
import game.entity.PlayerEntity;
import game.map.MapHandler;
import game.tile.GameTile;
import listeners.IMoveValidity;
import util.Dimension;
import util.Direction;
import util.OperationTime;
import util.Pos;

public class GameState {

    private PlayerEntity player;
    private Pos cameraPosition;
    private GameTile[][] tiles;
    private Container[] container;
    private final int tileSize;
    private final Dimension windowSize;
    //private Entity[] entities;

    public GameState(int tileSize, Dimension windowsSize) {
        this.tileSize = tileSize;
        this.windowSize = windowsSize;
        this.cameraPosition = new Pos(3*tileSize, 3*tileSize);
        init();
    }

    public void move(Direction d) {
        System.out.println("Previous: " + cameraPosition.getFormat());
        switch(d) {
            case Direction.UP -> cameraPosition = cameraPosition.add(new Pos(0, -4));
            case Direction.DOWN -> cameraPosition = cameraPosition.add(new Pos(0, 4));
            case Direction.RIGHT -> cameraPosition = cameraPosition.add(new Pos(4, 0));
            case Direction.LEFT -> cameraPosition = cameraPosition.add(new Pos(-4, 0));
        }
        System.out.println("Result: " + cameraPosition.getFormat());
    }

    /**
     * @return the player {@link game.entity.Entity}
     */
    public Entity getPlayer() {
        return this.player;
    }

    /**
     * Determines the player position
     */
    public Pos getCameraPosition() {
        return this.player.getPosition();
    }

    /**
     * Starts up the game.
     */
    public void init() {
        // 1. Load map
        this.loadMap("resources/map/map.csv", tileSize);

        // 2. Load entities & player
        this.loadEntities();
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
        EntityLoader entityLoader = new EntityLoader(setupMoveChecker());
        this.player = new PlayerEntity(this.cameraPosition, setupMoveChecker());
    }

    //TODO optimisation...
    private IMoveValidity setupMoveChecker() {
        return new IMoveValidity() {
            @Override
            public boolean canMoveTo(Pos pos) {
                OperationTime time = new OperationTime("check move validity");
                time.start();

                // check for all tiles
                for(GameTile[] tileArr : tiles)
                    for(GameTile tile : tileArr)
                        if(tile.getHitbox().isInHitbox(pos) && !tile.canCollide())
                            return false;

                time.stop();
                //time.verslag("Checking position validity");
                return true;
            }
        };
    }

}
