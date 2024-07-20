package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.hitbox.Hitbox;

import static org.junit.jupiter.api.Assertions.*;

public class TestHitbox {

    Hitbox h0;

    @BeforeEach
    public void init() {
        h0 = new Hitbox(new Pos(0, 0), new Pos(5, 5));
    }

    @Test
    public void testCenterConstructor() {
        assertEquals(
                new Hitbox(new Pos(-2, -2), new Pos(2, 2)),
                new Hitbox(new Pos(0, 0), 2, 2)
        );
    }

    @Test
    public void testCenterConstructor2() {
        assertEquals(
                new Hitbox(new Pos(-2, -3), new Pos(2, 3)),
                new Hitbox(new Pos(0, 0), 2, 3)
        );
    }

    @Test
    public void testOutside_1() {
        Hitbox h1 = new Hitbox(new Pos(6, 6), new Pos(10, 10));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_2() {
        Hitbox h1 = new Hitbox(new Pos(-1, -1), new Pos(-5, -5));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_3() {
        Hitbox h1 = new Hitbox(new Pos(0, 6), new Pos(0, 10));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_4() {
        Hitbox h1 = new Hitbox(new Pos(6, 0), new Pos(10, 0));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_5() {
        Hitbox h1 = new Hitbox(new Pos(-10, 0), new Pos(-6, 0));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_6() {
        Hitbox h1 = new Hitbox(new Pos(0, -10), new Pos(0, -5));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_7() {
        Hitbox h1 = new Hitbox(new Pos(5, -10), new Pos(10, -5));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOutside_8() {
        Hitbox h1 = new Hitbox(new Pos(-10, -10), new Pos(-6, -5));
        assertFalse(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_1() {
        Hitbox h1 = new Hitbox(new Pos(-10, -10), new Pos(1, 1));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_2() {
        Hitbox h1 = new Hitbox(new Pos(1, -10), new Pos(4, 1));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_3() {
        Hitbox h1 = new Hitbox(new Pos(4, -10), new Pos(10, 1));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_4() {
        Hitbox h1 = new Hitbox(new Pos(-5, 1), new Pos(1, 4));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_5() {
        Hitbox h1 = new Hitbox(new Pos(-10, 4), new Pos(4, 10));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_6() {
        Hitbox h1 = new Hitbox(new Pos(1, 10), new Pos(4, 1));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_7() {
        Hitbox h1 = new Hitbox(new Pos(4, 4), new Pos(10, 10));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_8() {
        Hitbox h1 = new Hitbox(new Pos(4, 1), new Pos(10, 4));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testOverlap_Inside() {
        Hitbox h1 = new Hitbox(new Pos(1, 1), new Pos(4, 4));
        assertTrue(h0.hasOverlap(h1));
    }

    @Test
    public void testWidth() {
        assertEquals(h0.getWidth(), 5);
        assertEquals(h0.getHeight(), 5);
    }

    @Test
    public void testGetCenter_1() {
        assertEquals(h0.getCenterPos(), new Pos(3, 3));
    }

    @Test
    public void testGetCenter_2() {
        h0 = new Hitbox(new Pos(0, 0), new Pos(10, 10));
        assertEquals(h0.getCenterPos(), new Pos(5, 5));
    }

    @Test
    public void testShifted() {
        h0 = new Hitbox(new Pos(0, 0), new Pos(10, 10));
        Pos upperLeft2 = new Pos(24,24);
        assertEquals(h0.calculateRelativeHitbox(upperLeft2), new Hitbox(new Pos(24, 24), new Pos(34, 34)));
    }

}
