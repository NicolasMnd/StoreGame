package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static util.Direction.*;

public class DirectionHelperTest {

    DirectionHelper helper;

    @BeforeEach
    public void init() {
        this.helper = new DirectionHelper();
    }

    @Test
    public void testIsSquare() {

        assertTrue(helper.isSquareDirection(new Pair<>(UP, RIGHT)));
        assertTrue(helper.isSquareDirection(new Pair<>(UP, LEFT)));
        assertTrue(helper.isSquareDirection(new Pair<>(DOWN, RIGHT)));
        assertTrue(helper.isSquareDirection(new Pair<>(DOWN, LEFT)));
        assertTrue(helper.isSquareDirection(new Pair<>(RIGHT, UP)));
        assertTrue(helper.isSquareDirection(new Pair<>(LEFT, UP)));
        assertTrue(helper.isSquareDirection(new Pair<>(RIGHT, DOWN)));
        assertTrue(helper.isSquareDirection(new Pair<>(LEFT, DOWN)));

        assertTrue(helper.isSquareDirection(new Pair<>(NORTH, EAST)));
        assertTrue(helper.isSquareDirection(new Pair<>(EAST, NORTH)));
        assertTrue(helper.isSquareDirection(new Pair<>(EAST, SOUTH)));
        assertTrue(helper.isSquareDirection(new Pair<>(SOUTH, EAST)));
        assertTrue(helper.isSquareDirection(new Pair<>(WEST, SOUTH)));
        assertTrue(helper.isSquareDirection(new Pair<>(SOUTH, WEST)));
        assertTrue(helper.isSquareDirection(new Pair<>(WEST, NORTH)));
        assertTrue(helper.isSquareDirection(new Pair<>(NORTH, WEST)));

    }

    @Test
    public void testIsNotSquare() {
        assertFalse(helper.isSquareDirection(new Pair<>(UP, UP)));
        assertFalse(helper.isSquareDirection(new Pair<>(DOWN, UP)));
        assertFalse(helper.isSquareDirection(new Pair<>(RIGHT, LEFT)));
        assertFalse(helper.isSquareDirection(new Pair<>(RIGHT, RIGHT)));
        assertFalse(helper.isSquareDirection(new Pair<>(LEFT, RIGHT)));
        assertFalse(helper.isSquareDirection(new Pair<>(LEFT, LEFT)));
        assertFalse(helper.isSquareDirection(new Pair<>(DOWN, DOWN)));
        assertFalse(helper.isSquareDirection(new Pair<>(UP, DOWN)));

        assertFalse(helper.isSquareDirection(new Pair<>(NORTH, NORTH)));
        assertFalse(helper.isSquareDirection(new Pair<>(SOUTH, NORTH)));
        assertFalse(helper.isSquareDirection(new Pair<>(NORTH, SOUTH)));
        assertFalse(helper.isSquareDirection(new Pair<>(SOUTH, SOUTH)));

        assertFalse(helper.isSquareDirection(new Pair<>(EAST, EAST)));
        assertFalse(helper.isSquareDirection(new Pair<>(WEST, EAST)));
        assertFalse(helper.isSquareDirection(new Pair<>(EAST, WEST)));
        assertFalse(helper.isSquareDirection(new Pair<>(WEST, WEST)));
    }

    @Test
    public void testFunctionCorrectness_LeftUp() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(UP, LEFT));
        assertEquals(function.getFirst().apply(1), 0);
        assertEquals(function.getSecond().apply(1), 0);
    }

    @Test
    public void testFunctionCorrectness_UpLeft() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(LEFT, UP));
        assertEquals(function.getFirst().apply(1), 0);
        assertEquals(function.getSecond().apply(1), 0);
    }

    @Test
    public void testFunctionCorrectness_RightUp() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(UP, RIGHT));
        assertEquals(function.getFirst().apply(1), 2);
        assertEquals(function.getSecond().apply(1), 0);
    }

    @Test
    public void testFunctionCorrectness_UpRight() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(RIGHT, UP));
        assertEquals(function.getFirst().apply(1), 2);
        assertEquals(function.getSecond().apply(1), 0);
    }

    @Test
    public void testFunctionCorrectness_RightDown() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(DOWN, RIGHT));
        assertEquals(function.getFirst().apply(1), 2);
        assertEquals(function.getSecond().apply(1), 2);
    }

    @Test
    public void testFunctionCorrectness_DownRight() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(RIGHT, DOWN));
        assertEquals(function.getFirst().apply(1), 2);
        assertEquals(function.getSecond().apply(1), 2);
    }

    @Test
    public void testFunctionCorrectness_LeftDown() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(DOWN, LEFT));
        assertEquals(function.getFirst().apply(1), 0);
        assertEquals(function.getSecond().apply(1), 2);
    }

    @Test
    public void testFunctionCorrectness_DownLeft() {
        Pair<Function<Integer, Integer>, Function<Integer, Integer>> function = helper.getFunction(new Pair<>(LEFT, DOWN));
        assertEquals(function.getFirst().apply(1), 0);
        assertEquals(function.getSecond().apply(1), 2);
    }

}
