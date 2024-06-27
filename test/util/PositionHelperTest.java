package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Direction.*;

public class PositionHelperTest {

    PositionHelper helper;
    Pos reference;

    @BeforeEach
    public void init() {
        Pos reference = new Pos(0, 0);
        this.helper = new PositionHelper(reference);
    }

    @Test
    public void testDiagonal_Range0_False() {
        assertFalse(helper.isDiagonally(new Pos(0,0), new Pair<>(UP, LEFT), 1));
    }

    @Test
    public void testDiagonal_Range1_True_UpRight() {
        assertTrue(helper.isDiagonally(new Pos(1,-1), new Pair<>(UP, RIGHT), 1));
        assertTrue(helper.isDiagonally(new Pos(1,-1), new Pair<>(RIGHT, UP), 1));
    }

    @Test
    public void testDiagonal_Range1_True_UpLeft() {
        assertTrue(helper.isDiagonally(new Pos(-1,-1), new Pair<>(UP, LEFT), 1));
        assertTrue(helper.isDiagonally(new Pos(-1,-1), new Pair<>(LEFT, UP), 1));
    }

    @Test
    public void testDiagonal_Range1_True_DownRight() {
        assertTrue(helper.isDiagonally(new Pos(1,1), new Pair<>(DOWN, RIGHT), 1));
        assertTrue(helper.isDiagonally(new Pos(1,1), new Pair<>(RIGHT, DOWN), 1));
    }

    @Test
    public void testDiagonal_Range1_True_DownLeft() {
        assertTrue(helper.isDiagonally(new Pos(-1,1), new Pair<>(DOWN, LEFT), 1));
        assertTrue(helper.isDiagonally(new Pos(-1,1), new Pair<>(LEFT, DOWN), 1));
    }

    @Test
    public void testDiagonal_Range1_False_Up() {
        assertFalse(helper.isDiagonally(new Pos(0,-1), new Pair<>(UP, RIGHT), 1));
    }

    @Test
    public void testDiagonal_Range1_False_Down() {
        assertFalse(helper.isDiagonally(new Pos(0,1), new Pair<>(UP, RIGHT), 1));
    }


    @Test
    public void testDiagonal_Range1_False_Right() {
        assertFalse(helper.isDiagonally(new Pos(1,0), new Pair<>(UP, RIGHT), 1));
    }

    @Test
    public void testDiagonal_Range1_False_Left() {
        assertFalse(helper.isDiagonally(new Pos(-1,0), new Pair<>(UP, RIGHT), 1));
    }

    @Test
    public void testDiagonal_Range2_True_UpRight() {
        assertTrue(helper.isDiagonally(new Pos(2,-2), new Pair<>(UP, RIGHT), 2));
        assertTrue(helper.isDiagonally(new Pos(2,-2), new Pair<>(RIGHT, UP), 2));
    }

    @Test
    public void testDiagonal_Range2_True_UpLeft() {
        assertTrue(helper.isDiagonally(new Pos(-2,-2), new Pair<>(UP, LEFT), 2));
        assertTrue(helper.isDiagonally(new Pos(-2,-2), new Pair<>(LEFT, UP), 2));
    }

    @Test
    public void testDiagonal_Range2_True_DownRight() {
        assertTrue(helper.isDiagonally(new Pos(2,2), new Pair<>(DOWN, RIGHT), 2));
        assertTrue(helper.isDiagonally(new Pos(2,2), new Pair<>(RIGHT, DOWN), 2));
    }

    @Test
    public void testDiagonal_Range2_True_DownLeft() {
        assertTrue(helper.isDiagonally(new Pos(-2,2), new Pair<>(DOWN, LEFT), 2));
        assertTrue(helper.isDiagonally(new Pos(-2,2), new Pair<>(LEFT, DOWN), 2));
    }

    @Test
    public void testConnected_Range0_False() {
        assertFalse(helper.isConnected(new Pos(0,0), NORTH));
        assertFalse(helper.isConnected(new Pos(0,0), SOUTH));
        assertFalse(helper.isConnected(new Pos(0,0), WEST));
        assertFalse(helper.isConnected(new Pos(0,0), UP));
        assertFalse(helper.isConnected(new Pos(0,0), DOWN));
    }

    @Test
    public void testConnected_Range1_True() {
        assertTrue(helper.isConnected(new Pos(1, 0), null));
        assertTrue(helper.isConnected(new Pos(0, 1), null));
        assertTrue(helper.isConnected(new Pos(0, -1), null));
        assertTrue(helper.isConnected(new Pos(-1, 0), null));
    }

    @Test
    public void testConnected_Range1_True_Specific() {
        assertTrue(helper.isConnected(new Pos(1, 0), RIGHT));
        assertTrue(helper.isConnected(new Pos(0, 1), DOWN));
        assertTrue(helper.isConnected(new Pos(0, -1), UP));
        assertTrue(helper.isConnected(new Pos(-1, 0), LEFT));
    }

    @Test
    public void testConnected_Range1_False_Specific() {
        assertFalse(helper.isConnected(new Pos(1, 0), DOWN));
        assertFalse(helper.isConnected(new Pos(0, 1), RIGHT));
        assertFalse(helper.isConnected(new Pos(0, -1), LEFT));
        assertFalse(helper.isConnected(new Pos(-1, 0), UP));
    }


    @Test
    public void testConnected_Range2_False() {
        assertFalse(helper.isConnected(new Pos(2, 0), null));
        assertFalse(helper.isConnected(new Pos(1, 2), RIGHT));
        assertFalse(helper.isConnected(new Pos(1, 0), LEFT));
    }

    @Test
    public void isConnected1D_True() {
        assertTrue(helper.isConnected1D(new Pos(1,0), 1));
        assertTrue(helper.isConnected1D(new Pos(2,0), 2));
        assertTrue(helper.isConnected1D(new Pos(1,0), 2));
    }

    @Test
    public void isConnected1D_False() {
        assertFalse(helper.isConnected1D(new Pos(2,0), 1));
        assertFalse(helper.isConnected1D(new Pos(3,0), 2));
        assertFalse(helper.isConnected1D(new Pos(1,1), 5));
    }

}
