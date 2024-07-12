package controller;

import util.Direction;

import java.awt.event.KeyEvent;

public class InputTranslator {

    public void translate(GameFacade facade, KeyEvent event) {

        switch(event.getKeyChar()) {

            case 'q':
                facade.move(Direction.LEFT);
                return;

            case 's':
                facade.move(Direction.DOWN);
                return;

            case 'z':
                facade.move(Direction.UP);
                return;

            case 'd':
                facade.move(Direction.RIGHT);
                return;

        }

    }

}
