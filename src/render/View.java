package render;

import controller.input.InputHandler;
import game.state.GameState;
import listeners.ListenerRegistrator;
import util.Dimension;

public interface View {

    void render(GameState state);

    void update(GameState state);

    void registerMouseHandler(InputHandler listener);

    void registerKeyHandler(InputHandler listener);

    void registerListener(ListenerRegistrator listener);

    double getGameSize();

    int getTileSize();

    Dimension getDimension();

}
