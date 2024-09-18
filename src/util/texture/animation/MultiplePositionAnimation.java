package util.texture.animation;

import util.positions.Pos;

import java.util.List;
import java.util.function.Supplier;

public class MultiplePositionAnimation extends Animation {

    private final List<Pos> relativePositions;
    private final List<Integer> times;
    private int index = 0;
    private PositionAnimation currentPositionAnimation;

    public MultiplePositionAnimation(Supplier<Pos> relative, List<Pos> relativePositions, List<Integer> times, int id) {
        super(relative, id);
        this.relativePositions = relativePositions;
        this.times = times;
        this.currentPositionAnimation = new PositionAnimation(relative, new Pos(0,0), relativePositions.getFirst(), times.getFirst(), id);
    }

    @Override
    public void tick() {

        super.tick();

        this.currentPositionAnimation.tick();

        // Go to next stage
        if(getTick() == times.get(index)) {
            resetTick();
            index++;
            switchCurrentPosition();

            if(index == relativePositions.size())
                this.setDone();
        }

    }

    void switchCurrentPosition() {
        if(index < relativePositions.size())
            this.currentPositionAnimation = new PositionAnimation(getReference(), new Pos(0,0), relativePositions.get(index), times.get(index), getId());
        else
            this.currentPositionAnimation = new PositionAnimation(getReference(), new Pos(0, 0), new Pos(10, 10), 10, getId());
    }

    /**
     * @return the current {@link PositionAnimation} in the list.
     */
    public Pos getCurrentPosition() {
        return currentPositionAnimation.getAnimationPosition().add(sum());
    }

    /**
     * This class works with relative positions only. This means that sub-positions are not saved.
     * 0,0 -> 0,100 ; 0,100 -> 100,100 is saved as 0,100; 100, 0.
     * Meaning that the algorithm will first do 0,0->0,100 added to reference, then 0,0->100,0.
     * The previous 0,100 is not added. Here we make a sum of all previous positions.
     * @return a sum of all previous indices
     */
    Pos sum() {
        int i = 0;
        Pos sum = new Pos(0,0);
        while(i < index) {
            sum = sum.add(relativePositions.get(i));
            i++;
        }
        return sum;
    }

    @Override
    public boolean stopAnimation(AnimationStopCondition condition) {
        return false;
    }

}
