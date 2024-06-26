package controller;

import com.misterc.controller.MisterC;
import com.misterc.input.ScannerInput;

/**
 * This class is the main controller of the game. It will get input and delegate it
 */
public class GameSenior extends MisterC {

    public GameSenior() {
        super(new ScannerInput());
        setController(new GameController(this));
    }

}
