package game.map;

import game.tile.GameTile;
import util.Dimension;
import util.Direction;
import util.Rotator;
import util.positions.Pos;
import util.texture.comp.TextureSelector;

import java.util.function.Function;

public class MapRotator {

    private final Dimension dimension;
    private final Rotator<GameTile> rotator = new Rotator<>(GameTile.class);

    public MapRotator(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Rotates the tiles correctly
     * @param tiles the game tiles
     * @param dir the direction for rotation
     * @return a rotated {@link GameTile} double array
     */
    public GameTile[][] rotate(GameTile[][] tiles, Direction dir, int tileSize) {

        switch(dir) {

            case LEFT:
                tiles = rotator.rotateLeft(tiles, dimension);
                updateFacings(tiles, Direction::turnLeft, tileSize);
                return tiles;

            case RIGHT:
                tiles = rotator.rotateRight(tiles, dimension);
                updateFacings(tiles, Direction::turnRight, tileSize);
                return tiles;

        }

        return null;

    }

    private void updateFacings(GameTile[][] tiles, Function<Direction, Direction> dirUpdater, int tileSize) {

        for(int i = 0; i < tiles.length; i++ )
            for(int j = 0; j < tiles[i].length; j++) {
                GameTile tile = tiles[i][j];
                tile.setFacing(dirUpdater.apply(tile.getFacing()));
                tile.updatePosition(new Pos(j*tileSize, i*tileSize));
                tile.textureSelector(new TextureSelector());
            }

    }

}
