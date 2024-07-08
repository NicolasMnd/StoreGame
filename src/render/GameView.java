package render;

import game.GameObject;
import game.GameState;
import game.tile.GameTile;
import listeners.InputHandler;
import util.Pos;
import util.texture.TextureSelector;

import javax.swing.*;
import java.awt.*;
import util.Dimension;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JPanel implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private final int tileSize;
    private final int gameSize = 2;
    private JFrame frame;

    public GameView(int size, Dimension windowSize) {
        this.tileSize = size;
        this.frame = initializeFrame(windowSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        this.graphics = (Graphics2D) g;
        renderGameState();
        g.dispose();
    }

    @Override
    public void render(GameState state) {
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
        this.frame.addKeyListener(listener);
    }

    /**
     * @return the scale of the game
     */
    public int getGameSize() {
        return this.gameSize;
    }

    /**
     * Renders all elements of the {@link GameState}
     */
    private void renderGameState() {
        for(GameTile[] tileArr : latestGameState.getTiles())
            for(GameTile tile : tileArr)
                if(tile != null)
                    draw(tile);
    }

    /**
     * Draws a given {@link GameObject}
     * @param object an object to be printed
     */
    private void draw(GameObject object) {
        Pos drawPosition = object.getPosition();
        drawPosition.addY(-object.getHeight() + tileSize);



        this.graphics.drawImage(
                object.textureSelector(new TextureSelector()).retrieveTexture(),
                object.getPosition().x()*getGameSize(),
                drawPosition.y()*getGameSize(),
                object.getWidth()*getGameSize(),
                object.getHeight()*getGameSize(),
                null
        );
    }

    private JFrame initializeFrame(Dimension dimension) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Colruyt Simulator");
        frame.getContentPane().add(this);
        frame.revalidate();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(dimension.getWidth(), dimension.getHeight());
        frame.setLocationRelativeTo(null);
        return frame;
    }


}
