package controller.input;

import util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DirectionInput extends KeyEvent {

    public final static char NORTH_EAST = 0xfff1;
    public final static char NORTH_WEST = 0xfff2;
    public final static char SOUTH_EAST = 0xfff3;
    public final static char SOUTH_WEST = 0xfff4;

    public DirectionInput(Component comp, int keyCode, char c) {
        super(comp, -1, -1, -1, keyCode, c, 1);
    }

    public static KeyEvent getKeyInput(Direction dir, Component comp) {
        switch(dir) {
            case NORTH_EAST:
                return new DirectionInput(comp, NORTH_EAST, (char) 0xfff1);
            case NORTH_WEST:
                return new DirectionInput(comp, NORTH_WEST, (char) 0xfff2);
            case SOUTH_EAST:
                return new DirectionInput(comp, SOUTH_EAST, (char) 0xfff3);
            case SOUTH_WEST:
                return new DirectionInput(comp, SOUTH_WEST, (char) 0xfff4);
        }
        return null;
    }

}
