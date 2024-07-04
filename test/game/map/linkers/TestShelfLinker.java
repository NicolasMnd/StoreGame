package game.map.linkers;

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
                {new TestTile(), new TileShelf(new Pos(0, 1)), new TileShelf(new Pos(0, 2)), new TileShelf(new Pos(0, 3)), new TileShelf(new Pos(0, 4)), new TileShelf(new Pos(0, 5))},
                {new TestTile(), new TileShelf(new Pos(1, 1)), new TileShelf(new Pos(1, 2)), new TileShelf(new Pos(1, 3)), new TileShelf(new Pos(1, 4)), new TileShelf(new Pos(1, 5))},
                {new TestTile(), new TestTile(),                     new TestTile(),                     new TestTile(),                      new TestTile(),                     new TileShelf(new Pos(2, 5))},
                {new TestTile(), new TileShelf(new Pos(0, 1)), new TileShelf(new Pos(0, 2)), new TileShelf(new Pos(0, 3)), new TileShelf(new Pos(0, 4)), new TileShelf(new Pos(3, 5))},
        };
        for(GameTile[] row : tiles) {
            for (GameTile t : row)
                System.out.print(t + ", ");
            System.out.println();
        }
        ShelfLinker linker = new ShelfLinker(tiles);
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
