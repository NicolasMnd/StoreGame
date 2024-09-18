package render.screen.effect.player;

import game.ScreenObject;
import util.positions.Pos;
import util.texture.animation.AnimationStopCondition;
import util.texture.animation.MultiplePositionAnimation;

import java.util.List;

public class PlayerArrowAnimation extends MultiplePositionAnimation {

    private static final int INTERVAL = 10, BOUNCE = 15;

    public PlayerArrowAnimation(ScreenObject player) {
        super(player::getPosition,
                List.of(
                    new Pos(0, -BOUNCE),
                    new Pos(0, BOUNCE),
                    new Pos(0, -BOUNCE),
                    new Pos(0, BOUNCE),
                    new Pos(0, -BOUNCE),
                    new Pos(0, BOUNCE)
                ),
                List.of(INTERVAL, INTERVAL, INTERVAL, INTERVAL, INTERVAL, INTERVAL),
                player.hashCode()
        );
    }

    @Override
    public ScreenObject getScreenObject() {
        PlayerArrow arrow = new PlayerArrow();
        arrow.updatePosition(getCurrentPosition().add(new Pos(0, -32)));
        return arrow;
    }

    @Override
    public boolean stopAnimation(AnimationStopCondition condition) {
        return condition.stopAnimation(this);
    }

}
