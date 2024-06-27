package util;

import com.misterl.location.positions.Pos2d;
import com.misterl.location.positions.Pos2di;

import java.util.function.Function;

public class PositionHelper {

    private final Pos pos;
    private final DirectionHelper directionHelper;

    public PositionHelper(Pos pos) {
        this.pos = pos;
        this.directionHelper = new DirectionHelper();
    }

    /**
     * Determines if the given position is next to the other position.
     * Note the {@link Pos} object where this method is called from is the CENTER.
     * @param pos the position which should be checked
     * @param directions a pair of directions: up,right ; up,left...
     * @return a boolean determining if the given {@link Pos} object is in a 1 unit radius
     */
    public boolean isDiagonally(Pos pos, Pair<Direction, Direction> directions, int range) {
        Pos2di subtracted = (Pos2di) this.pos.subtract(pos);

        // Check if the general position is correct: range in x after subtracting
        if(Math.abs(subtracted.x()) != range) return false;

        // Check if the general position is correct: range in x after subtracting
        if(Math.abs(subtracted.y()) != range) return false;

        // Check if the positions are an exact match
        int xInDirection = this.pos.x();
        for(int i = 0; i < range; i++)
            xInDirection = directionHelper.getFunction(directions).getFirst().apply(xInDirection);

        int yInDirection = this.pos.y();
        for(int i = 0; i < range; i++)
            yInDirection = directionHelper.getFunction(directions).getSecond().apply(yInDirection);

        return new Pos(xInDirection, yInDirection).equals(pos);
    }

    /**
     * Determines if the given position is next to the other position
     * @param pos the position which should be checked
     * @param dir the direction of where the position should find itself
     * @return a boolean determining if the given {@link Pos} object is in a 1 unit radius
     */
    public boolean isConnected(Pos pos, Direction dir) {
        if(dir == null) {
            Pos2di subtracted = (Pos2di) this.pos.subtract(pos);
            return Math.abs(subtracted.x()) == 1 || Math.abs(subtracted.y()) == 1;
        } else {
            Pair<Function<Integer, Integer>, Function<Integer, Integer>> functionPair = directionHelper.getFunction(new Pair<>(dir, null));
            int x = functionPair.getFirst().apply(this.pos.x);
            int y = functionPair.getSecond().apply(this.pos.y);
            return pos.equals(new Pos(x, y));
        }
    }

    /**
     * Determines if the given position is on the same axis and at least in the given range
     * @param pos the position to be checked
     * @param range the max range
     * @return a boolean
     */
    public boolean isConnected1D(Pos pos, int range) {
        if(pos.x() == this.pos.x())
            return Math.abs(this.pos.subtract(pos).y()) <= range;
        else if(pos.y() == this.pos.y())
            return Math.abs(this.pos.subtract(pos).x()) <= range;
        return false;
    }



}
