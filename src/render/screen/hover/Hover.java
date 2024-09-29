package render.screen.hover;

import game.state.GameState;
import game.tile.GameTile;
import render.game.camera.Camera;
import render.game.load.RenderPlayer;
import render.game.load.RenderTiles;
import render.game.renderorder.RenderableScreenObject;
import util.positions.Hitbox;
import util.positions.Pos;

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
     * Optimalization by limiting amount of tiles searched, brought down the execution time from 10-20ms to max 3ms
     * @param x the mouse X position
     * @param y the mouse Y position
     * @return a {@link Hitbox} of a {@link GameTile} that is rendered at the position of the mouse
     */
    public Hitbox getHoveredItem(int x, int y) {
        Hitbox selected = getHoveredEntity(x, y);

        if(selected != null)
            return selected;

        selected = getHoveredGameTile(x, y);

        return selected;
    }

    public Hitbox getHoveredEntity(int x, int y) {

        List<RenderableScreenObject> player = new RenderPlayer().getRenderables(state, camera);
        Pos playerPos = player.getFirst().getPosition();
        Hitbox playerHitbox = new Hitbox(playerPos, playerPos.add(new Pos(32, 32)));

        if(playerHitbox.isInHitbox(new Pos(x, y)))
            return playerHitbox;

        return null;

    }

    public Hitbox getHoveredGameTile(int x, int y) {

        List<RenderableScreenObject> tiles = new RenderTiles().getRenderables(state, camera);

        int yRow = Math.floorDiv(y, 32);
        int maxAmountWidth = (camera.getCameraSize().getWidth()/32) + 1 + (new RenderTiles().getSpacing()*2);

        for(int i = (yRow) * maxAmountWidth; i < (yRow*4) * maxAmountWidth; i++) {

            RenderableScreenObject tile = tiles.get(i);

            if(new Hitbox(tile.getPosition(), tile.getPosition().add(new Pos(32, 32))).isInHitbox(new Pos(x, y))) {

                return new Hitbox(tile.getPosition(), tile.getPosition().add(new Pos(32, 32)));

            }

        }

        return null;

    }


}
