package game.tile;

import game.GameObject;
import util.Direction;
import util.Pos;

public abstract class GameTile extends GameObject {

    public GameTile(Pos pos) {
        super(pos);
    }

    @Override
    public void updatePosition(Pos pos) {
        return;
    }

    @Override
    public void setFacing(Direction dir) {
    }

}
