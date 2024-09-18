package controller.tasks;

import game.state.GameState;
import listeners.IAnimationListener;
import util.texture.animation.Animation;
import util.texture.animation.AnimationStopCondition;

import java.util.ArrayList;
import java.util.List;

public class AnimationEventManager implements Event {

    private List<Animation> animations;

    public AnimationEventManager() {
        animations = new ArrayList<>();
    }

    @Override
    public void call(GameState state) {
        for(Animation animation : animations)
            animation.tick();
        trim(state);
    }

    /**
     * Remove animations that have finished to keep the list clean.
     */
    private void trim(GameState state) {
        List<Animation> animations = new ArrayList<>();
        AnimationStopCondition stopCondition = new AnimationStopCondition(state);

        for(Animation animation : this.animations) {
            if(!animation.isDone() && !stopCondition.checkAnimation(animation))
                animations.add(animation);
        }
        if(animations.size() < this.animations.size())
            this.animations = animations;
    }

    /**
     * @return a reference to a listener, allowing other classes to start an animation.
     */
    public IAnimationListener getListener() {
        return new IAnimationListener() {
            @Override
            public List<Animation> getAnimations() {
                return animations;
            }

            @Override
            public void startAnimation(Animation animation) {
                if(animations.stream().map(Animation::getId).noneMatch(id -> id == animation.getId()))
                    animations.add(animation);
            }
        };
    }

}
