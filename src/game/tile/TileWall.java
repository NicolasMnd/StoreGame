package game.tile;

import util.Direction;
import util.texture.TextureLoader;
import util.texture.comp.Texture;
import util.Pos;

import java.awt.image.BufferedImage;

public class TileWall extends GameTile {

    public TileWall(Pos pos) {
        super(pos);
    }

    @Override
    public Texture loadTexture(TextureLoader textureLoader) {
        return null;
    }

    @Override
    public BufferedImage selectTexture(Direction viewDirection) {
        return null;
    }

}
