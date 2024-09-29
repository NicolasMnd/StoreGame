package controller;

import controller.input.InputHandler;
import controller.tasks.TaskManager;
import game.entity.types.Entity;
import game.state.GameState;
import render.View;
import render.game.GameView;
import render.screen.hover.Hover;
import util.Dimension;
import util.Direction;

import java.util.function.Consumer;

/**
 * The first layer where we will connect subsystems.
 * Currently, this class will take care of:
 *   1) Creating the swing view
 *   2) Enabling mouse listeners
 *   3) Passing commands
 *   4) Ticking
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
        state.getListenerManager().subscribeAnimationListener(this.taskManager.animationEventManager.getListener());
        state.getListenerManager().subscribeHoverListener(this.taskManager.hoverEventManager.getListener());
        view.registerMouseHandler(inputHandler);
        view.registerKeyHandler(inputHandler);
        view.registerListener(state.close());
    }

    private void tick() {
        taskManager.processEvents(state);
    }

    public void leftClicked(int x, int y) {

    }

    public void rightClicked(int x, int y) {

    }

    public void hover(int x, int y) {

        if(view.getCamera() == null)
            return;

        this.taskManager.hoverEventManager.addHover(
                new Hover(state, view.getCamera()).getHoveredItem(x, y)
        );

    }

    public void playerCommand(Consumer<Entity> command) {
        command.accept(this.state.getPlayer());
    }

    public void rotateLeft() {
        this.state.rotateMap(Direction.LEFT);
    }

    public void rotateRight() {
        this.state.rotateMap(Direction.RIGHT);
    }

    /**
     * Invokes the facade to render itself. All data necessary {@link GameState} will need to be transferred to the View
     */
    public void render() {
        this.view.render(this.state);
    }

    /**
     * Updates the {@link GameState} to the {@link GameView}
     */
    public void update() {
        this.view.update(this.state);
        tick();
    }

}
