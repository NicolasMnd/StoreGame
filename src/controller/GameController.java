package controller;

import com.misterc.controller.Controller;
import com.misterc.controller.MisterC;
import com.misterc.input.InputType;

public class GameController extends Controller {

    /**
     * The facade which will connect all subsystems
     */
    private final GameFacade facade;

    public GameController(MisterC c) {
        super(c, null);
        this.facade = new GameFacade();
    }

    @Override
    public void handle(InputType inputType) {

    }

    @Override
    public void paint() {

    }

}
