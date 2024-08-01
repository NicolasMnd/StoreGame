package render;

import controller.input.InputHandler;
import game.GameObject;
import game.GameState;
import util.Dimension;
import util.positions.Pos;

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
            this.camera = new Camera(state.getCameraPosition(), this);

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

    /**
     * Renders all elements of the {@link GameState}
     */
    private void renderGameState() {
        if(this.latestGameState == null) return;

        for(GameObject[] oArr : camera.getRenderTiles(latestGameState.getTiles(), gameSize))
            for(GameObject object : oArr)
                if(object != null)
                    draw(object);

        draw(new RenderableGameObject(latestGameState.getPlayer(), new Pos(10+getWidth()/2, 14+getHeight()/2)));

        // DBUG
        // draw(latestGameState.getPlayer());
        //new RenderStrategy().renderHitbox(latestGameState.getPlayer().getHitbox()).render(graphics, getGameSize(), getTileSize());

        graphics.setStroke(new BasicStroke(1f));
        graphics.setColor(Color.RED);
        this.graphics.drawString(latestGameState.getPlayer().getPosition().getFormat(), 10, 10);
        this.graphics.drawString(latestGameState.getPlayer().getHitbox().getPrint(), 10, 20);

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
