package controller.tasks;

import game.GameState;

public interface Task {

    void call(GameState state);

}
