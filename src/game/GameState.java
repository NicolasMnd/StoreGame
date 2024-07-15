package game;

import game.container.Container;
import game.entity.Entity;
import game.entity.EntityLoader;
import game.entity.PlayerEntity;
import game.map.MapHandler;
import game.tile.GameTile;
import listeners.IMoveValidity;
import util.*;

public class GameState {

    private PlayerEntity player;
    private Pos cameraPosition;
    private GameTile[][] tiles;
    private Container[] container;
    private final int tileSize;
    private final Dimension windowSize;
    private final Logger performanceLogger = new Logger("Check collision");
    //private Entity[] entities;

    public GameState(int tileSize, Dimension windowsSize) {
        this.tileSize = tileSize;
        this.windowSize = windowsSize;
        this.cameraPosition = new Pos(3*tileSize, 3*tileSize);
        init();
    }

    public void move(Direction d) {
        switch(d) {
            case Direction.UP -> cameraPosition = cameraPosition.add(new Pos(0, -4));
            case Direction.DOWN -> cameraPosition = cameraPosition.add(new Pos(0, 4));
            case Direction.RIGHT -> cameraPosition = cameraPosition.add(new Pos(4, 0));
            case Direction.LEFT -> cameraPosition = cameraPosition.add(new Pos(-4, 0));
        }
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

    /**
     * Returns a listener of {@link Entity} objects to check if they are colliding with a {@link GameTile#getHitbox()}
     * Performance by looping through all tiles:    0.1ms  - 2ms   overhead
     *                checking only relevant tiles: 0.01ms - 0.1ms overhead
     *                checking relevants with try/catch:
     *                                              0.01ms - 0.5ms overhead
     * @return listener for determining collision with {@link util.hitbox.Hitbox}
     */
    private IMoveValidity setupMoveChecker() {
        return new IMoveValidity() {
            @Override
            public boolean canMoveTo(Pos pos) {
                OperationTime time = new OperationTime("check move validity");
                time.start();
                int gridLocationX = Math.floorDiv(pos.x(), 32);
                int gridLocationY = Math.floorDiv(pos.y(), 32);
                // check for all tiles
                for(int x = -4; x < 5; x++)
                    for(int y = -4; y < 5; y++) {
                        if(gridLocationY+y < 0 || gridLocationY+y > tiles.length || gridLocationX+x < 0 || gridLocationX+x > tiles[0].length)
                            continue;
                        GameTile selection = tiles[gridLocationY+y][gridLocationX+x];
                        if(selection.getHitbox().isInHitbox(pos) && !selection.canCollide())
                            return false;
                    }

                time.stop();
                performanceLogger.time(time.getNano());
                return true;
            }
        };
    }

}
