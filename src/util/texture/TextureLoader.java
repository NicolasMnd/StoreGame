package util.texture;

import game.entity.PlayerEntity;
import game.item.vegetables.ItemTomato;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import render.screen.effect.player.PlayerArrow;
import util.texture.comp.Texture;
import util.texture.textureinformation.ITextureLoader;
import util.texture.textureinformation.PlayerTextureInformation;
import util.texture.textureinformation.ShelfTextureInformation;

import java.io.File;

/**
 * Instances of {@link game.GameObject} will call upon {@link game.GameObject#textureLoader(TextureLoader)} and double dispatch here.
 *
 * This class brings all texture loading together. It makes sure that the texture information is stored.
 */
public class TextureLoader {

    public ITextureLoader loadTexture(TileShelf shelf) {
        return new ShelfTextureInformation(shelf);
    }

    public ITextureLoader loadTexture(TileWall wall) {
        return () -> new Texture(new File("resources/tiles/wall.png"));
    }

    public ITextureLoader loadTexture(TileGround ground) {
        return () -> new Texture(new File("resources/tiles/floor.png"));
    }

    public ITextureLoader loadTexture(PlayerEntity player) {
        return new PlayerTextureInformation(player);
    }

    public ITextureLoader loadTexture(PlayerArrow arrow) {
        return () -> new Texture(new File("resources/ui/arrow_down.png"));
    }

    public ITextureLoader loadTexture(ItemTomato tomato) {
        return () -> new Texture(new File("resources/items/tomato.png"));
    }

}
