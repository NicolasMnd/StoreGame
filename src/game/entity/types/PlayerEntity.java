package game.entity.types;

import game.entity.util.LimbTracker;
import game.entity.util.LimbVisitor;
import listeners.IAnimationListener;
import listeners.IMoveValidity;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import util.positions.Hitbox;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class PlayerEntity extends Entity {

    public PlayerEntity(Pos pos, IMoveValidity listener, IAnimationListener animationListener) {
        super(pos, 4, listener, animationListener);
    }

    @Override
    public LimbTracker getLimbTracker() {
        return new LimbVisitor().getLimbTracker(this);
    }

    @Override
    public Hitbox getHitbox() {
        Pos pos = this.getPosition();
        return
            new Hitbox(pos, pos.add(
                    new Pos(
                        getWidth(),
                        getHeight())
            )
        );
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
    public RenderStage renderStage(RenderStageSelector selector) {
        return selector.getRenderStage(this);
    }

    @Override
    protected void updateListener() {
        super.updateListener();
    }

    @Override
    public String toString() {
        return "player";
    }
}
