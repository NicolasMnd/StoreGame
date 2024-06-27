package game.map;

import game.tile.GameTile;
import util.Dimension;
import util.Direction;
import util.Rotator;

public class MapRotator {

    private final Dimension dimension;
    private final Rotator<GameTile> rotator = new Rotator<GameTile>(GameTile.class);

    public MapRotator(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Rotates the tiles correctly
     * @param tiles the game tiles
     * @param dir the direction for rotation
     * @return a rotated {@link GameTile} double array
     */
    public GameTile[][] rotate(GameTile[][] tiles, Direction dir) {

        switch(dir) {

            case LEFT:
                return rotator.rotateLeft(tiles, dimension);

            case RIGHT:
                return rotator.rotateRight(tiles, dimension);

        }

        return null;

    }



}
