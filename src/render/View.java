package render;

import game.GameState;
import controller.input.InputHandler;
import util.Dimension;

public interface View {

    void increaseSize();

    void decreaseSize();

    void render(GameState state);

    void update(GameState state);

    void registerMouseHandler(InputHandler listener);

    void registerKeyHandler(InputHandler listener);

    double getGameSize();

    int getTileSize();

    Dimension getDimension();

}
