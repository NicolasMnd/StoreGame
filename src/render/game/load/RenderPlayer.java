package render.game.load;

import game.state.GameState;
import game.tile.GameTile;
import render.game.renderorder.RenderStage;
import render.game.renderorder.RenderableGameObject;
import render.game.camera.Camera;
import util.positions.Hitbox;
import util.positions.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RenderPlayer implements IHasRenderables {

    @Override
    public List<RenderableGameObject> getRenderables(GameState state, Camera camera) {
        RenderableGameObject object = new RenderableGameObject(state.getPlayer(), camera.getRenderPosition(state.getTiles()[0].length, state.getTiles().length));
        object.setRenderOrder(getRenderStage(state));
        return List.of(
                object
        );
    }

    /**
     * Get the render stage of the player based upon the player's overlap with other tiles
     * @param state the current {@link GameState}
     * @return a render stage
     */
    RenderStage getRenderStage(GameState state) {
        // Now get a radius of game tiles using this width & height
        List<GameTile> candidates = getGameTiles(state);

        // With these tiles, we check overlap with the player using its full hitbox.
        List<Hitbox> hitboxes = candidates.stream().map(GameTile::getOverlapHitbox).toList();

        // Check if these hitbox overlap with the player.
        return hitboxes.stream().anyMatch(tile -> tile.hasOverlap(state.getPlayer().getHitbox())) ? RenderStage.PLAYER_UNDER : RenderStage.PLAYER;
    }

    /**
     * Returns a list of {@link GameTile} objects that possibly overlap the player.
     * @param state the current game state
     * @return a list of candidates
     */
    List<GameTile> getGameTiles(GameState state) {
        GameTile[][] tiles = state.getTiles();
        List<GameTile> candidates = new ArrayList<>();
        Pos playerPosition = state.getPlayer().getPosition();
        int startIndexX = Math.floorDiv(playerPosition.x(), 32);
        int startIndexY = Math.floorDiv(playerPosition.y(), 32);

        int maxWidth = 1 + getMax(tiles, GameTile::getWidth) / state.getTileSize();
        int maxHeight = 1 + getMax(tiles, GameTile::getHeight) / state.getTileSize();

        for(int i = -maxWidth; i <= maxWidth; i++)
            for(int j = -maxHeight; j <= maxHeight; j++)
                if(startIndexY+j > 0 && startIndexY+j < tiles.length && startIndexX+i > 0 && startIndexX+i < tiles[0].length
                   && tiles[startIndexY + j][startIndexX + i].getOverlapHitbox() != null) {
                    candidates.add(tiles[startIndexY + j][startIndexX + i]);
                }
        return candidates;
    }

    /**
     * Get the max height / width
     * @param tiles the tiles
     * @param function the function leading to height or width
     * @return the integer representing the max height or width
     */
    int getMax(GameTile[][] tiles, Function<GameTile, Integer> function) {
        int max = 0;
        for(GameTile[] row : tiles) {
            for(GameTile tile : row) {
                max = Math.max(max, function.apply(tile));
            }
        }
        return max;
    }

}
