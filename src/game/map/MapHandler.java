package game.map;

import game.container.Container;
import game.tile.GameTile;
import listeners.IGameSizeListener;
import util.Dimension;
import util.Direction;
import util.FileHelper;
import util.Pair;

import java.util.function.Consumer;

/**
 * This is the interface used by {@link game.GameState} to initialise the map
 */
public class MapHandler {

    /**
     * A specific class that implements a {@link Consumer}. It will provide a {@link GameTile}[][] and do other operations
     * like {@link listeners.IContainerNotifier}
     */
    private final MapReader reader;
    /**
     * A class that will rotate a given {@link GameTile}[][].
     */
    private final MapRotator rotator;
    private final String mapName;
    private final int tileSize;

    public MapHandler(String mapLocation, int tileSize, IGameSizeListener gameSizeListener) {
        this.reader = new MapReader(getMapDimensions(mapLocation), tileSize, gameSizeListener);
        this.rotator = new MapRotator(getMapDimensions(mapLocation));
        this.mapName = mapLocation;
        this.tileSize = tileSize;
    }

    /**
     * Reads a map on disk & transforms it to a {@link GameTile} matrix
     * @return a {@link GameTile} matrix
     */
    public GameTile[][] readMap() {
        readLines(reader, mapName);
        ShelfLinker linker = new ShelfLinker(reader.getTiles(), tileSize);
        linker.findRelations();
        return linker.getMatrix();
    }

    /**
     * Retrieves the {@link Container} list from the {@link MapReader}'s {@link MapReader#getContainers()};
     * @return the {@link Container} array
     */
    public Container[] getContainers() {
        return reader.getContainers().toArray(new Container[0]);
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
    Dimension getMapDimensions(String mapLocation) {
        int[] amountLines = {0};
        int[] maxColumns = {0};

        Consumer<String> count = (string) -> {
            String[] split = string.split(";");
            if(maxColumns[0] < split.length)
                maxColumns[0] = split.length;
            amountLines[0]++;
        };

        readLines(count, mapLocation);
        return new Dimension(amountLines[0], maxColumns[0]);
    }

    /**
     * Reads a line in the map and lets a function handle any logic behind handling that line
     */
    void readLines(Consumer<String> f, String location) {
        new FileHelper().readAndConsume(f, location);
    }

}
