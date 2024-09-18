package util.texture.animation;

import game.ScreenObject;
import util.positions.Pos;

import java.util.function.Supplier;

/**
 * We define an animation as an object that tries to draw something relative to a
 * given location in a timely matter using ticks.
 */
public abstract class Animation {

    /**
     * The other {@link Pos} where the animation will be drawn relatively.
     */
    protected final Supplier<Pos> reference;
    private final int duplicationId;
    private int tick = 0;
    private boolean done = false;

    public Animation(Supplier<Pos> reference, int id) {
        this.reference = reference;
        this.duplicationId = id;
    }

    public final int getId() {
        return this.duplicationId;
    }

    public void tick() {
        tick++;
    }

    public final int getTick() {
        return this.tick;
    }

    public final void resetTick() {
        this.tick = 0;
    }

    public Supplier<Pos> getReference() {
        return this.reference;
    }

    public void setDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    /**
     * Each animation is required to implement a function in {@link AnimationStopCondition} to let the {@link controller.tasks.AnimationEventManager} determine if this
     * animation can be stopped early. This method will only determine which subtype method should be used in visitor style.
     * @param condition the animation stop condition
     * @return a boolean returned from {@link AnimationStopCondition} specific method for this subtype.
     */
    public abstract boolean stopAnimation(AnimationStopCondition condition);

    /**
     * @return a {@link ScreenObject} which is to be printed.
     */
    public ScreenObject getScreenObject() {
        return null;
    }

}
