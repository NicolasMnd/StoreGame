package game.map;

import game.tile.GameTile;
import util.Dimension;
import util.Function;
import util.Pair;

public class MapReader implements Function<String> {

    private GameTile[][] tiles;
    private int parseLine = 0;

    /**
     * Class that handles the interpretation of lines to {@link GameTile}
     * @param dimensions the dimensions of the map contained in a {@link Dimension} object
     */
    public MapReader(Dimension dimensions) {
        tiles = new GameTile[dimensions.getHeight()][dimensions.getWidth()];
    }

    @Override
    public void execute(String type) {

        String[] split = type.split(";");

        for(int i = 0; i < split.length; i++)
            tiles[parseLine][i] = getTileFor(split[i]);

        parseLine++;

    }

    /**
     * Returns the constructed {@link GameTile} map
     * @return a double array of {@link GameTile}s
     */
    public GameTile[][] getTiles() {
        return this.tiles;
    }

    /**
     * Retrieves the correct {@link GameTile} instance for a given key
     * @param key the string key that maps to a {@link GameTile}
     * @return a {@link GameTile} object
     */
    private GameTile getTileFor(String key) {
        switch(key) {
            default:
                return null;
        }
    }

}
