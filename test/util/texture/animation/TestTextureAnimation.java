package util.texture.animation;

import game.ScreenObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTextureAnimation {

    TextureAnimationTest t;

    @BeforeEach
    public void init() {
        t = new TextureAnimationTest();
        t.animate();
    }

    @Test
    public void testInitializedCorrectly() {
        assertEquals(1, t.getCurrentStage());
        assertEquals(0, t.getTick());
        assertEquals(3, t.getMaxStages());
    }

    @Test
    public void testStageTransition_NotEnoughTicksForNext() {
        tick(t::tick, 5);
        assertEquals(1, t.getCurrentStage());
    }

    @Test
    public void testStageTransition_LastStage_To1() {
        tick(t::tick, 30+1);
        assertEquals(1, t.getCurrentStage());
    }

    public void tick(Runnable runnable, int amount) {
        for(int i = 0; i < amount; i++)
            runnable.run();
    }

    public static class TextureAnimationTest extends TextureAnimation {

        public TextureAnimationTest() {
            super(null);
            this.setStages(3);
            this.setTicksForStage(1, 10);
            this.setTicksForStage(2, 10);
            this.setTicksForStage(3, 10);
        }

        @Override
        public ScreenObject getScreenObject() {
            return null;
        }

    }

}
