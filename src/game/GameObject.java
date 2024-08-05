package game;

import game.property.PropertyManager;
import util.Direction;
import util.positions.Hitbox;
import util.positions.Pos;

public abstract class GameObject extends ScreenObject {

    private Direction facing;
    private final PropertyManager properties;

    public GameObject(Pos pos) {
        super(pos);
        this.properties = new PropertyManager();
    }

    /**
     * Returns the {@link Direction} where this object is currently facing towards
     * @return the {@link Direction}
     */
    public Direction getFacing() {
        return this.facing;
    }

    /**
     * Overwrites the facing of this object to the parameter
     * @param dir the new {@link Direction} facing.
     */
    public void setFacing(Direction dir) {
        this.facing = dir;
    }

    /**
     * Returns the {@link PropertyManager} containing all assigned {@link game.property.Property} instances
     * @return the {@link PropertyManager}
     */
    public PropertyManager getProperties() {
        return this.properties;
    }

    /**
     * returns the {@link Hitbox} of this object
     * @return the {@link Hitbox} object
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.getPosition(), this.getPosition().add(new Pos(width, height)));
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getName() + ":" + this.getPosition().getFormat() + ":" + this.facing + "]";
    }

}
