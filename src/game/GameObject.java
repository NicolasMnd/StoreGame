package game;

import game.property.PropertyManager;
import util.Direction;
import util.Pos;
import util.hitbox.Hitbox;
import util.texture.TextureLoader;
import util.texture.comp.Texture;
import util.texture.comp.TextureHolder;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public abstract class GameObject {

    private Pos pos;
    private TextureHolder texture;
    private Direction facing;
    private final PropertyManager properties;
    private int width, height;

    public GameObject(Pos pos) {
        this.pos = pos;
        this.texture = textureLoader(new TextureLoader()).loadTexture();
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
     * Returns the height of this object
     * @return the height of this object
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the width of this object
     * @return the width of this object
     */
    public int getWidth() {
        return this.width;
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
    public abstract ITextureLoader textureLoader(TextureLoader textureLoader);

    /**
     * Sets the {@link TextureHolder} to the parameter. Used when applying the flyweight pattern in {@link game.map.TileReader}
     * @param texture the texture for the {@link GameObject}
     */
    void setTexture(TextureHolder texture) {
        this.texture = texture;
    }

    /**
     * Retrieves the texture for this object, which is set by either {@link GameObject#textureLoader(TextureLoader)} or {@link GameObject#setTexture(TextureHolder)}
     * @return the {@link TextureHolder} object
     */
    public TextureHolder getTexture() {
        return this.texture;
    }

    /**
     * The method that selects textures dynamically based upon the rotation of the map.
     * @return hello
     */
    public abstract ITextureStrategy textureSelector();

    /**
     * Sets the width of this game object
     * @param width the width of the object
     */
    protected void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of this game object
     * @param height the height of the object
     */
    protected void setHeight(int height) {
        this.height = height;
    }

    /**
     * returns the {@link Hitbox} of this object
     * @return the {@link Hitbox} object
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.pos, (Pos) this.pos.add(new Pos(width, height)));
    }

}
