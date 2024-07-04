package game.property;

import game.tile.TileShelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Pos;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyPeerTest {

    TileShelf[] shelvesHorizontal = new TileShelf[3];
    TileShelf[] shelvesVertical = new TileShelf[3];

    @BeforeEach
    public void init() {
        shelvesHorizontal[0] = new TileShelf(new Pos(0,0), null);
        shelvesHorizontal[1] = new TileShelf(new Pos(1,0), null);
        shelvesHorizontal[2] = new TileShelf(new Pos(2,0), null);
        ((PropertyPeer) shelvesHorizontal[0].getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelvesHorizontal[1]);
        ((PropertyPeer) shelvesHorizontal[1].getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelvesHorizontal[2]);

        shelvesVertical[0] = new TileShelf(new Pos(0,0), null);
        shelvesVertical[1] = new TileShelf(new Pos(0, 1), null);
        shelvesVertical[2] = new TileShelf(new Pos(0,2), null);
        ((PropertyPeer) shelvesVertical[0].getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelvesVertical[1]);
        ((PropertyPeer) shelvesVertical[1].getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelvesVertical[2]);
    }

    @Test
    public void testContainsPeer() {
        TileShelf s = new TileShelf(new Pos(0,0), null);
        TileShelf peer = new TileShelf(new Pos(0, 1), null);
        getProperty(s).addPeer(peer);
        assertTrue(getProperty(s).containsPeer(peer));
        assertTrue(getProperty(peer).containsPeer(s));
    }

    @Test
    public void testLink_AmountsCorrect() {
        TileShelf shelf1 = new TileShelf(new Pos(0,0), null);
        TileShelf shelf2 = new TileShelf(new Pos(1,0), null);
        TileShelf shelf3 = new TileShelf(new Pos(2,0), null);
        ((PropertyPeer) shelf1.getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelf2);
        ((PropertyPeer) shelf2.getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelf3);

        assertEquals(getProperty(shelf1).getPeers().size(), 2);
        assertEquals(getProperty(shelf2).getPeers().size(), 2);
        assertEquals(getProperty(shelf3).getPeers().size(), 2);
    }


    @Test
    public void testLink_ObjectsCorrect() {
        TileShelf shelf1 = new TileShelf(new Pos(0,0), null);
        TileShelf shelf2 = new TileShelf(new Pos(1,0), null);
        TileShelf shelf3 = new TileShelf(new Pos(2,0), null);
        ((PropertyPeer) shelf1.getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelf2);
        ((PropertyPeer) shelf2.getProperties().getProperty(PropertyType.SHELF_PEER))
                .addPeer(shelf3);

        assertEquals(getProperty(shelf1).getPeers(), Set.of(shelf2, shelf3));
        assertEquals(getProperty(shelf2).getPeers(), Set.of(shelf1, shelf3));
        assertEquals(getProperty(shelf3).getPeers(), Set.of(shelf1, shelf2));
    }

    @Test
    public void testPosition_Middle() {
        assertTrue(getProperty(shelvesHorizontal[1]).isMiddle());
        assertFalse(getProperty(shelvesHorizontal[0]).isMiddle());
        assertFalse(getProperty(shelvesHorizontal[2]).isMiddle());
    }

    @Test
    public void testPosition_Left() {
        assertFalse(getProperty(shelvesHorizontal[1]).isLeftSide());
        assertTrue(getProperty(shelvesHorizontal[0]).isLeftSide());
        assertFalse(getProperty(shelvesHorizontal[2]).isLeftSide());
    }

    @Test
    public void testPosition_Right() {
        assertFalse(getProperty(shelvesHorizontal[1]).isRightSide());
        assertFalse(getProperty(shelvesHorizontal[0]).isRightSide());
        assertTrue(getProperty(shelvesHorizontal[2]).isRightSide());
    }


    private PropertyPeer getProperty(TileShelf shelf) {
        return ((PropertyPeer) shelf.getProperties().getProperty(PropertyType.SHELF_PEER));
    }

}
