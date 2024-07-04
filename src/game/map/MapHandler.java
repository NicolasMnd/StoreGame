package game.map;

import game.tile.GameTile;
import util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Consumer;

/**
 * This is the interface used by {@link game.GameState} to initialise the map
 */
public class MapHandler {

    private final MapReader reader;
    private final MapRotator rotator;

    public MapHandler() {
        this.reader = new MapReader(getMapDimensions());
        this.rotator = new MapRotator(getMapDimensions());
    }

    /**
     * Reads a map on disk & transforms it to a {@link GameTile} matrix
     * @param tag the string name of the map on disk
     * @return a {@link GameTile} matrix
     */
    public GameTile[][] readMap(String tag) {
        readLines(reader); //TODO link here
        //ObjectLinker<GameTile> objectLinker = new ObjectLinker<>(reader.getTiles());
        return reader.getTiles();
    }

    /**
     * Passes a request to {@link MapRotator} which will handle the rotation of the double array
     * @param map the map we want to rotate
     * @param dir the direction of the rotation
     * @return the rotated {@link GameTile} double array
     */
    public GameTile[][] rotate(GameTile[][] map, Direction dir) {
        return this.rotator.rotate(map, dir);
    }

    /**
     * Reads through the map once and retrieves the dimensions of the map
     * @return gives a {@link Pair} object containing width height of the map
     */
    private Dimension getMapDimensions() {
        int[] amountLines = {0};
        int[] maxColumns = {0};

        Consumer<String> count = (string) -> {
            String[] split = string.split(";");
            if(maxColumns[0] < split.length)
                maxColumns[0] = split.length;
            amountLines[0]++;
        };

        readLines(count);
        return new Dimension(amountLines[0], maxColumns[0]);
    }

    /**
     * Reads a line in the map and lets a function handle any logic behind handling that line
     */
    private void readLines(Consumer<String> f) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/map.csv"));

            while(reader.ready())
                f.accept(reader.readLine());

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

}
