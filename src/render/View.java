package render;

import game.GameState;
import listeners.MouseHandler;

import java.awt.event.MouseListener;

public interface View {

    void render(GameState state);

    void registerMouseHandler(MouseHandler listener);

}
