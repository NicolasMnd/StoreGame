package game;

import util.Direction;
import util.Pos;
import util.texture.Texture;

public abstract class GameObject {

    private Pos pos;
    private final Texture texture;
    private Direction facing;

    public GameObject(Pos pos, Texture texture) {
        this.texture = texture;
        this.facing = Direction.NORTH;
    }

    /**
     * Allows subclasses to implement update position behaviour.
     *
     * @param pos the new position
     */
    public void updatePosition(Pos pos) {
        this.pos = pos;
    }

    /**
     * Returns a clone of the position of this object
     * @return the position in a {@link Pos} object
     */
    public Pos getPosition() {
        return this.pos.clone();
    }

    /**
     * Returns the {@link Direction} where this object is currently facing towards
     * @return the {@link Direction}
     */
    public Direction getFacing() {
        return  this.facing;
    }

    /**
     * Overwrites the facing of this object to the parameter
     * @param dir the new {@link Direction} facing.
     */
    public void setFacing(Direction dir) {
        this.facing = dir;
    }

}
