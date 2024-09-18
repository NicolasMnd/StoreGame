package util.texture.animation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.positions.Pos;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMultiplePositionAnimation extends TestPositionAnimation {

    MultiplePositionAnimation multiplePositionAnimation;
    MultiplePositionAnimation movingAnimation;
    Pos movingEntity;

    @BeforeEach
    public void init() {

        multiplePositionAnimation = new MultiplePositionAnimation(
                getRelative(),
                List.of(
                        new Pos(0, 100),
                        new Pos(100, 0),
                        new Pos(0, -100),
                        new Pos(-100, 0)
                ),
                List.of(
                        100, 100, 100, 100
                ),
                100
        );

        movingAnimation = new MultiplePositionAnimation(
                () -> movingEntity,
                List.of(
                        new Pos(0, 100),
                        new Pos(100, 0),
                        new Pos(0, -100),
                        new Pos(-100, 0)
                ),
                List.of(
                        100, 100, 100, 100
                ),
                100
        );

    }

    @Test
    public void testSum2() {
        tick(multiplePositionAnimation, 101);
        assertEquals(new Pos(0, 100), multiplePositionAnimation.sum());
    }

    @Test
    public void testSum3() {
        tick(multiplePositionAnimation, 200);
        assertEquals(new Pos(100, 100), multiplePositionAnimation.sum());
    }

    @Test
    public void testMovementUp_50() {
        tick(multiplePositionAnimation, 50);
        assertEquals(new Pos(0, 50), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementUp_100() {
        tick(multiplePositionAnimation, 100);
        assertEquals(new Pos(0, 100), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementRight_50() {
        tick(multiplePositionAnimation, 150);
        assertEquals(new Pos(50, 100), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementRight_100() {
        tick(multiplePositionAnimation, 200);
        assertEquals(new Pos(100, 100), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementRightDown_50() {
        tick(multiplePositionAnimation, 250);
        assertEquals(new Pos(100, 50), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementRightDown_100() {
        tick(multiplePositionAnimation, 300);
        assertEquals(new Pos(100, 0), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementLeftDown_50() {
        tick(multiplePositionAnimation, 350);
        assertEquals(new Pos(50, 0), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovementLeftDown_100() {
        tick(multiplePositionAnimation, 400);
        assertEquals(new Pos(0, 0), multiplePositionAnimation.getCurrentPosition());
    }

    @Test
    public void testMovingAnimation_Up() {
        tick(movingAnimation, 40);
        this.movingEntity = new Pos(10, 11);
        assertEquals(this.movingEntity.add(new Pos(0, 40)), movingAnimation.getCurrentPosition());
    }

}
