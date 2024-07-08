package render;

import game.GameObject;
import game.GameState;
import game.tile.GameTile;
import listeners.InputHandler;
import util.texture.TextureSelector;

import javax.swing.*;
import java.awt.*;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JPanel implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private int size;
    private JFrame frame;

    public GameView(int size) {
        this.size = size;
        this.frame = initializeFrame();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        System.out.println("Painting components");
        this.graphics = (Graphics2D) g;
        renderGameState();
        g.dispose();
    }

    @Override
    public void render(GameState state) {
        System.out.println("Painting componentssdfs");
        this.latestGameState = state;
        repaint();
    }

    @Override
    public void registerMouseHandler(InputHandler listener) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    @Override
    public void registerKeyHandler(InputHandler listener) {
        //this.frame.addKeyListener(listener);
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
        System.out.println("Gametiles: " + latestGameState.getTiles());
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

    private JFrame initializeFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Colruyt Simulator");
        frame.getContentPane().add(this);
        frame.revalidate();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(192*4, 256*4);
        frame.setLocationRelativeTo(null);
        return frame;
    }


}
