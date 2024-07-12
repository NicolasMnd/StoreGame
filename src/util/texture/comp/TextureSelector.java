package util.texture.comp;

import game.item.ItemTomato;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import util.texture.textureinformation.ITextureStrategy;

public class TextureSelector {

    public ITextureStrategy selectTexture(TileGround ground) {
        return () -> ((Texture) ground.getTexture()).getImage();
    }

    public ITextureStrategy selectTexture(TileWall wall) {
        return () -> ((Texture) wall.getTexture()).getImage();
    }

    public ITextureStrategy selectTexture(TileShelf shelf) {
        return new ShelfTextureInformation(shelf);
    }

    public ITextureStrategy selectTexture(ItemTomato tomato) {
        return () -> null;
    }

}
