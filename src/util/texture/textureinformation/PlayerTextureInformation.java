package util.texture.textureinformation;

import game.entity.PlayerEntity;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;

public class PlayerTextureInformation extends TextureInformation {

    private final PlayerEntity player;

    public PlayerTextureInformation(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public TextureHolder loadTexture() {
        return null;
    }

    @Override
    public BufferedImage retrieveTexture() {
        return null;
    }

}
