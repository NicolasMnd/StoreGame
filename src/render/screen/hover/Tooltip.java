package render.screen.hover;

import util.texture.textureinformation.IRender;

public abstract class Tooltip {

    private final Rectangle rectangle;

    public Tooltip(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public abstract IRender renderInformation();

}
