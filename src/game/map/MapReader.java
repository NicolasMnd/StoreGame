package game.map;

import game.container.Container;
import game.tile.GameTile;
import listeners.IContainerNotifier;
import util.Dimension;
import util.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class will be called upon on each line that is read from the csv file.
 * Call happens in {@link MapHandler#readMap()}. It will split a line, then use {@link TileReader}
 * to convert a code into a {@link GameTile} and use {@link game.GameObjectBuilder} to set its {@link util.Direction}.
 */
public class MapReader implements Consumer<String> {

    private GameTile[][] tiles;
    private List<Container> containers;
    private int parseLine = 0;
    private TileReader tileReader;

    /**
     * Class that handles the interpretation of lines to {@link GameTile}
     * @param dimensions the dimensions of the map contained in a {@link Dimension} object
     */
    public MapReader(Dimension dimensions) {
        this.tiles = new GameTile[dimensions.getHeight()][dimensions.getWidth()];
        this.containers = new ArrayList<>();
        this.tileReader = new TileReader(addContainerListener());
    }

    /**
     * Is executed for each line while reading through the map.
     * @param type the string that should be parsed
     */
    @Override
    public void accept(String type) {

        String[] split = type.split(";");

        for(int i = 0; i < split.length; i++)
            tiles[parseLine][i] = tileReader.getTileFor(split[i], new Pos(i, parseLine));

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
     * Returns the constructed containers
     * @return an array of containers
     */
    public List<Container> getContainers() {
        return containers;
    }

    /**
     * Allows the {@link TileReader} to call upon this method when he encounters a {@link GameTile} that has a container
     * @return a {@link IContainerNotifier} object that is defined in this class itself and updates {@link MapReader#containers}
     */
    private IContainerNotifier addContainerListener() {
        return containerCode -> {
                Container newContainer = null;
                if(containers.stream().noneMatch(c -> c.getContainerCode().equals(containerCode))) {
                    newContainer = new Container(containerCode);
                    this.containers.add(newContainer);
                }
                else
                    newContainer = containers.stream().filter(c -> c.getContainerCode().equals(containerCode)).toList().get(0);
                return newContainer.getInteractor();
        };
    }

}
