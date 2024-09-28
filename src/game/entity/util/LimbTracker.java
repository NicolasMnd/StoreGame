package game.entity.util;

import game.entity.types.Entity;
import util.positions.Pos;

public abstract class LimbTracker {

    private final Entity e;

    public LimbTracker(Entity e) {
        this.e = e;
    }

    public abstract Pos getLeftHand();

    public abstract Pos getRightHand();

}
