package render;

import game.GameState;
import listeners.InputHandler;
import util.Dimension;
import util.Pos;

import java.awt.*;

public class VirtualView implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private Camera camera;
    private final int tileSize;
    private double gameSize = 1.0d;
    private final Dimension windowsSize;

    public VirtualView(int size, Dimension windowSize, Pos center) {
        this.tileSize = size;
        this.windowsSize = windowSize;
        this.camera = new Camera(center, this);
    }

    @Override
    public void increaseSize() {

    }

    @Override
    public void decreaseSize() {

    }

    @Override
    public void render(GameState state) {

    }

    @Override
    public void update(GameState state) {

    }

    @Override
    public void registerMouseHandler(InputHandler listener) {

    }

    @Override
    public void registerKeyHandler(InputHandler listener) {

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
}
