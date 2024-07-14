package render;

import game.GameObject;
import game.GameState;
import listeners.InputHandler;
import util.Dimension;
import util.Pos;

import javax.swing.*;
import java.awt.*;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JPanel implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private Camera camera;
    private final int tileSize;
    private double gameSize = 1.0d;
    private final Dimension windowsSize;
    private JFrame frame;

    public GameView(int size, Dimension windowSize, Pos center) {
        this.tileSize = size;
        this.frame = initializeFrame(windowSize);
        this.windowsSize = windowSize;
        this.camera = new Camera(center, this);
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
        repaint();
    }

    @Override
    public void update(GameState state) {
        this.latestGameState = state;

        // Update camera if necessary.
        if(!state.getCameraPosition().equals(camera.getCenter()))
            this.camera.updateCenter(state.getCameraPosition(), this);

    }

    @Override
    public void registerMouseHandler(InputHandler listener) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.addMouseWheelListener(listener);
    }

    @Override
    public void registerKeyHandler(InputHandler listener) {
        this.frame.addKeyListener(listener);
    }

    @Override
    public double getGameSize() {
        return this.gameSize;
    }

    @Override
    public int getTileSize() {
        return this.tileSize;
    }

    @Override
    public Dimension getDimension() {
        return this.windowsSize;
    }

    @Override
    public void increaseSize() {
        this.gameSize += 0.25d;
    }

    @Override
    public void decreaseSize() {
        this.gameSize -= 0.25d;
    }

    /**
     * Renders all elements of the {@link GameState}
     */
    private void renderGameState() {
        if(this.latestGameState == null) return;

        for(GameObject[] oArr : camera.getRenderTiles(latestGameState.getTiles(), gameSize))
            for(GameObject object : oArr)
                if(object != null) {
                    draw(object);
                }


        printCenter(latestGameState.getCameraPosition());
    }

    /**
     * Draws a given {@link GameObject}
     * @param object an object to be printed
     */
    private void draw(GameObject object) {
        object.getRenderStrategy(object).render(graphics, gameSize, tileSize);
    }

    private void printCenter(Pos pos) {
        this.graphics.drawOval(
                (int) (getWidth()/2),
                (int) (getHeight()/2),
                (int) (16),
                (int) (16)
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
