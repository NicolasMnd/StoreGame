package util.hitbox;

import util.Pos;

/**
 * Another hitbox which represents the top side of a {@link game.tile.GameTile}, by adding another dimension
 */
public class TopHitbox extends Hitbox {

    private final int height;

    public TopHitbox(Pos start, Pos end, int height) {
        super(start, end);
        this.height = height;
    }
    
}
