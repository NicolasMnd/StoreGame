package render.game;

import controller.input.InputHandler;
import game.GameObject;
import game.entity.types.PlayerEntity;
import game.state.GameState;
import listeners.ListenerRegistrator;
import render.View;
import render.game.camera.Camera;
import render.game.load.RenderObjects;
import render.game.renderorder.RenderStrategy;
import render.game.renderorder.RenderableScreenObject;
import util.Dimension;
import util.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JPanel implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    protected Camera camera;
    private final int tileSize;
    private double gameSize = 1.0d;
    private final Dimension windowsSize;
    private JFrame frame;
    private Logger logger = new Logger("");

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
        if(this.camera == null) {
            this.camera = new Camera(state.getPlayer(), this);
        }

        this.latestGameState = state;
        camera.updateMapSize(latestGameState.getMapDimensions());

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

    @Override
    public Camera getCamera() {
        return camera;
    }

    /**
     * Renders all elements of the {@link GameState}
     */
    private void renderGameState() {
        if(this.latestGameState == null) return;

        List<RenderableScreenObject> renderables = new RenderObjects().getRenderObjects(latestGameState, camera);

        for(int i = 0; i < renderables.size(); i++) {
            RenderableScreenObject object = renderables.get(i);
            draw(object);
        }

        new RenderStrategy().renderCoordinates((PlayerEntity) latestGameState.getPlayer()).render(graphics, gameSize, tileSize);

    }

    /**
     * Draws a given {@link GameObject}
     * @param object an object to be printed
     */
    private void draw(RenderableScreenObject object) {
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
