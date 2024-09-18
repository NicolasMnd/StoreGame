package game;

import listeners.IGameSizeListener;
import render.View;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import render.game.renderorder.RenderStrategy;
import util.positions.Hitbox;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureHolder;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.IRender;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public abstract class ScreenObject {

    private Pos position;
    protected int width, height;
    private TextureHolder texture;
    private IGameSizeListener gameSizeListener;

    public ScreenObject(Pos position) {
        this.position = position;
        this.texture = textureLoader(new TextureLoader()).loadTexture();
        this.gameSizeListener = () -> 1d;
    }

    private ScreenObject(Pos p, int width, int height, TextureHolder holder) {
        this.position = p;
        this.width = width;
        this.height = height;
        this.texture = holder;
    }

    /**
     * Allows subclasses to implement update position behaviour.
     *
     * @param pos the new position
     */
    public void updatePosition(Pos pos) {
        this.position = pos;
    }

    /**
     * Returns a clone of the position of this object
     * @return the position in a {@link Pos} object
     */
    public Pos getPosition() {
        return this.position.clone();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public IRender getRenderStrategy(ScreenObject screenObject) {
        return new RenderStrategy().imageRenderer(screenObject);
    }

    /**
     * returns the {@link Hitbox} of this object
     * @return the {@link Hitbox} object
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.getPosition(), this.getPosition().add(new Pos(width, height)));
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

    /**
     * Sets a listener for {@link View#getGameSize()}. This is necessary because hitboxes & sizes need to be updated when the size increases
     * @param gameSizeListener the listener that retrieves {@link View#getGameSize()}
     */
    void setGameSizeListener(IGameSizeListener gameSizeListener) {
        this.gameSizeListener = gameSizeListener;
    }

    public abstract RenderStage renderStage(RenderStageSelector selector);

    /**
     * Retrieves the texture for this object, which is set by either {@link GameObject#textureLoader(TextureLoader)} or {@link GameObject#setTexture(TextureHolder)}
     * @return the {@link TextureHolder} object
     */
    public TextureHolder getTexture() {
        return this.texture;
    }

}
