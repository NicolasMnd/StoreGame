package game.tile;

import game.GameObject;
import util.Direction;
import util.Pos;
import util.hitbox.TopHitbox;

public abstract class GameTile extends GameObject {

    private int height;

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

    /**
     * @return the hitbox of the top of the tile
     */
    public TopHitbox getTopHitbox() {
        return null;
    }

    @Override
    public final void updatePosition(Pos pos) {
        return;
    }

    /**
     * Sets the height of this {@link GameTile}
     * @param height the height
     */
    protected final void setTileHeight(int height) {
        this.height = height;
    }

}
