package game.map;

import game.GameObject;
import game.GameObjectBuilder;
import game.tile.GameTile;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import listeners.IContainerInteraction;
import listeners.IContainerNotifier;
import listeners.IGameSizeListener;
import util.Direction;
import util.positions.Pos;
import util.texture.comp.Texture;
import util.texture.comp.TextureHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * The reason for this clas is to apply the flyweight pattern. All possible textures will be loaded in once. All objects that use that texture will refer to the {@link Texture} objects here.
 */
class TileReader {

    private final IContainerNotifier containerNotifier;
    private final IGameSizeListener gameSizeListener;
    private Map<String, TextureHolder> textureRegistry;

    public TileReader(IContainerNotifier containerNotifier, IGameSizeListener gameSizeListener) {
        this.containerNotifier = containerNotifier;
        this.gameSizeListener = gameSizeListener;
        this.textureRegistry = new HashMap<>();
    }

    /**
     * Retrieves the {@link GameTile} given a code in the game map
     * @param code the code in the game map
     * @return a {@link GameTile} object corresponding to the code
     */
    public GameTile getTileFor(String code, Pos pos) {

        return switch (ripId(code).toLowerCase()) {
            case "shelf" -> buildTile(code, new TileShelf(pos, notifyContainer(ripContainerCode(code))));
            case "ground" -> buildTile(code, new TileGround(pos));
            case "wall" -> buildTile(code, new TileWall(pos));
            default -> null;
        };

    }

    /**
     * Contains the necessary logic to create a tile, while preserving memory. The {@link TileReader#textureRegistry} contains only one entry of each texture.
     * @param code the rest of the code.
     * @param o the type of the {@link GameTile}
     * @return a {@link GameTile}
     */
    public GameTile buildTile(String code, GameObject o) {
        GameObjectBuilder s = new GameObjectBuilder(o);

        if(textureRegistry.containsKey(o.getClass().getName()))
            s.addTexture(textureRegistry.get(o.getClass().getName()));
        else
            this.textureRegistry.put(o.getClass().getName(), o.getTexture());

        s.setFacing(ripOrientation(code));
        s.setGameSizeListener(this.gameSizeListener);

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
     * @param code the code in the file
     * @return the orientation
     */
    private Direction ripOrientation(String code) {
        String direction = getValue(code, "or", "no_orientation");
        return switch (direction.toUpperCase()) {
            case "S" -> Direction.SOUTH;
            case "W" -> Direction.WEST;
            case "E" -> Direction.EAST;
            default -> Direction.NORTH;
        };
    }

    private String ripContainerCode(String code) {
        return getValue(code, "cc", "nocontainer");
    }

    /**
     * Notifies a container
     * @param containerCode the code of the tile
     * @return a {@link IContainerNotifier} that allows the receiver of the value to interact with the {@link game.container.Container}
     */
    private IContainerInteraction notifyContainer(String containerCode) {
        return this.containerNotifier.notifyContainer(containerCode);
    }

    String getValue(String code, String key, String def) {
        String[] codeParts = code.split("_");
        for(int i = 0; i < codeParts.length; i++)
            if(codeParts[i].split(":")[0].equalsIgnoreCase(key))
                return codeParts[i].split(":")[1];
        return def;
    }

}
