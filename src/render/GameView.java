package render;

import game.GameState;
import listeners.MouseHandler;

import javax.swing.*;
import java.awt.event.MouseListener;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JFrame implements View {

    public GameView() {

    }

    @Override
    public void render(GameState state) {

    }

    @Override
    public void registerMouseHandler(MouseHandler listener) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

}
