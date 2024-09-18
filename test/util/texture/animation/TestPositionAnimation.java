package util.texture.animation;

import org.junit.jupiter.api.Test;
import util.positions.Pos;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPositionAnimation {

    @Test
    public void testPosition_Middle() {

        PositionAnimation animation = new PositionAnimation(() -> new Pos(0, 0), new Pos(100, 100), new Pos(200, 200), 100, 0);
        tick(animation, 10);
        assertEquals(new Pos(110, 110), animation.getAnimationPosition());

    }

    @Test
    public void testPosition_Middle2() {

        PositionAnimation animation = new PositionAnimation(() -> new Pos(0, 0), new Pos(100, 100), new Pos(200, 200), 100, 0);
        tick(animation, 20);
        assertEquals(new Pos(120, 120), animation.getAnimationPosition());

    }

    @Test
    public void testPosition_End() {

        PositionAnimation animation = new PositionAnimation(() -> new Pos(0, 0), new Pos(100, 100), new Pos(200, 200), 100, 0);
        tick(animation, 100);
        assertEquals(new Pos(200, 200), animation.getAnimationPosition());

    }

    void tick(Animation animation, int amount) {
        for(int i = 0; i < amount; i++)
            animation.tick();
    }

    Supplier<Pos> getRelative() {
        return () -> new Pos(0, 0);
    }

}
