package game.entity;

import listeners.IMoveValidity;
import util.Pos;
import util.hitbox.Hitbox;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class PlayerEntity extends Entity {

    private final int PLAYER_SPEED;

    public PlayerEntity(Pos pos, IMoveValidity listener) {
        super(pos, 4, listener);
        this.PLAYER_SPEED = 4;
    }

    @Override
    public Hitbox getHitbox() {
        Pos pos = this.getPosition().add(new Pos(0, 32));
        return new Hitbox(pos, pos.add(new Pos(getWidth(), -getHeight())));
    }

    @Override
    public ITextureLoader textureLoader(TextureLoader textureLoader) {
        return textureLoader.loadTexture(this);
    }

    @Override
    public ITextureStrategy textureSelector(TextureSelector selector) {
        return selector.selectTexture(this);
    }

}
