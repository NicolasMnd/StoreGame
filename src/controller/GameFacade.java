package controller;

import game.GameState;
import listeners.MouseHandler;
import render.GameView;
import render.View;

/**
 * The first layer where we will connect subsystems.
 * Currently, this class will take care of:
 *   1) Creating the swing view
 *   2) Enabling mouse listeners
 */
public class GameFacade {

    private final View view;
    private GameState state = new GameState();

    public GameFacade(MouseHandler mouseHandler) {
        this.view = new GameView();
        view.registerMouseHandler(mouseHandler);
        state.init();
    }

    /**
     * Let the game idle
     */
    public void tick() {

    }

    public void leftClicked(int x, int y) {

    }

    public void rightClicked(int x, int y) {

    }

    public void hover(int x, int y) {

    }

    /**
     * Invokes the facade to render itself. All data necessary will need to be transferred to the View
     */
    public void render() {
        this.view.render(this.state);
    }

}
