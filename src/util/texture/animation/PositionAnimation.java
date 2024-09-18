package util.texture.animation;

import util.positions.Pos;

import java.util.function.Supplier;

public class PositionAnimation extends Animation {

    private double dX, dY;
    private final Pos startPos, endPos;
    private int time;

    public PositionAnimation(Supplier<Pos> relative, Pos startPosition, Pos endPosition, int time, int id) {
        super(relative, id);
        this.startPos = startPosition;
        this.endPos = endPosition;
        this.time = time;
        setupStages(startPosition, endPosition, time);
    }

    public final Pos getAnimationPosition() {
        return getReference().get().add(startPos).add(new Pos((int) (dX * getTick()), (int) (dY * getTick())));
    }

    @Override
    public void tick() {
        if(getTick() <= time) {
            super.tick();
        }
    }

    private void setupStages(Pos currentObjectPosition, Pos targetObjectPosition, int time) {
        this.dX = (double) (targetObjectPosition.x() - currentObjectPosition.x()) / time;
        this.dY = (double) (targetObjectPosition.y() - currentObjectPosition.y()) / time;
        System.out.println("DX: " + dX + " , dY:" + dY);
    }

    @Override
    public boolean stopAnimation(AnimationStopCondition condition) {
        return false;
    }

}
