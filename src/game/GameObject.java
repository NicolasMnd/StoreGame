package game;

import game.property.PropertyManager;
import listeners.IGameSizeListener;
import render.game.RenderStrategy;
import render.game.RenderableGameObject;
import render.View;
import util.Direction;
import util.positions.Pos;
import util.positions.Hitbox;
import util.texture.TextureLoader;
import util.texture.comp.TextureHolder;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.IRender;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public abstract class GameObject {

    private Pos pos;
    private TextureHolder texture;
    private Direction facing;
    private final PropertyManager properties;
    private int width, height;
    private int renderOrder = 1;
    private IGameSizeListener gameSizeListener;

    public GameObject(Pos pos) {
        this.pos = pos;
        this.texture = textureLoader(new TextureLoader()).loadTexture();
        this.properties = new PropertyManager();
        this.gameSizeListener = () -> 1d;
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
        return this.facing;
    }

    /**
     * Overwrites the facing of this object to the parameter
     * @param dir the new {@link Direction} facing.
     */
    public void setFacing(Direction dir) {
        this.facing = dir;
    }

    /**
     * Sets a listener for {@link View#getGameSize()}. This is necessary because hitboxes & sizes need to be updated when the size increases
     * @param gameSizeListener the listener that retrieves {@link View#getGameSize()}
     */
    void setGameSizeListener(IGameSizeListener gameSizeListener) {
        this.gameSizeListener = gameSizeListener;
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
     * Retrieves the texture for this object, which is set by either {@link GameObject#textureLoader(TextureLoader)} or {@link GameObject#setTexture(TextureHolder)}
     * @return the {@link TextureHolder} object
     */
    public TextureHolder getTexture() {
        return this.texture;
    }

    /**
     * returns the {@link Hitbox} of this object
     * @return the {@link Hitbox} object
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.pos, this.pos.add(new Pos(width, height)));
    }

    /**
     * Get render strategy.
     * Watch out, by passing 'this' object you will get the real map positions. The {@link RenderableGameObject} will make sure to put relative positions in respect to the player position...
     */
    public IRender getRenderStrategy(GameObject object) {
        return new RenderStrategy().imageRenderer(object);
    }

    /**
     * @return the game size double in {@link View}
     */
    public IGameSizeListener getGameSizeListener() {
        return this.gameSizeListener;
    }

    /**
     * Allows subclasses to call upon their own algorithm that handles their {@link util.texture.comp.Texture} retrieval
     */
    public abstract ITextureLoader textureLoader(TextureLoader textureLoader);

    /**
     * The method that selects textures dynamically based upon the rotation of the map
     * @return hello
     */
    public abstract ITextureStrategy textureSelector(TextureSelector selector);

    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }

    /**
     * Sets the {@link TextureHolder} to the parameter. Used when applying the flyweight pattern in TileReader
     * @param texture the texture for the {@link GameObject}
     */
    protected void setTexture(TextureHolder texture) {
        this.texture = texture;
    }

    /**
     * Sets the width of this game object
     * @param width the width of the object
     */
    protected void setWidth(int width) {
        this.width = (int) (width * gameSizeListener.getSize());
    }

    /**
     * Sets the height of this game object
     * @param height the height of the object
     */
    protected void setHeight(int height) {
        this.height = (int) (height * gameSizeListener.getSize());
    }

}
