package render.game.load;

import game.state.GameState;
import game.tile.GameTile;
import game.tile.TileGround;
import render.game.camera.Camera;
import render.game.renderorder.RenderableScreenObject;
import render.screen.hover.HitboxRectangle;
import util.positions.Hitbox;

import java.util.ArrayList;
import java.util.List;

public class RenderHovered implements IHasRenderables {

    @Override
    public List<RenderableScreenObject> getRenderables(GameState state, Camera camera) {
        return createList(state.getListenerManager().getHovers());
    }

    private List<RenderableScreenObject> createList(List<Hitbox> boxes) {
        List<RenderableScreenObject> list = new ArrayList<>();
        for(Hitbox b : boxes) {
            GameTile parent = new TileGround(b.getUpperleft());
            list.add(new HitboxRectangle(parent, parent.getPosition(), b));
        }
        return list;
    }

}
