package render.game.load;

import game.ScreenObject;
import game.state.GameState;
import game.tile.GameTile;
import game.tile.TileGround;
import render.game.camera.Camera;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStrategy;
import render.game.renderorder.RenderableScreenObject;
import util.positions.Hitbox;
import util.positions.Pos;
import util.texture.textureinformation.IRender;

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
            list.add(new Rectangle(parent, parent.getPosition(), b));
        }
        return list;
    }

    private static class Rectangle extends RenderableScreenObject {

        private final Hitbox b;

        public Rectangle(ScreenObject parent, Pos renderPosition, Hitbox box) {
            super(parent, renderPosition);
            this.b = box;
        }

        @Override
        public IRender getRenderStrategy(ScreenObject object) {
            return new RenderStrategy().renderHitbox(b);
        }

        @Override
        public RenderStage getRenderStage() {
            return RenderStage.FOREGROUND;
        }
    }

}
