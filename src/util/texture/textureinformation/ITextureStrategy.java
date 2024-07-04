package util.texture.textureinformation;

import util.Direction;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;

public interface ITextureStrategy {

    BufferedImage retrieveTexture(Direction viewDirection, TextureHolder textureHolder);

}
