package render;

import game.GameObject;
import game.GameState;
import listeners.InputHandler;
import util.Dimension;
import util.Pos;
import util.texture.TextureSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * All graphic related items are processed here.
 */
public class GameView extends JPanel implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private Camera camera;
    private final int tileSize;
    private double gameSize = 1.0d;
    private JFrame frame;

    public GameView(int size, Dimension windowSize, Camera camera) {
        this.tileSize = size;
        this.frame = initializeFrame(windowSize);
        this.camera = camera;
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
        for(GameObject[] oArr : camera.getRenderTiles(latestGameState.getTiles(), gameSize))
            for(GameObject object : oArr)
                if(object != null) {
                    draw(object);
                }
        printCenter(latestGameState.getPlayerPosition());
    }

    /**
     * Draws a given {@link GameObject}
     * @param object an object to be printed
     */
    private void draw(GameObject object) {
        Pos drawPosition = object.getPosition();
        drawPosition.addY(-object.getHeight() + tileSize);

        BufferedImage im = object.textureSelector(new TextureSelector()).retrieveTexture();
        graphics.setStroke(new BasicStroke(5));

        if(im != null)
        this.graphics.drawImage(
                im,
                (int) (object.getPosition().x()*gameSize),
                (int) (drawPosition.y()*gameSize),
                (int) (object.getWidth()*gameSize),
                (int) (object.getHeight()*gameSize),
                null
        );
        else
            this.graphics.drawRect(
                    (int) (object.getPosition().x()*gameSize),
                    (int) (drawPosition.y()*gameSize),
                    (int) (object.getWidth()*gameSize),
                    (int) (object.getHeight()*gameSize)
            );
    }

    private void printCenter(Pos pos) {
        this.graphics.drawOval(
                (int) (getWidth()*gameSize/2),
                (int) (getHeight()*gameSize/2),
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
