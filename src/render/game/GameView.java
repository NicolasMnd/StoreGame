package render.game;

import controller.input.InputHandler;
import game.GameObject;
import game.state.GameState;
import listeners.ListenerRegistrator;
import render.View;
import render.game.camera.Camera;
import render.game.load.RenderObjects;
import render.game.load.RenderPlayer;
import util.Dimension;

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

    public GameView(int size, Dimension windowSize) {
        this.tileSize = size;
        this.frame = initializeFrame(windowSize);
        this.windowsSize = windowSize;
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
        if(this.camera == null)
            this.camera = new Camera(state.getPlayer(), this);

        this.latestGameState = state;

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
    public void registerListener(ListenerRegistrator listener) {
        listener.registerListener(frame);
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

    /**
     * Renders all elements of the {@link GameState}
     */
    private void renderGameState() {
        if(this.latestGameState == null) return;

        for(GameObject object : new RenderObjects().getRenderObjects(latestGameState, camera))
            draw(object);

        // DBUG
        // draw(latestGameState.getPlayer());
        //new RenderStrategy().renderHitbox(latestGameState.getPlayer().getHitbox()).render(graphics, getGameSize(), getTileSize());

        graphics.setStroke(new BasicStroke(1f));
        graphics.setColor(Color.RED);
        this.graphics.drawString(latestGameState.getPlayer().getPosition().getFormat(), 10, 10);
        this.graphics.drawString(latestGameState.getPlayer().getHitbox().getPrint(), 10, 20);
        new RenderStrategy().hitboxRenderer(new RenderPlayer().getRenderables(latestGameState, camera).getFirst()).render(graphics, 1d, 32);

    }

    /**
     * Draws a given {@link GameObject}
     * @param object an object to be printed
     */
    private void draw(GameObject object) {
        object.getRenderStrategy(object).render(graphics, gameSize, tileSize);
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
