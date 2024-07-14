package controller;

import util.Direction;

import java.awt.event.KeyEvent;

/**
 * This class contains all details about translating a {@link KeyEvent} to a command in {@link GameFacade}.
 * For now cooldowns will also be handled here.
 */
public class InputTranslator {

    /**
     * Amount of ms before sending a command to facade.
     */
    private final int cooldown;
    private long entryTime;

    public InputTranslator() {
        this.cooldown = 5;
        this.entryTime = System.currentTimeMillis();
    }

    public void translate(GameFacade facade, KeyEvent event) {

        switch(event.getKeyChar()) {

            case 'q':
                sendToFacade(() -> facade.move(Direction.LEFT));
                return;

            case 's':
                sendToFacade(() -> facade.move(Direction.DOWN));
                return;

            case 'z':
                sendToFacade(() -> facade.move(Direction.UP));
                return;

            case 'd':
                sendToFacade(() -> facade.move(Direction.RIGHT));
                return;

        }

    }

    private void sendToFacade(Runnable command) {
        if(this.entryTime + cooldown < System.currentTimeMillis()) {
            this.entryTime = System.currentTimeMillis();
            command.run();
        }
    }

}
