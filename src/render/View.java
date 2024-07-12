package render;

import game.GameState;
import listeners.InputHandler;

public interface View {

    void increaseSize();

    void decreaseSize();

    void render(GameState state);

    void update(GameState state);

    void registerMouseHandler(InputHandler listener);

    void registerKeyHandler(InputHandler listener);

}
