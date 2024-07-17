package controller.input;

import util.Direction;

import java.awt.event.KeyEvent;

public class DirectionInput extends KeyEvent {

    public final static int NORTH_EAST = 0xfff1;
    public final static int NORTH_WEST = 0xfff2;
    public final static int SOUTH_EAST = 0xfff3;
    public final static int SOUTH_WEST = 0xfff4;

    public DirectionInput(int keyCode, char c) {
        super(null, -1, -1, -1, keyCode, c, -1);
    }

    public static KeyEvent getKeyInput(Direction dir) {
        switch(dir) {
            case NORTH_EAST -> new DirectionInput(NORTH_EAST, (char) 0xfff1);
            case NORTH_WEST -> new DirectionInput(NORTH_WEST, (char) 0xfff2);
            case SOUTH_EAST -> new DirectionInput(SOUTH_EAST, (char) 0xfff3);
            case SOUTH_WEST -> new DirectionInput(SOUTH_WEST, (char) 0xfff4);
        }
        return null;
    }

}
