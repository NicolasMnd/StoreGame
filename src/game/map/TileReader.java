package game.map;

import game.GameObject;
import game.GameObjectBuilder;
import game.tile.GameTile;
import game.tile.TileShelf;
import listeners.IContainerInteraction;
import listeners.IContainerNotifier;
import util.Direction;
import util.Pos;
import util.texture.TextureLoader;
import util.texture.comp.Texture;
import util.texture.comp.TextureHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * The reason for this clas is to apply the flyweight pattern. All possible textures will be loaded in once. All objects that use that texture will refer to the {@link Texture} objects here.
 */
public class TileReader {

    private final IContainerNotifier containerNotifier;
    private Map<String, TextureHolder> textureRegistry;

    public TileReader(IContainerNotifier containerNotifier) {
        this.containerNotifier = containerNotifier;
        this.textureRegistry = new HashMap<>();
    }

    /**
     * Retrieves the {@link GameTile} given a code in the game map
     * @param code the code in the game map
     * @return a {@link GameTile} object corresponding to the code
     */
    public GameTile getTileFor(String code, Pos pos) {

        switch(ripId(code)) {
            case "shelf":
                return buildTile(code, new TileShelf(pos, notifyContainer(ripContainerCode(code))));
        }

        return null;
    }

    /**
     * Contains the necessary logic to create
     * @param code the rest of the code.
     * @param o the type of the {@link GameTile}
     * @return a {@link GameTile}
     */
    public GameTile buildTile(String code, GameObject o) {
        GameObjectBuilder s = new GameObjectBuilder(o);

        if(textureRegistry.containsKey(o.getClass().getName()))
            s.addTexture(textureRegistry.get(o.getClass().getName()));
        else
            this.textureRegistry.put(o.getClass().getName(), o.textureLoader(new TextureLoader()).loadTexture());

        s.setFacing(ripOrientation(code));

        return (GameTile) s.getFinishedObject();

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
            default -> Direction.NORTH;
        };
    }

    private int ripContainerCode(String name) {
        return Integer.parseInt(name.split("_")[2]);
    }

    /**
     * Notifies a container
     * @param containerCode the code of the tile
     * @return a {@link IContainerNotifier} that allows the receiver of the value to interact with the {@link game.container.Container}
     */
    private IContainerInteraction notifyContainer(int containerCode) {
        return this.containerNotifier.notifyContainer(containerCode);
    }

}
