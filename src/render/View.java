package render;

import game.GameState;
import listeners.InputHandler;

public interface View {

    void render(GameState state);

    void registerMouseHandler(InputHandler listener);

    void registerKeyHandler(InputHandler listener);

}
