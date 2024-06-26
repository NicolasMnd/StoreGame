package game;

import util.Pos;

public abstract class GameTile {

    private final Pos pos;
    private final String texture;

    public GameTile(String texture, Pos pos) {
        this.texture = texture;
        this.pos = pos;
    }

    /**
     * The position of the tile
     * @return a {@link Pos} object
     */
    public Pos getPos() {
        return this.pos;
    }

}
