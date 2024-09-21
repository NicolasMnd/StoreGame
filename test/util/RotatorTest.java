package util;

import org.junit.jupiter.api.Test;
import util.positions.Pos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RotatorTest {

    @Test
    public void testRotateLeft_3x3() {

        Integer[][] map = new Integer[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        Integer[][] out = new Integer[][] {
                {7,4,1},
                {8,5,2},
                {9,6,3}
        };

        assertTrue(
            new ArrayTestHelper<Integer>().arraysEquals2D(
                    new Rotator<Integer>(Integer.class).rotateLeft(map, new Dimension(3,3)),
                    out
            )
        );

    }

    @Test
    public void testRotateLeft_3x2() {

        Integer[][] map = new Integer[][] {
                {1,2},
                {4,5},
                {7,8}
        };

        Integer[][] out = new Integer[][] {
                {7,4,1},
                {8,5,2}
        };

        assertTrue(
                new ArrayTestHelper<Integer>().arraysEquals2D(
                        new Rotator<Integer>(Integer.class).rotateLeft(map, new Dimension(3,2)),
                        out
                )
        );

    }

    @Test
    public void testRotateRight_3x3() {
        Integer[][] map = new Integer[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        Integer[][] out = new Integer[][] {
                {3,6,9},
                {2,5,8},
                {1,4,7}
        };

        assertTrue(
                new ArrayTestHelper<Integer>().arraysEquals2D(
                        new Rotator<Integer>(Integer.class).rotateRight(map, new Dimension(3,3)),
                        out
                )
        );

    }

    @Test
    public void testRotateRight_3x2() {

        Integer[][] map = new Integer[][] {
                {1,2},
                {4,5},
                {7,8}
        };

        Integer[][] out = new Integer[][] {
                {2,5,8},
                {1,4,7}
        };

        assertTrue(
                new ArrayTestHelper<Integer>().arraysEquals2D(
                        new Rotator<Integer>(Integer.class).rotateRight(map, new Dimension(3,2)),
                        out
                )
        );

    }

    @Test
    public void testRotateRight_Twice_3x3() {
        Integer[][] map = new Integer[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        Integer[][] out = new Integer[][] {
                {9,8,7},
                {6,5,4},
                {3,2,1}
        };

        Rotator<Integer> rotator = new Rotator<Integer>(Integer.class);

        Integer[][] rotatedRight = rotator.rotateRight(map, new Dimension(3,3));
        Integer[][] rotatedRightTwice = rotator.rotateRight(rotatedRight, new Dimension(3,3));

        assertTrue(
                new ArrayTestHelper<Integer>().arraysEquals2D(
                        rotatedRightTwice,
                        out
                )
        );

    }

    @Test
    public void testRotateRightTwice_RotateBackNormal_3x3() {
        Integer[][] map = new Integer[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        Integer[][] out = new Integer[][] {
                {9,8,7},
                {6,5,4},
                {3,2,1}
        };

        Rotator<Integer> rotator = new Rotator<Integer>(Integer.class);

        Integer[][] rotatedRight = rotator.rotateRight(map, new Dimension(3,3));
        Integer[][] rotatedRightTwice = rotator.rotateRight(rotatedRight, new Dimension(3,3));
        Integer[][] rotatedLeftThrice = rotator.rotateLeft(rotatedRightTwice, new Dimension(3,3));
        Integer[][] original = rotator.rotateLeft(rotatedLeftThrice, new Dimension(3,3));

        assertTrue(
                new ArrayTestHelper<Integer>().arraysEquals2D(
                        original,
                        map
                )
        );

    }

    @Test
    public void testRotateRight_AllRound_3x3() {
        Integer[][] map = new Integer[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        Rotator<Integer> rotator = new Rotator<Integer>(Integer.class);

        Integer[][] rotatedRight = rotator.rotateRight(map, new Dimension(3,3));
        Integer[][] rotatedRightTwice = rotator.rotateRight(rotatedRight, new Dimension(3,3));
        Integer[][] rotatedRightThrice = rotator.rotateRight(rotatedRightTwice, new Dimension(3,3));
        Integer[][] round = rotator.rotateRight(rotatedRightThrice, new Dimension(3,3));

        assertTrue(
                new ArrayTestHelper<Integer>().arraysEquals2D(
                        round,
                        map
                )
        );

    }

    @Test
    public void testRotatePos_Left() {
        Rotator<Integer> rotator = new Rotator<>(Integer.class);
        assertEquals(new Pos(2, 1), rotator.rotatePos(new Pos(1,3), new Dimension(4,6), Direction.LEFT));
    }

    @Test
    public void testRotatePos_Right() {
        Rotator<Integer> rotator = new Rotator<>(Integer.class);
        assertEquals(new Pos(2, 1), rotator.rotatePos(new Pos(4,2), new Dimension(4,6), Direction.RIGHT));
    }

}
