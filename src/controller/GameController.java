package controller;

import com.misterc.controller.Controller;
import com.misterc.controller.MisterC;
import com.misterc.input.InputType;
import listeners.MouseHandler;
import listeners.IMouseNotifier;

public class GameController extends Controller {

    /**
     * The facade which will connect all subsystems
     */
    private final GameFacade facade;

    /**
     * Allows this class to receive mouse input requests
     */
    private final MouseHandler mouseHandler;

    public GameController(MisterC c) {
        super(c, null);
        this.mouseHandler = new MouseHandler();
        this.mouseHandler.subscribeListener(getNotifier());
        this.facade = new GameFacade(mouseHandler);
    }

    @Override
    public void handle(InputType inputType) {

    }

    @Override
    public void paint() {
        this.facade.render();
    }

    // Creates a mouse listener
    private IMouseNotifier getNotifier() {
        return new IMouseNotifier() {
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
        };
    }

}
