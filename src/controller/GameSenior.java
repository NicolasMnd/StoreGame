package controller;

/**
 * This class is the main controller of the game. It will get input and delegate it
 */
public class GameSenior {

    private GameController controller;

    public GameSenior() {
        this.controller = new GameController();
        startRefresh();
    }

    private void startRefresh() {
        double drawInterval = 1000 / 144;
        javax.swing.Timer t = new javax.swing.Timer((int) drawInterval,
                (e) -> controller.idle(e)
        );
        t.start();
    }

}
