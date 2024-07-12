package game.tile;

import game.GameObject;
import util.Pos;

public abstract class GameTile extends GameObject {

    public GameTile(Pos pos) {
        super(pos);
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void updatePosition(Pos pos) {
        return;
    }

}
