package render.screen.hover;

import game.state.GameState;
import game.tile.GameTile;
import render.game.camera.Camera;
import render.game.load.RenderTiles;
import render.game.renderorder.RenderableScreenObject;

import java.util.List;

/**
 * Class that helps the {@link controller.GameFacade} to handle hovering of mouse
 */
public class Hover {

    private final GameState state;
    private final Camera camera;

    public Hover(GameState state, Camera camera) {
        this.state = state;
        this.camera = camera;
    }

    /**
     * @param x the mouse X position
     * @param y the mouse Y position
     * @return a {@link GameTile} that is rendered at the position of the mouse
     */
    public GameTile getHoveredGameTile(int x, int y) {

        List<RenderableScreenObject> object = new RenderTiles().getRenderables(state, camera);

        return null;

    }

}
