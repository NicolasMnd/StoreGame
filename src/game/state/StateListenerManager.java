package game.state;

import listeners.IAnimationListener;
import listeners.IGameSizeListener;
import listeners.IHoverListener;
import util.positions.Hitbox;
import util.texture.animation.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * For a {@link GameState}, all event registering and getters are here.
 */
public class StateListenerManager {

    final IGameSizeListener gameSizeListener;
    private List<IAnimationListener> animationListeners;
    private List<IHoverListener> hoverListeners;

    public StateListenerManager(IGameSizeListener gameSizeListener) {
        this.gameSizeListener = gameSizeListener;
        this.animationListeners = new ArrayList<>();
        this.hoverListeners = new ArrayList<>();
    }

    /**
     * Notifies to render a {@link render.game.load.RenderAnimations}
     * @param animation the animation to be rendered
     */
    public void startAnimation(Animation animation) {
        for(IAnimationListener listener : this.animationListeners)
            listener.startAnimation(animation);
    }

    public List<Animation> getAnimations() {
        for(IAnimationListener listener : this.animationListeners)
            return listener.getAnimations();
        return null;
    }

    public List<Hitbox> getHovers() {
        for(IHoverListener listener : this.hoverListeners)
            return listener.getHoveredItems();
        return null;
    }

    /**
     * Lets this object pass animations to the {@link controller.GameFacade}
     * @param listener the listener
     */
    public void subscribeAnimationListener(IAnimationListener listener) {
        this.animationListeners.add(listener);
    }

    public void subscribeHoverListener(IHoverListener listener) {
        this.hoverListeners.add(listener);
    }

}
