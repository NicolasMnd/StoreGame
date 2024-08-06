package util.texture.animation;

import game.ScreenObject;

import java.util.HashMap;
import java.util.Map;

public abstract class TextureAnimation {

    protected final ScreenObject parent;
    private int maxStages, currentStage = 1, tick;
    private Map<Integer, Integer> ticksForStage;
    boolean start = false;

    public TextureAnimation(ScreenObject start) {
        this.parent = start;
        this.ticksForStage = new HashMap<>();
    }

    /**
     * Ticks the animation
     */
    public void tick() {

        if(!start)
            return;

        for(Map.Entry<Integer, Integer> entry : ticksForStage.entrySet())
            if(entry.getValue().equals(tick)) {
                this.tick = 0;

                if(this.currentStage == maxStages)
                    start = false;

                this.currentStage = (this.currentStage + 1) % (maxStages);

            }

        tick++;

    }

    /**
     * Starts the animation
     */
    public void animate() {
        this.start = true;
    }

    /**
     * @return a {@link ScreenObject}
     */
    public abstract ScreenObject getScreenObject();

    protected void resetTick() {
        this.tick = 0;
    }

    protected int getTick() {
        return this.tick;
    }

    protected int getMaxStages() {
        return this.maxStages;
    }

    protected int getCurrentStage() {
        return this.currentStage;
    }

    protected void setStages(int stages) {
        this.maxStages = stages;
    }

    protected void setTicksForStage(int stage, int ticks) {
        this.ticksForStage.put(stage, ticks);
    }

}
