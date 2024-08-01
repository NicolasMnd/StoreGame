package game.state;

import game.container.Container;
import game.entity.Entity;
import game.entity.PlayerEntity;
import game.map.MapHandler;
import game.map.MapRotator;
import game.tile.GameTile;
import listeners.IGameSizeListener;
import listeners.IMoveValidity;
import listeners.ListenerRegistrator;
import util.Dimension;
import util.Direction;
import util.Logger;
import util.OperationTime;
import util.positions.Hitbox;
import util.positions.Pos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Game State contains all main aspects of the game.
 */
public class GameState {

    PlayerEntity player;
    String mapName;
    MapHandler mapHandler;
    GameTile[][] tiles;
    Container[] containers;
    final int tileSize;
    final Dimension windowSize;
    private final Logger performanceLogger = new Logger("Check collision");
    final IGameSizeListener gameSizeListener;
    //private Entity[] entities;

    public GameState(int tileSize, Dimension windowsSize, IGameSizeListener gameSizeListener) {
        this.tileSize = tileSize;
        this.windowSize = windowsSize;
        this.gameSizeListener = gameSizeListener;
        init();
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

    public void rotateMap(Direction dir) {
        new StateInitializer().save(this);
        this.tiles = new MapRotator(this.windowSize).rotate(this.tiles, dir);
    }

    /**
     * Starts up the game by letting {@link StateSave} objects access data & setting the values in this class.
     */
    public void init() {
        new StateInitializer().load(this);
    }

    /**
     * Returns the map representation in {@link GameTile} objects
     * @return the game map
     */
    public GameTile[][] getTiles() {
        return this.tiles;
    }

    /**
     * @return a listener that is called when the game is closed
     */
    public ListenerRegistrator close() {
        GameState state = this;
        return (frame) ->
            frame.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            new StateInitializer().save(state);
                        }
                    }
            );
    }

    /**
     * Returns a listener of {@link Entity} objects to check if they are colliding with a {@link GameTile#getHitbox()}
     * Performance by looping through all tiles:    0.1ms  - 2ms   overhead
     *                checking only relevant tiles: 0.01ms - 0.1ms overhead
     *                checking relevants with try/catch:
     *                                              0.01ms - 0.5ms overhead
     * @return listener for determining collision with {@link util.positions.Hitbox}
     */
    IMoveValidity getMoveChecker() {
        return new IMoveValidity() {
            @Override
            public boolean canMoveTo(Hitbox hitbox) {
                OperationTime time = new OperationTime("check move validity");
                time.start();
                int gridLocationX = Math.floorDiv(hitbox.getCenterPos().x(), 32);
                int gridLocationY = Math.floorDiv(hitbox.getCenterPos().y(), 32);
                // check for all tiles
                for(int x = -4; x < 5; x++)
                    for(int y = -4; y < 5; y++) {
                        if(gridLocationY+y < 0 || gridLocationY+y > tiles.length || gridLocationX+x < 0 || gridLocationX+x > tiles[0].length)
                            continue;
                        GameTile selection = tiles[gridLocationY+y][gridLocationX+x];
                        if(!selection.canCollide() && selection.getHitbox().hasOverlap(hitbox)) {
                            return false;
                        }
                    }

                time.stop();
                performanceLogger.time(time.getNano());
                return true;
            }
        };
    }

}
