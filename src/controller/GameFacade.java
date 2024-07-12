package controller;

import controller.tasks.TaskManager;
import game.GameState;
import listeners.InputHandler;
import render.Camera;
import render.GameView;
import render.View;
import util.Dimension;

/**
 * The first layer where we will connect subsystems.
 * Currently, this class will take care of:
 *   1) Creating the swing view
 *   2) Enabling mouse listeners
 */
public class GameFacade {

    private final View view;
    private GameState state;
    private final int GAME_SIZE = 32;
    private final Dimension windowSize = new Dimension(30*32, 24*32);
    private final TaskManager taskManager;

    public GameFacade(InputHandler inputHandler) {
        this.state = new GameState(GAME_SIZE, windowSize);
        this.taskManager = new TaskManager();
        this.view = new GameView(GAME_SIZE, windowSize, new Camera(state.getPlayerPosition(), windowSize.getWidth(), windowSize.getHeight(), GAME_SIZE));
        view.registerMouseHandler(inputHandler);
        view.registerKeyHandler(inputHandler);
    }

    private void tick() {

    }

    public void leftClicked(int x, int y) {

    }

    public void rightClicked(int x, int y) {

    }

    public void hover(int x, int y) {

    }

    public void enterCharacter() {

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
