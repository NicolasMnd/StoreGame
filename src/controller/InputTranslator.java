package controller;

import game.entity.Entity;
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
        this.cooldown = 20;
        this.entryTime = System.currentTimeMillis();
    }

    public void translate(GameFacade facade, KeyEvent event) {

        switch(event.getKeyCode()) {

            case KeyEvent.VK_Q:
                facade.move(Direction.LEFT);
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.LEFT)));
                return;

            case KeyEvent.VK_S:
                facade.move(Direction.DOWN);
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.DOWN)));
                return;

            case KeyEvent.VK_Z:
                facade.move(Direction.UP);
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.UP)));
                return;

            case KeyEvent.VK_D:
                facade.move(Direction.RIGHT);
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.RIGHT)));
                return;

            case KeyEvent.VK_SPACE:
                sendToFacade(() -> facade.playerCommand(Entity::jump));

        }

    }

    private void sendToFacade(Runnable command) {
        if(this.entryTime + cooldown < System.currentTimeMillis()) {
            this.entryTime = System.currentTimeMillis();
            command.run();
            return;
        }
    }

}
