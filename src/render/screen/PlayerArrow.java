package render.screen;

import game.ScreenObject;
import game.entity.PlayerEntity;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStageSelector;
import util.positions.Pos;
import util.texture.TextureLoader;
import util.texture.comp.TextureSelector;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.ITextureStrategy;

public class PlayerArrow extends ScreenObject {

    public PlayerArrow(PlayerEntity player) {
        super(player.getPosition().add(new Pos(0, -32)));
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

}
