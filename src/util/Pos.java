package util;

import com.misterl.location.positions.Pos2d;
import com.misterl.location.positions.Pos2di;

public class Pos extends Pos2di {

    public final PositionHelper operation;

    public Pos(int x, int y) {
        super(x, y);
        this.operation = new PositionHelper(this);
    }

    /**
     * Moves this position to up
     */
    public void moveUp(int multiplier) {
        this.addY(-1*multiplier);
        new Pos(x(), y());
    }

    /**
     * Moves this position down
     */
    public void moveDown(int multiplier) {
        this.addY(multiplier);
        new Pos(x(), y());
    }

    /**
     * Moves this position to the right
     */
    public void moveRight(int multiplier) {
        this.addX(multiplier);
        new Pos(x(), y());
    }

    /**
     * Moves this position to the left
     */
    public void moveLeft(int multiplier) {
        this.addX(-1*multiplier);
        new Pos(x(), y());
    }

    @Override
    public Pos add(Pos2d<Integer> pos) {
        return new Pos(this.x() + pos.x(), this.y() + pos.y());
    }

    @Override
    public Pos subtract(Pos2d<Integer> pos) {
        return new Pos(this.x() - pos.x(), this.y() - pos.y());
    }

    /**
     * Returns a clone of this object
     * @return a cloned {@link Pos} object
     */
    public Pos clone() {
        return new Pos(x(), y());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pos p)
            return this.x().equals(p.x()) && this.y().equals(p.y());
        return false;
    }
}
