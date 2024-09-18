package listeners;

import util.texture.animation.Animation;

import java.util.List;

public interface IAnimationListener {

    void startAnimation(Animation animation);

    List<Animation> getAnimations();

}
