package util.texture.comp;

import game.entity.types.PlayerEntity;
import game.item.vegetables.ItemTomato;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import render.screen.effect.player.PlayerArrow;
import util.texture.textureinformation.ITextureStrategy;
import util.texture.textureinformation.PlayerTextureInformation;
import util.texture.textureinformation.ShelfTextureInformation;

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
        return () -> ((Texture) tomato.getTexture()).getImage();
    }

    public ITextureStrategy selectTexture(PlayerEntity player) {
        return new PlayerTextureInformation(player);
    }

    public ITextureStrategy selectTexture(PlayerArrow arrow) {
        return () -> ((Texture) arrow.getTexture()).getImage();
    }

}
