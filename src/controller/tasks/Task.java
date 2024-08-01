package controller.tasks;

import game.state.GameState;

public interface Task {

    void call(GameState state);

}
