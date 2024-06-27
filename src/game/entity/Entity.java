package game.entity;

import game.GameObject;
import util.Pos;

public abstract class Entity extends GameObject {

    public Entity(Pos pos) {
        super(pos, null);
    }

}
