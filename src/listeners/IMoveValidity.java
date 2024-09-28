package listeners;

import game.entity.types.Entity;
import game.state.GameState;
import util.positions.Pos;
import util.positions.Hitbox;

/**
 * Listener defined in {@link GameState} and given to {@link Entity}
 */
public interface IMoveValidity {

    /**
     * Determines if the given {@link Pos} is a valid {@link Entity} position
     * @param box the Hit box of an entity
     * @return a boolean determining if the given {@link Pos} is valid for entity
     */
    boolean canMoveTo(Hitbox box);

}
