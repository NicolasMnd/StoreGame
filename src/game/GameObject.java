package game;

import game.property.PropertyManager;
import util.Direction;
import util.Pos;
import util.texture.comp.Texture;
import util.texture.TextureLoader;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;

public abstract class GameObject {

    private Pos pos;
    private TextureHolder texture;
    private Direction facing;
    private final PropertyManager properties;

    public GameObject(Pos pos) {
        this.pos = pos;
        this.texture = loadTexture(new TextureLoader());
        this.facing = Direction.NORTH;
        this.properties = new PropertyManager();
    }

    /**
     * Allows subclasses to implement update position behaviour.
     *
     * @param pos the new position
     */
    public void updatePosition(Pos pos) {
        this.pos = pos;
    }

    /**
     * Returns a clone of the position of this object
     * @return the position in a {@link Pos} object
     */
    public Pos getPosition() {
        return this.pos.clone();
    }

    /**
     * Returns the {@link Direction} where this object is currently facing towards
     * @return the {@link Direction}
     */
    public Direction getFacing() {
        return  this.facing;
    }

    /**
     * Overwrites the facing of this object to the parameter
     * @param dir the new {@link Direction} facing.
     */
    public void setFacing(Direction dir) {
        this.facing = dir;
    }

    /**
     * Returns the {@link PropertyManager} containing all assigned {@link game.property.Property} instances
     * @return the {@link PropertyManager}
     */
    public PropertyManager getProperties() {
        return this.properties;
    }

    /**
     * Allows subclasses to call upon their own algorithm that handles their {@link Texture} retrieval
     */
    public abstract TextureHolder loadTexture(TextureLoader textureLoader);

    /**
     * Sets the {@link TextureHolder} to the parameter. Used when applying the flyweight pattern in {@link game.map.TileReader}
     * @param texture the texture for the {@link GameObject}
     */
    void setTexture(TextureHolder texture) {
        this.texture = texture;
    }

    /**
     * Retrieves the texture for this object
     * @return the {@link TextureHolder} object
     */
    protected TextureHolder getTexture() {
        return this.texture;
    }

    public abstract BufferedImage selectTexture(Direction viewDirection);

}
