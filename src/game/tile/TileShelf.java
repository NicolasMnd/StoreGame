package game.tile;

import game.GameObject;
import game.property.PropertyContainer;
import game.property.PropertyPeer;
import listeners.IContainerInteraction;
import render.game.RenderStrategy;
import util.positions.Hitbox;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.IRender;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class TileShelf extends GameTile {

    public TileShelf(Pos pos, IContainerInteraction containerNotifier) {
        super(pos);
        this.getProperties().addProperty(new PropertyPeer(this));
        this.getProperties().addProperty(new PropertyContainer(this, containerNotifier));
        setHeight(128);
    }

    @Override
    public Hitbox getHitbox() {
        Pos pos = getPosition().add(new Pos(0, -96));
        return new Hitbox(pos, pos.add(new Pos(getWidth(), getHeight() - 32)));
    }

    @Override
    public boolean canCollide() {
        return false;
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return textureLoader.loadTexture(this);
    }

    @Override
    public ITextureStrategy textureSelector(TextureSelector selector) {
        return selector.selectTexture(this);
    }

    @Override
    public IRender getRenderStrategy(GameObject object) {
        return new RenderStrategy().doubleImage(object, new TileGround(object.getPosition()));
    }

}
