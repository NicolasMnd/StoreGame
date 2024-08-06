package util.texture.animation;

import game.TestTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.positions.Pos;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMultiplePositionAnimation {

    MultiplePositionAnimation animation;
    TestTile tile;

    @BeforeEach
    public void init() {
        tile = new TestTile(new Pos(0, 0));
        this.animation = new MultiplePositionAnimation(tile, List.of(new Pos(10, 10), new Pos(10, 20), new Pos(0, 20), new Pos(0, 10), new Pos(0, 0)),
                                                             List.of(10                   , 10                   , 10                  , 10                  , 10 ));
        animation.animate();
    }

    @Test
    public void testMultiplePosition_FirstMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 9);
        assertEquals(new Pos(9, 9), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_EndFirstMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 10);
        assertEquals(new Pos(10, 10), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_BeginSecondMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 10);
        new TestTextureAnimation().tick(() -> animation.tick(), 1);
        assertEquals(new Pos(10, 11), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_EndSecondMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 20);
        assertEquals(new Pos(10, 20), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_BeginThirdMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 21);
        assertEquals(new Pos(9, 20), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_EndThirdMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 30);
        assertEquals(new Pos(0, 20), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_BeginFourthMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 31);
        assertEquals(new Pos(0, 19), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_EndFourthMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 40);
        assertEquals(new Pos(0, 10), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_BeginFifthMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 41);
        assertEquals(new Pos(0, 9), animation.getScreenObject().getPosition());
    }

    @Test
    public void testMultiplePosition_EndFifthMovement() {
        new TestTextureAnimation().tick(() -> animation.tick(), 50);
        assertEquals(new Pos(0, 0), animation.getScreenObject().getPosition());
    }


}
