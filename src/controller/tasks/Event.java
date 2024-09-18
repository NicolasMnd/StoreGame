package controller.tasks;

import game.state.GameState;

public interface Event {

    void call(GameState state);

}
