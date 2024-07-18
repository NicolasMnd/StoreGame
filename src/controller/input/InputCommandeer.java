package controller.input;

import controller.GameFacade;
import game.entity.Entity;
import util.Direction;
import util.Logger;
import util.OperationTime;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Long> commandCooldowns;
    private final Logger logger;

    public InputCommandeer() {
        this.cooldown = 20;
        this.entryTime = System.currentTimeMillis();
        this.commandCooldowns = new HashMap<>();
        this.logger = new Logger("Command translation");
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

    /**
     * Provides a cooldown for input & executes commands provided by {@link InputCommandeer#translate(GameFacade, KeyEvent)}
     * @param command the command to be run
     */
    private void sendToFacade(Runnable command) {
        OperationTime time =new OperationTime("operation translation");
        time.start();
        String commandName = getCommandId(command);

        // Register new command
        if(!this.commandCooldowns.containsKey(commandName)) {
            this.commandCooldowns.put(commandName, System.currentTimeMillis() - 1 - cooldown);
        }

        // this.entryTime + cooldown < System.currentTimeMillis()
        if(this.commandCooldowns.get(commandName) + cooldown < System.currentTimeMillis()) {
            this.commandCooldowns.remove(commandName);
            this.commandCooldowns.put(commandName, System.currentTimeMillis());
            command.run();
        }

        time.stop();
        logger.time(time.getNano());

    }

    private String getCommandId(Runnable command) {
        return command.getClass().descriptorString();
    }

}
