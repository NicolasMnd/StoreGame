package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosTest {

    Pos pos;

    @BeforeEach
    public void init() {
        this.pos = new Pos(0,1);
    }

    @Test
    public void testVariables() {
        assertEquals(pos.x(), 0);
        assertEquals(pos.y(), 1);
    }

    @Test
    public void testMoveDown() {
        this.pos.moveDown();
        assertEquals(pos.x(), 0);
        assertEquals(pos.y(), 2);
    }

    @Test
    public void testMoveUp() {
        this.pos.moveUp();
        assertEquals(pos.x(), 0);
        assertEquals(pos.y(), 0);
    }

    @Test
    public void testMoveRight() {
        this.pos.moveRight();
        assertEquals(pos.x(), 1);
        assertEquals(pos.y(), 1);
    }

    @Test
    public void testMoveLeft() {
        this.pos.moveLeft();
        assertEquals(pos.x(), -1);
        assertEquals(pos.y(), 1);
    }

    @Test
    public void testEquals() {
        assertEquals(new Pos(1, 1), new Pos(1,1));
    }

}
