package listeners;

import util.Pos;
import util.hitbox.Hitbox;

/**
 * Listener defined in {@link game.GameState} and given to {@link game.entity.Entity}
 */
public interface IMoveValidity {

    /**
     * Determines if the given {@link Pos} is a valid {@link game.entity.Entity} position
     * @param box the Hit box of an entity
     * @return a boolean determining if the given {@link Pos} is valid for entity
     */
    boolean canMoveTo(Hitbox box);

}
