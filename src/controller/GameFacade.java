package controller;

import controller.tasks.TaskManager;
import game.GameState;
import game.entity.Entity;
import controller.input.InputHandler;
import render.GameView;
import render.View;
import util.Dimension;
import util.Direction;

import java.util.function.Consumer;

/**
 * The first layer where we will connect subsystems.
 * Currently, this class will take care of:
 *   1) Creating the swing view
 *   2) Enabling mouse listeners
 */
public class GameFacade {

    private final View view;
    GameState state;
    private final int TILE_SIZE = 32;
    private final Dimension windowSize = new Dimension(30*TILE_SIZE, 24*TILE_SIZE);
    private final TaskManager taskManager;

    public GameFacade(InputHandler inputHandler) {
        this.taskManager = new TaskManager();
        this.view = new GameView(TILE_SIZE, windowSize);
        this.state = new GameState(TILE_SIZE, windowSize, view::getGameSize);
        view.registerMouseHandler(inputHandler);
        view.registerKeyHandler(inputHandler);
    }

    private void tick() {
        taskManager.updateTickables(state);
    }

    public void leftClicked(int x, int y) {

    }

    public void rightClicked(int x, int y) {

    }

    public void hover(int x, int y) {

    }

    public void move(Direction d) {
        this.state.move(d);
    }

    public void playerCommand(Consumer<Entity> command) {
        command.accept(this.state.getPlayer());
    }

    public void rotateMap() {

    }

    public void increaseSize() {
        this.view.increaseSize();
    }

    public void decreaseSize() {
        this.view.decreaseSize();
    }

    /**
     * Invokes the facade to render itself. All data necessary will need to be transferred to the View
     */
    public void render() {
        this.view.render(this.state);
    }

    /**
     * Updates the {@link GameState} to the {@link GameView}
     */
    public void update() {
        this.taskManager.updateTickables(this.state);
        this.view.update(this.state);
        tick();
    }

}
