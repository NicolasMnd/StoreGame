package render.game.renderorder;

import game.entity.Entity;
import game.item.vegetables.ItemTomato;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import render.screen.effect.player.PlayerArrow;

/**
 * Visitor for Game Objects. This allows them to retrieve a base setting for their render stage.
 */
public class RenderStageSelector {

    public RenderStage getRenderStage(TileGround ground) {
        return RenderStage.BACKGROUND;
    }

    public RenderStage getRenderStage(TileWall wall) {
        return RenderStage.BACKGROUND;
    }

    public RenderStage getRenderStage(TileShelf shelf) {
        return RenderStage.TILES;
    }

    public RenderStage getRenderStage(Entity e) {
        return RenderStage.PLAYER;
    }

    public RenderStage getRenderStage(PlayerArrow playerArrow) {
        return RenderStage.FOREGROUND;
    }

    public RenderStage getRenderStage(ItemTomato tomato) {
        return RenderStage.PLAYER;
    }

}
