package controller;

import game.GameState;
import listeners.InputHandler;
import render.Camera;
import render.GameView;
import render.View;
import util.Dimension;
import util.Pos;

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

    public GameFacade(InputHandler inputHandler) {
        this.state = new GameState(GAME_SIZE, windowSize);
        this.view = new GameView(GAME_SIZE, windowSize, new Camera(state.getPlayerPosition(), windowSize.getWidth(), windowSize.getHeight(), GAME_SIZE));
        view.registerMouseHandler(inputHandler);
        view.registerKeyHandler(inputHandler);
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

    }

    public void increaseSize() {
        System.out.println("hello");
        ((GameView) this.view).increaseSize();
    }

    public void decreaseSize() {
        ((GameView) this.view).decreaseSize();
    }

    /**
     * Invokes the facade to render itself. All data necessary will need to be transferred to the View
     */
    public void render() {
        this.view.render(this.state);
    }

}
