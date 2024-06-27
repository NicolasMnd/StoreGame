package game.map;

import util.texture.Texture;
import game.tile.GameTile;
import game.tile.TileGround;
import game.tile.TileWall;
import listeners.IContainerNotifier;
import util.Direction;
import util.Pos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The reason for this clas is to apply the flyweight pattern. All possible textures will be loaded in once. All objects that use that texture will refer to the {@link Texture} objects here.
 */
public class TileReader {

    private final Map<String, Texture> textures;
    private List<IContainerNotifier> containerNotifiers = new ArrayList<>();

    public TileReader(IContainerNotifier containerNotifier) {
        this.containerNotifiers.add(containerNotifier);
        this.textures = loadTextures();
    }

    public GameTile getTileFor(String code) {
        return null;
    }

    /**
     * Handles the creation of a ground tile
     */
    public GameTile handleGround(Pos pos) {
        return new TileGround(pos, textures.get("floor"));
    }

    public GameTile handleWall(Pos pos) {
        return new TileWall(pos, textures.get("wall"));
    }

    /**
     * Loads all the textures in the specified file
     * @return a list of {@link Texture} objects
     */
    private Map<String, Texture> loadTextures() {

        Map<String, Texture> textures = new HashMap<>();
        File directory = new File("resources/tiles");
        File[] allTextures = directory.listFiles();

        for(File textureLocation : allTextures) {
            textures.put(
                    ripId(textureLocation.getName()),
                    new Texture(textureLocation)
            );
        }

        return textures;
    }

    /**
     * Will get the first part of the string. Separated by _
     * @param name the name of the file
     * @return the first part of the name
     */
    private String ripId(String name) {
        return name.split("_")[0];
    }

    /**
     * Returns the orientation of the tile
     * @param name the name of the file
     * @return the orientation
     */
    private Direction ripOrientation(String name) {
        return switch (name.split("_")[1].toUpperCase()) {
            case "S" -> Direction.SOUTH;
            case "W" -> Direction.WEST;
            case "E" -> Direction.EAST;
            case "U" -> Direction.UP;
            case "D" -> Direction.DOWN;
            case "R" -> Direction.RIGHT;
            case "L" -> Direction.LEFT;
            default -> Direction.NORTH;
        };
    }

}
