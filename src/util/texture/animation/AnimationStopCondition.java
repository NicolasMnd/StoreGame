package util.texture.animation;

import game.state.GameState;
import render.game.load.RenderPlayer;
import render.game.renderorder.RenderStage;
import render.screen.effect.player.PlayerArrowAnimation;

/**
    * Class is responsible to stop currently ticking {@link Animation} objects.
 */
public class AnimationStopCondition {

    private final GameState state;

    public AnimationStopCondition(GameState state) {
        this.state = state;
    }

    public boolean checkAnimation(Animation animation) {
        return animation.stopAnimation(this);
    }

    public boolean stopAnimation(PlayerArrowAnimation animation) {
        return new RenderPlayer().getRenderStage(state).getStage() == RenderStage.PLAYER.getStage();
    }

}
