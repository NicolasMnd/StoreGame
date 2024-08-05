package render.game.renderorder;

import game.entity.Entity;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;

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

}
