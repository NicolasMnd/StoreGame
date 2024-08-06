package render.screen.effect.player;

import game.TestTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.positions.Pos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerArrowAnimationTest {

    Pos location;
    TestTile arrow;
    PlayerArrowAnimation animation;

    @BeforeEach
    public void init() {
        location = new Pos(10, 10);
        arrow = new TestTile(location);
        animation = new PlayerArrowAnimation(arrow);
    }

    @Test
    public void testInitialisationPosition_NoTicks() {
        assertEquals(location, animation.getScreenObject().getPosition());
    }

    @Test
    public void test200Ticks_NoMovement() {
        tick(() -> animation.tick(), 200);
        assertEquals(location, animation.getScreenObject().getPosition());
    }

    @Test
    public void test301Ticks_3PixelsDown() {
        tick(() -> animation.tick(), 301);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -3)));
    }

    @Test
    public void test401Ticks_3PixelsDown() {
        tick(() -> animation.tick(), 401);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -3)));
    }

    @Test
    public void test601Ticks_6PixelsDown() {
        tick(() -> animation.tick(), 601);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -6)));
    }

    @Test
    public void test1501Ticks_12PixelsDown() {
        tick(() -> animation.tick(), 1501);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -12)));
    }

    @Test
    public void test1601Ticks_12PixelsDown() {
        tick(() -> animation.tick(), 1601);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -12)));
    }

    @Test
    public void test1801Ticks_12PixelsDown_ButINSecondIf() {
        tick(() -> animation.tick(), 1801);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -12)));
    }

    @Test
    public void test2101Ticks_9PixelsDown() {
        tick(() -> animation.tick(), 2101);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -9)));
    }

    @Test
    public void test2401Ticks_6PixelsDown() {
        tick(() -> animation.tick(), 2401);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -6)));
    }

    @Test
    public void test2701Ticks_3PixelsDown() {
        tick(() -> animation.tick(), 2701);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, -3)));
    }

    @Test
    public void test3001Ticks_0PixelsDown() {
        tick(() -> animation.tick(), 3001);
        assertEquals(location, animation.getScreenObject().getPosition().add(new Pos(0, 0)));
    }


    private void tick(Runnable runnable, int amount) {
        for(int i = 0; i < amount; i++)
            runnable.run();
    }

}
