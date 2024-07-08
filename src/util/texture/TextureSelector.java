package util.texture;

import game.item.ItemTomato;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import util.texture.comp.Texture;
import util.texture.textureinformation.ITextureStrategy;
import util.texture.textureinformation.ShelfTextureInformation;

import java.awt.image.BufferedImage;

public class TextureSelector {

    public ITextureStrategy selectTexture(TileGround ground) {
        return () -> ((Texture) ground.getTexture()).getImage();
    }

    public ITextureStrategy selectTexture(TileWall wall) {
        return () -> null;
    }

    public ITextureStrategy selectTexture(TileShelf shelf) {
        return new ShelfTextureInformation(shelf);
    }

    public ITextureStrategy selectTexture(ItemTomato tomato) {
        return () -> null;
    }

}
