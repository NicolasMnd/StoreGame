package game;

import util.Pos;
import util.texture.Texture;

public abstract class GameObject {

    private Pos pos;
    private final Texture texture;

    public GameObject(Pos pos, Texture texture) {
        this.texture = texture;
    }

    public abstract void updatePosition(Pos pos);

    /**
     * Returns a clone of the position of this object
     * @return the position in a {@link Pos} object
     */
    public Pos getPosition() {
        return this.pos.clone();
    }

}
