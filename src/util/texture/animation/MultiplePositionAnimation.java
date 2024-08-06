package util.texture.animation;

import game.ScreenObject;
import util.positions.Pos;

import java.util.List;

public class MultiplePositionAnimation extends TextureAnimation {

    private final List<Pos> relativePositions;
    private final List<Integer> times;
    private int index = 0;
    private PositionAnimation currentPositionAnimation;

    public MultiplePositionAnimation(ScreenObject object, List<Pos> relativePositions, List<Integer> times) {
        super(object);
        this.relativePositions = relativePositions;
        this.times = times;
        this.currentPositionAnimation = new PositionAnimation(object, relativePositions.getFirst(), times.getFirst());
        this.currentPositionAnimation.animate();
    }

    @Override
    public void tick() {

        super.tick();

        // Go to next stage
        if(getTick()-1 == times.get(index)) {
            resetTick();
            super.tick();
            index = (index + 1) % relativePositions.size();
            this.currentPositionAnimation = new PositionAnimation(currentPositionAnimation.parent, relativePositions.get(index), times.get(index));
            this.currentPositionAnimation.animate();
        }

        this.currentPositionAnimation.tick();

    }

    @Override
    public ScreenObject getScreenObject() {
        return this.currentPositionAnimation.getScreenObject();
    }

}
