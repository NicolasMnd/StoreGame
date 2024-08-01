package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.positions.Pos;

import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {

    TestTile tile;

    @BeforeEach
    public void init() {
        this.tile = new TestTile(new Pos(10, 20));
    }

    @Test
    public void testWidth() {
        tile.setWidth(10);
        assertEquals(tile.getWidth(), 10);
    }

    @Test
    public void testHeight() {
        tile.setHeight(20);
        assertEquals(tile.getHeight(), 20);
    }

    @Test
    public void testUpdatePosition() {
        assertEquals(tile.getPosition(), new Pos(10, 20));
        tile.updatePosition(new Pos(20, 20));
        assertEquals(tile.getPosition(), new Pos(10,20));
    }

}
