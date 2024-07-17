package controller.input;

import controller.GameFacade;
import game.entity.Entity;
import util.Direction;

import java.awt.event.KeyEvent;

/**
 * This class contains all details about translating a {@link KeyEvent} to a command in {@link GameFacade}.
 * For now cooldowns will also be handled here.
 */
public class InputCommandeer {

    /**
     * Amount of ms before sending a command to facade.
     */
    private final int cooldown;
    private long entryTime;

    public InputCommandeer() {
        this.cooldown = 20;
        this.entryTime = System.currentTimeMillis();
    }

    /**
     * Method that will translate a {@link KeyEvent} to an action
     * @param facade the control layer of the game
     * @param event the {@link KeyEvent} that will be translated
     */
    public void translate(GameFacade facade, KeyEvent event) {

        switch(event.getKeyCode()) {

            case KeyEvent.VK_Q:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.LEFT)));
                return;

            case KeyEvent.VK_S:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.DOWN)));
                return;

            case KeyEvent.VK_Z:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.UP)));
                return;

            case KeyEvent.VK_D:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.RIGHT)));
                return;

            case DirectionInput.NORTH_EAST:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.NORTH_EAST)));
                return;

            case DirectionInput.NORTH_WEST:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.NORTH_WEST)));
                return;

            case DirectionInput.SOUTH_EAST:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.SOUTH_EAST)));
                return;

            case DirectionInput.SOUTH_WEST:
                sendToFacade(() -> facade.playerCommand(player -> player.move(Direction.SOUTH_WEST)));
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
