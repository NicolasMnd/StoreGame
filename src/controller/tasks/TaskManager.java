package controller.tasks;

import game.state.GameState;

public class TaskManager {

    public void updateTickables(GameState state) {
        new TickTask().call(state);
    }

}
