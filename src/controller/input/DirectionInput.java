package controller.input;

import util.Direction;

import java.awt.event.KeyEvent;

public class DirectionInput extends KeyEvent {

    public final static int NORTH_EAST = 0xfff1;
    public final static int NORTH_WEST = 0xfff2;
    public final static int SOUTH_EAST = 0xfff3;
    public final static int SOUTH_WEST = 0xfff4;

    public DirectionInput(int keyCode) {
        super(null, -1, -1, -1, keyCode, ' ', -1);
    }

    public KeyEvent getKeyInput(Direction dir) {
        switch(dir) {
            case NORTH_EAST -> new DirectionInput(NORTH_EAST);
            case NORTH_WEST -> new DirectionInput(NORTH_WEST);
            case SOUTH_EAST -> new DirectionInput(SOUTH_EAST);
            case SOUTH_WEST -> new DirectionInput(SOUTH_WEST);
        }
        return null;
    }

}
