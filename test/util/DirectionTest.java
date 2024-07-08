package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Direction.*;


public class DirectionTest {

    @Test
    public void testTurnRight() {
        assertEquals(NORTH.turnRight(), EAST);
        assertEquals(EAST.turnRight(), SOUTH);
        assertEquals(SOUTH.turnRight(), WEST);
        assertEquals(WEST.turnRight(), NORTH);
        assertEquals(RIGHT.turnRight(), NORTH);
    }

    @Test
    public void testTurnLeft() {
        assertEquals(NORTH.turnLeft(), WEST);
        assertEquals(EAST.turnLeft(), NORTH);
        assertEquals(SOUTH.turnLeft(), EAST);
        assertEquals(WEST.turnLeft(), SOUTH);
        assertEquals(RIGHT.turnLeft(), NORTH);
    }


}
