package util;

public enum Direction {
    UP("up"),
    DOWN("down"),
    RIGHT("right"),
    LEFT("left"),
    NORTH("north"),
    EAST("east"),
    WEST("west"),
    SOUTH("south");

    private final String stringValue;

    // Constructor to initialize the stringValue field
    private Direction(String stringValue) {
        this.stringValue = stringValue;
    }

    // Getter to retrieve the string value
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Returns a direction corresponding to a string key of the form: N, S, W, E, R, L, U, B
     */
    public Direction getDirectionFrom(String key) {
        switch(key) {
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "W":
                return WEST;
            case "E":
                return EAST;
            case "U":
                return UP;
            case "B":
                return DOWN;
            case "R":
                return RIGHT;
            case "L":
                return LEFT;
        }
        return NORTH;
    }

    public Direction turnLeft(Direction d) {
        switch (d) {
            case NORTH:
                return Direction.WEST;
            case SOUTH:
                return Direction.EAST;
            case EAST:
                return Direction.NORTH;
            case WEST:
                return Direction.SOUTH;
        }
        return NORTH;
    }

    public Direction turnRight(Direction d) {
        switch (d) {
            case NORTH:
                return Direction.EAST;
            case SOUTH:
                return Direction.WEST;
            case EAST:
                return Direction.SOUTH;
            case WEST:
                return Direction.NORTH;
        }
        return NORTH;
    }

    public Direction opposite(Direction d) {
        switch (d) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
        }
        return NORTH;
    }

    /**
     * When the rotate key is pressed, the direction changes to the right.
     */
    public final Direction rotate(Direction d) {
        if(d == NORTH) return EAST;
        if(d == EAST) return SOUTH;
        if(d == SOUTH) return WEST;
        else return NORTH;
    }

    public final Direction getFacing(Direction globalFacing, Direction tileFacing) {
        if(globalFacing == NORTH) return tileFacing;
        if(globalFacing == EAST) return turnLeft(tileFacing);
        if(globalFacing == WEST) return turnRight(tileFacing);
        return opposite(tileFacing);
    }
}
