package controller;

import listeners.InputHandler;
import listeners.InputNotifier;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameController {

    /**
     * The facade which will connect all subsystems
     */
    final GameFacade facade;

    /**
     * Allows this class to receive mouse input requests
     */
    private final InputHandler inputHandler;

    public GameController() {
        this.inputHandler = new InputHandler();  // input handler is passed down to GameView (JPanel listeners)
        this.inputHandler.subscribeListener(getNotifier());
        this.facade = new GameFacade(inputHandler);
    }

    public void idle(ActionEvent event) {
        update();
        paint();
    }

    public void update() {
        this.facade.update();
    }

    private void paint() {
        this.facade.render();
    }

    // Creates a mouse listener
    private InputNotifier getNotifier() {
        return new InputNotifier() {
            private InputTranslator translator = new InputTranslator();

            @Override
            public void hover(int x, int y) {
                facade.hover(x, y);
            }

            @Override
            public void leftClick(int x, int y) {
                facade.leftClicked(x, y);
            }

            @Override
            public void rightClick(int x, int y) {
                facade.rightClicked(x, y);
            }

            @Override
            public void enterCharacter(KeyEvent e) {
                translator.translate(facade, e);
            }

            @Override
            public void scrollDown() {
                facade.decreaseSize();
            }

            @Override
            public void scrollUp() {
                facade.increaseSize();
            }
        };
    }

}
