package render.game.load;

import game.state.GameState;
import game.tile.GameTile;
import game.tile.TileGround;
import game.tile.TileShelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import render.View;
import render.VirtualView;
import render.game.RenderStage;
import render.game.camera.Camera;
import util.Dimension;
import util.positions.Pos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRenderPlayer {

    GameState state;
    Dimension windowSize;
    Camera camera;
    View view;
    int tileSize = 32;

    @BeforeEach
    public void setUp() {
        windowSize = new Dimension(20*tileSize, 20*tileSize);
        view = new VirtualView(tileSize, windowSize);
        state = new GameState(32, windowSize, () -> 1d);
    }

    @Test
    public void testGetGameTiles_NoCandidates_TooFar() {
        state.getPlayer().updatePosition(new Pos(32*2, 32*2));
        camera = new Camera(state.getPlayer(), view);
        assertTrue(new RenderPlayer().getGameTiles(state).isEmpty());
    }

    @Test
    public void testGetGameTiles_AllShelves() {
        state.getPlayer().updatePosition(new Pos(32*5, 32*6));
        camera = new Camera(state.getPlayer(), view);
        assertTrue(new RenderPlayer().getGameTiles(state).stream().allMatch((tile) -> tile instanceof TileShelf));
    }

    @Test
    public void testGetGameTiles_UnderShelf() {
        state.getPlayer().updatePosition(new Pos(32*5, 32*6));
        camera = new Camera(state.getPlayer(), view);
        assertEquals(RenderStage.PLAYER_UNDER, new RenderPlayer().getRenderStage(state));
    }

    @Test
    public void testGetGameTiles_NotUnderShelf() {
        state.getPlayer().updatePosition(new Pos(32*2, 32*2));
        camera = new Camera(state.getPlayer(), view);
        assertEquals(RenderStage.PLAYER, new RenderPlayer().getRenderStage(state));
    }

    @Test
    public void testGetMax() {
        GameTile[][] tiles = new GameTile[][] {
                {new TileShelf(new Pos(1, 1), null)},
                {new TileGround(new Pos(1, 2))}
        };
        assertEquals(128, new RenderPlayer().getMax(tiles, GameTile::getHeight));
        assertEquals(32, new RenderPlayer().getMax(tiles, GameTile::getWidth));
    }

}
