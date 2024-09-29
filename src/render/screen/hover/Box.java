package render.screen.hover;

import game.ScreenObject;
import render.game.renderorder.RenderStrategy;
import render.game.renderorder.RenderableScreenObject;
import util.positions.Pos;
import util.texture.textureinformation.IRender;

public abstract class Box extends RenderableScreenObject {

    private final int x, y, width, height;

    public Box(int x, int y, int width, int height) {
        super(null, new Pos(x, y));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public IRender getRenderStrategy(ScreenObject object) {
        return new RenderStrategy().rectangleRenderer(() -> this.renderPosition, width, height);
    }

    @Override
    public String toString() {
        return "Box object";
    }

}
