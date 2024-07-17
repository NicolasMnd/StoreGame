package util;

public enum Direction {
    UP("up"),
    DOWN("down"),
    RIGHT("right"),
    LEFT("left"),
    NORTH("north"),
    EAST("east"),
    WEST("west"),
    SOUTH("south"),
    NORTH_EAST("north_east"),
    NORTH_WEST("north_west"),
    SOUTH_EAST("south_east"),
    SOUTH_WEST("south_west");

    private final String stringValue;

    // Constructor to initialize the stringValue field
    private Direction(String stringValue) {
        this.stringValue = stringValue;
    }

    public Direction turnLeft() {
        switch (this) {
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

    public Direction turnRight() {
        switch (this) {
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

    public Direction opposite() {
        switch (this) {
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
            default: LEFT:
                return RIGHT;
        }
    }

}
