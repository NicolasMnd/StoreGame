package util.texture;

import game.ScreenObject;

public abstract class TextureAnimation {

    protected final ScreenObject parent;

    public TextureAnimation(ScreenObject start) {
        this.parent = start;
    }

    /**
     * Ticks the animation
     */
    public void tick() {
    }

    /**
     * @return a {@link ScreenObject}
     */
    public abstract ScreenObject getScreenObject();

}
