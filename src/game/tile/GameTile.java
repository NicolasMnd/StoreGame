package game.tile;

import game.GameObject;
import util.Direction;
import util.Pos;

public abstract class GameTile extends GameObject {

    public GameTile(Pos pos) {
        super(pos);
        setWidth(32);
        setHeight(32);
        setFacing(Direction.NORTH);
    }

    /**
     * @return if a {@link game.entity.Entity} can collide with this {@link GameTile}.
     */
    public boolean canCollide() {
        return true;
    }

    @Override
    public final void updatePosition(Pos pos) {
        return;
    }

}
