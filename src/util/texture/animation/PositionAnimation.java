package util.texture.animation;

import game.ScreenObject;
import util.positions.Pos;

public class PositionAnimation extends TextureAnimation {

    private final Pos startPosition, targetPosition;
    private double dX, dY;

    public PositionAnimation(ScreenObject object, Pos target, int time) {
        super(object);
        this.startPosition = object.getPosition().clone();
        this.targetPosition = target;
        setupStages(time);
    }

    @Override
    public void tick() {
        super.tick();
        update();
    }

    private void setupStages(int time) {
        this.dX = (double) (targetPosition.x() - startPosition.x()) / time;
        this.dY = (double) (targetPosition.y() - startPosition.y()) / time;
    }

    private void update() {
        Pos pos = startPosition.add(new Pos((int )(dX * getTick()), (int) (dY * getTick())));
        parent.updatePosition(pos);
    }

    @Override
    public ScreenObject getScreenObject() {
        return parent;
    }
}
