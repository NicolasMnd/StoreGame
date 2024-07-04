package game;

import game.tile.GameTile;
import util.Direction;
import util.texture.TextureLoader;
import util.texture.comp.TextureHolder;

import java.awt.image.BufferedImage;

public class TestTile extends GameTile {

    public TestTile() {
        super(null);
    }

    @Override
    public TextureHolder loadTexture(TextureLoader textureLoader) {
        return null;
    }

    @Override
    public BufferedImage selectTexture(Direction viewDirection) {
        return null;
    }
}
