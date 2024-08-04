package render;

import controller.input.InputHandler;
import game.state.GameState;
import listeners.ListenerRegistrator;
import render.game.camera.Camera;
import util.Dimension;

import java.awt.*;

public class VirtualView implements View {

    private GameState latestGameState;
    private Graphics2D graphics;
    private Camera camera;
    private final int tileSize;
    private double gameSize = 1.0d;
    private final Dimension windowsSize;

    public VirtualView(int size, Dimension windowSize) {
        this.tileSize = getTileSize();
        this.windowsSize = windowSize;
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
    public void registerListener(ListenerRegistrator listener) {

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
