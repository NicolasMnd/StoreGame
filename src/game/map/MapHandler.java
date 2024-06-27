package game.map;

import game.tile.GameTile;
import util.Dimension;
import util.Direction;
import util.Function;
import util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;

public class MapHandler {

    private final MapReader reader;

    public MapHandler() {
        this.reader = new MapReader(getMapDimensions());
    }

    /**
     * Reads a map on disk & transforms it to a {@link GameTile} matrix
     * @param tag the string name of the map on disk
     * @return a {@link GameTile} matrix
     */
    public GameTile[][] readMap(String tag) {
        readLines(reader);
        return reader.getTiles();
    }

    public GameTile[] rotate(GameTile[][] map, Direction dir) {
        return null;
    }

    /**
     * Reads through the map once and retrieves the dimensions of the map
     * @return gives a {@link Pair} object containing width height of the map
     */
    private Dimension getMapDimensions() {
        int[] amountLines = {0};
        int[] maxColumns = {0};

        Function<String> count = (string) -> {
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
    private void readLines(Function<String> f) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/map.csv"));

            while(reader.ready())
                f.execute(reader.readLine());

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

}
