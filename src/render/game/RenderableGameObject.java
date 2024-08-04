package render.game;

import game.GameObject;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.IRender;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

/**
 * A {@link GameObject} which {@link Pos} is set to a relative position for the camera.
 * It decorates the {@link GameObject}
 */
public class RenderableGameObject extends GameObject {
    
    private final GameObject parent;
    final Pos renderPosition;
    private int tileSize = 0;

    public RenderableGameObject(GameObject parent, Pos renderPosition) {
        super(parent.getPosition());
        this.parent = parent;
        this.renderPosition = renderPosition;
        setTexture(parent.getTexture());
        this.setHeight(parent.getHeight());
        this.setWidth(parent.getWidth());
    }

    public RenderableGameObject(int tileSize, Pos renderPosition) {
        super(null);
        this.tileSize = tileSize;
        this.parent = null;
        this.renderPosition = renderPosition;
        setTexture(textureLoader(new TextureLoader()).loadTexture());
        this.setHeight(32);
        this.setWidth(32);
    }

    GameObject parent() {
        return this.parent;
    }

    @Override
    public Pos getPosition() {
        return this.renderPosition;
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        if(parent == null) return () -> null;
        return parent.textureLoader(textureLoader);
    }

    @Override
    public ITextureStrategy textureSelector(TextureSelector selector) {
        if(parent == null) return () -> null;/*((Texture) (Texture.getBackground(tileSize))).getImage();*/
        return parent.textureSelector(selector);
    }

    @Override
    public IRender getRenderStrategy(GameObject object) {
        if(this.getTexture() == null)
            return new RenderStrategy().rectangleRenderer(this);
        else
            return parent.getRenderStrategy(this);
    }

    /**
     * @return returns an integer
     */
    public int getRenderStage() {
        return 0;
    }

}