package render.screen.effect.player;

import game.ScreenObject;
import util.positions.Pos;
import util.texture.animation.MultiplePositionAnimation;

import java.util.List;

public class PlayerArrowAnimation extends MultiplePositionAnimation {

    public PlayerArrowAnimation(ScreenObject object) {
        super(object, List.of(
                object.getPosition(),
                object.getPosition().add(new Pos(0, 3)),
                object.getPosition().add(new Pos(0, 6)),
                object.getPosition().add(new Pos(0, 9)),
                object.getPosition().add(new Pos(0, 6)),
                object.getPosition().add(new Pos(0, 3)),
                object.getPosition()
            ),
                List.of(300, 300, 300, 300, 300, 300, 300)
        );
    }

}
