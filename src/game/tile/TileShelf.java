package game.tile;

import game.property.PropertyContainer;
import game.property.PropertyPeer;
import listeners.IContainerInteraction;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import render.game.renderorder.RenderStrategy;
import game.ScreenObject;
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
        Pos pos = getPosition().add(new Pos(0, 0));
        return new Hitbox(pos, pos.add(new Pos(getWidth(), 2)));
    }

    @Override
    public Hitbox getOverlapHitbox() {
        Pos pos = getPosition().clone();
        return new Hitbox(
            pos.add(new Pos(0, -getHeight() + 32)),
            pos.add(new Pos(getWidth(), 0))
        );
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
    public IRender getRenderStrategy(ScreenObject object) {
        return new RenderStrategy().doubleImage(object, new TileGround(object.getPosition()));
    }


    @Override
    public RenderStage renderStage(RenderStageSelector selector) {
        return selector.getRenderStage(this);
    }

    @Override
    public String toString() {
        return "T";
    }
}
