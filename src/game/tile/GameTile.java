package game.tile;

import game.GameObject;
import game.entity.types.Entity;
import util.Direction;
import util.positions.Hitbox;
import util.positions.Pos;

public abstract class GameTile extends GameObject {

    public GameTile(Pos pos) {
        super(pos);
        setWidth(32);
        setHeight(32);
        setFacing(Direction.NORTH);
    }

    /**
     * @return if a {@link Entity} can collide with this {@link GameTile}.
     */
    public boolean canCollide() {
        return true;
    }

    /**
     * @return a {@link Hitbox} that represents the space where the player walks behind the object.
     */
    public Hitbox getOverlapHitbox() {
        return null;
    }

}
