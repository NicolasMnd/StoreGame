package controller;

import game.GameState;
import listeners.InputHandler;
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
    private final int GAME_SIZE = 32;

    public GameFacade(InputHandler inputHandler) {
        this.view = new GameView(GAME_SIZE);
        view.registerMouseHandler(inputHandler);
        view.registerKeyHandler(inputHandler);
        state.init(GAME_SIZE);
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

    public void enterCharacter() {
        System.out.println("Key pressed");
    }

    /**
     * Invokes the facade to render itself. All data necessary will need to be transferred to the View
     */
    public void render() {
        this.view.render(this.state);
    }

}
