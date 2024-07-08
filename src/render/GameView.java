package render;

import game.GameObject;
import game.GameState;
import game.tile.GameTile;
import listeners.MouseHandler;
import util.texture.TextureSelector;

import javax.swing.*;
import java.awt.*;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JPanel implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private int size = 16;

    public GameView() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("Colruyt Simulator");
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(192*4, 256*4);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        this.graphics = (Graphics2D) g;
        //renderGameState();
    }

    @Override
    public void render(GameState state) {
        this.latestGameState = state;
        repaint();
    }

    @Override
    public void registerMouseHandler(MouseHandler listener) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    /**
     * @param newSize the new size of the game.
     */
    public void resize(int newSize) {
        this.size = newSize;
    }

    /**
     * @return the scale of the game
     */
    public int getGameSize() {
        return this.size;
    }

    /**
     * Renders all elements of the {@link GameState}
     */
    private void renderGameState() {
        for(GameTile[] tileArr : latestGameState.getTiles())
            for(GameTile tile : tileArr)
                draw(tile);

    }

    /**
     * Draws a given {@link GameObject}
     * @param object an object to be printed
     */
    private void draw(GameObject object) {
        this.graphics.drawImage(
                object.textureSelector(new TextureSelector()).retrieveTexture(),
                object.getPosition().x(),
                object.getPosition().y(),
                object.getWidth()*getGameSize(),
                object.getHeight()*getGameSize(),
                null
        );
    }

}
