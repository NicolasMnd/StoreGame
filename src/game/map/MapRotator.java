package game.map;

import game.tile.GameTile;
import util.Dimension;
import util.Direction;
import util.Rotator;
import util.positions.Pos;
import util.positions.MatrixHelper;
import util.texture.comp.TextureSelector;

import java.util.function.Function;

/**
 * This class is responsible for rotating the map itself, a {@link GameTile}[][] object.
 * Not only does this mean simply transforming a matrix, but also updating the {@link Direction} of a {@link GameTile}, this may eventually lead to texture changes...
 * Note that rotating means rotating the map itself. When drawn on a paper, rotating left means turning the page counterclockwise. Not viewing the page from the left side.
 */
public class MapRotator {

    private final Dimension dimension;
    private final int tileSize;
    private final Rotator<GameTile> rotator = new Rotator<>(GameTile.class);

    public MapRotator(Dimension dimension, int tileSize) {
        this.dimension = dimension;
        this.tileSize = tileSize;
    }

    /**
     * Rotates the tiles correctly
     * @param tiles the game tiles
     * @param dir the direction for rotation
     * @return a rotated {@link GameTile} double array
     */
    public GameTile[][] rotate(GameTile[][] tiles, Direction dir) {

        //System.out.println("Rotating the map " + dir + " with dimension [" + tiles.length + ", " + tiles[0].length + "]");

        switch(dir) {

            case LEFT:
                tiles = rotator.rotateLeft(tiles, dimension);
                updateFacings(tiles, Direction::turnLeft, tileSize);
                //System.out.println("After rotation the dimensions of the map are [" + tiles.length + ", " + tiles[0].length + "]");
                return tiles;

            case RIGHT:
                tiles = rotator.rotateRight(tiles, dimension);
                updateFacings(tiles, Direction::turnRight, tileSize);
                //System.out.println("After rotation the dimensions of the map are [" + tiles.length + ", " + tiles[0].length + "]");
                return tiles;

        }

        return null;

    }

    public Pos calculatePlayerPosition(Pos original, Direction dir) {
        Rotator<Integer> rotator = new Rotator<>(Integer.class);
        MatrixHelper helper = new MatrixHelper(original, dimension, tileSize);
        Pos ret = original;

        if(dir == Direction.LEFT) {
            //print(rotator.rotateLeft(helper.getPlayerPositionMatrix(), dimension));
            ret = helper.getRotatedPosition(rotator.rotateLeft(helper.getPlayerPositionMatrix(), dimension));
        }

        if(dir == Direction.RIGHT) {
            ret = helper.getRotatedPosition(rotator.rotateRight(helper.getPlayerPositionMatrix(), dimension));
        }

        return ret;
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

    private void print(Integer[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
