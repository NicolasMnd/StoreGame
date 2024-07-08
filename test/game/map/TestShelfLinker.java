package game.map;

import game.TestTile;
import game.property.PropertyPeer;
import game.property.PropertyType;
import game.tile.GameTile;
import game.tile.TileShelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Pos;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestShelfLinker {

    GameTile[][] tiles;

    @BeforeEach
    public void init() {
        tiles = new GameTile[][] {
                {new TestTile(), new TileShelf(new Pos(1, 0), null), new TileShelf(new Pos(2, 0), null), new TileShelf(new Pos(3, 0), null), new TileShelf(new Pos(4, 0), null), new TileShelf(new Pos(5, 0), null)},
                {new TestTile(), new TileShelf(new Pos(1, 1), null), new TileShelf(new Pos(2, 1), null), new TileShelf(new Pos(3, 1), null), new TileShelf(new Pos(4, 1), null), new TileShelf(new Pos(5, 1), null)},
                {new TestTile(), new TestTile(),                                         new TestTile(),                                        new TestTile(),                                         new TestTile(),                                         new TileShelf(new Pos(5, 2), null)},
                {new TestTile(), new TileShelf(new Pos(1, 3), null), new TileShelf(new Pos(2, 3), null), new TileShelf(new Pos(3, 3), null), new TileShelf(new Pos(4, 3), null), new TileShelf(new Pos(5, 3), null)},
        };

        ShelfLinker linker = new ShelfLinker(tiles, 1);
        linker.findRelations();
    }

    @Test
    public void testLinksCorrect_1() {
        assertNotNull(getProperty(tiles[0][1]));
        assertEquals(getProperty(tiles[0][1]).getPeers().size(), 3);
        assertEquals(getProperty(tiles[0][1]).getPeers(), Set.of(tiles[0][2], tiles[0][3], tiles[0][4]));
    }

    @Test
    public void testLinksCorrect_2() {
        assertNotNull(getProperty(tiles[1][1]));
        assertEquals(getProperty(tiles[1][1]).getPeers().size(), 3);
        assertEquals(getProperty(tiles[1][1]).getPeers(), Set.of(tiles[1][2], tiles[1][3], tiles[1][4]));
    }

    @Test
    public void testLinksCorrect_3() {
        assertNotNull(getProperty(tiles[3][1]));
        assertEquals(getProperty(tiles[3][1]).getPeers().size(), 3);
        assertEquals(getProperty(tiles[3][1]).getPeers(), Set.of(tiles[3][2], tiles[3][3], tiles[3][4]));
    }

    @Test
    public void testLinksCorrect_4() {
        assertNotNull(getProperty(tiles[0][5]));
        assertEquals(getProperty(tiles[1][5]).getPeers().size(), 3);
        assertEquals(getProperty(tiles[0][5]).getPeers(), Set.of(tiles[1][5], tiles[2][5], tiles[3][5]));
    }

    private PropertyPeer getProperty(GameTile tile) {
        if(tile instanceof TileShelf shelf)
            return ((PropertyPeer) shelf.getProperties().getProperty(PropertyType.SHELF_PEER));
        return null;
    }

}
