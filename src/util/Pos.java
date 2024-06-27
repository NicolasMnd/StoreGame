package util;

import com.misterl.location.positions.Pos2di;

public class Pos extends Pos2di {

    public int x, y;
    public final PositionHelper operation;

    public Pos(int x, int y) {
        super(x, y);
        this.operation = new PositionHelper(this);
    }

    /**
     * Moves this position to up
     */
    public Pos moveUp() {
        this.y--;
        return new Pos(x, y);
    }

    /**
     * Moves this position down
     */
    public Pos moveDown() {
        this.y++;
        return new Pos(x, y);
    }

    /**
     * Moves this position to the right
     */
    public Pos moveRight() {
        this.x++;
        return new Pos(x, y);
    }

    /**
     * Moves this position to the left
     */
    public Pos moveLeft() {
        this.x--;
        return new Pos(x, y);
    }

    /**
     * Returns a clone of this object
     * @return a cloned {@link Pos} object
     */
    public Pos clone() {
        return new Pos(x, y);
    }

}
