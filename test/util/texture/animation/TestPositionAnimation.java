package util.texture.animation;

import game.TestTile;
import org.junit.jupiter.api.Test;
import util.positions.Pos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPositionAnimation {

    @Test
    public void testPosition() {

        PositionAnimation animation = new PositionAnimation(new TestTile(new Pos(0, 0)), new Pos(100, 100), 100);
        animation.animate();
        new TestTextureAnimation().tick(animation::tick, 10);
        assertEquals(new Pos(10, 10), animation.getScreenObject().getPosition());

    }

}
