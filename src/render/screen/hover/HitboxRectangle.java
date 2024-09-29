package render.screen.hover;

import game.ScreenObject;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderStrategy;
import render.game.renderorder.RenderableScreenObject;
import util.positions.Hitbox;
import util.positions.Pos;
import util.texture.textureinformation.IRender;

public class HitboxRectangle extends RenderableScreenObject {

    private final Hitbox b;

    public HitboxRectangle(ScreenObject parent, Pos renderPosition, Hitbox box) {
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
