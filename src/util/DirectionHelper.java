package util;

import java.util.function.Function;

import static util.Direction.*;

public class DirectionHelper {

    public DirectionHelper() {
    }

    /**
     * Determines if the given {@link Pair} is a collection of two {@link Direction} objects that determine one of the points in a square
     * @param directionPair the pair that is checked
     * @return a boolean
     */
    public boolean isSquareDirection(Pair<Direction, Direction> directionPair) {
        // If none of the entries contain a UP or DOWN, it is always false
        if(!(isUpOrDown(directionPair.getFirst()) || isUpOrDown(directionPair.getSecond())) && !isCompDirection(directionPair))
            return false;

        // If the first argument is UP or DOWN ; check if second is LEFT or RIGHT
        if(isUpOrDown(directionPair.getFirst())) {

            return isLeftOrRight(directionPair.getSecond()) || isCompDirection(directionPair);

        // Otherwise invert the operation and do again
        } if(isCompDirection(directionPair))
            return true;
        else return isSquareDirection(new Pair<>(directionPair.getSecond(), directionPair.getFirst()));
    }

    /**
     * Determines if the given {@link Pair} of {@link Direction} forms a specific wind direction
     * @return
     */
    public boolean isCompDirection(Pair<Direction, Direction> direction) {
        return !direction.getFirst().opposite(direction.getFirst()).equals(direction.getSecond()) && !direction.getFirst().equals(direction.getSecond());
    }

    /**
     * Returns functions that determine the behaviour of positions in the given direction pair
     * @param directionPair the pair for which functions will be retrieved
     * @return a {@link Pair} consisting of {@link Function} elements specifying the direction in the pair
     */
    public Pair<Function<Integer, Integer>, Function<Integer, Integer>> getFunction(Pair<Direction, Direction> directionPair) {
        // Up or down == Y coordinate ; put as second argument
        if(isUpOrDown(directionPair.getFirst()) || isNorthOrSouth(directionPair.getFirst()))
            return new Pair<>(getFunctionFor(directionPair.getSecond()), getFunctionFor(directionPair.getFirst()));
        else
            return new Pair<>(getFunctionFor(directionPair.getFirst()), getFunctionFor(directionPair.getSecond()));
    }

    private boolean isUpOrDown(Direction dir) {
        return dir == UP || dir == DOWN;
    }

    private boolean isNorthOrSouth(Direction dir) {
        return dir == NORTH || dir == SOUTH;
    }

    private boolean isLeftOrRight(Direction dir) {
        return dir == LEFT || dir == RIGHT;
    }

    private boolean isEastOrWest(Direction dir) {
        return dir == EAST || dir == WEST;
    }

    private Function<Integer, Integer> getFunctionFor(Direction dir) {
        if(dir == null) return i -> i;
        switch(dir) {
            case LEFT, WEST:
                return i -> i-1;
            case RIGHT, EAST:
                return i -> i+1;
            case UP, NORTH:
                return i -> i-1;
            case DOWN, SOUTH:
                return i -> i+1;
        }
        return i -> i;
    }

}
