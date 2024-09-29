package game.state;

import game.entity.types.PlayerEntity;
import listeners.IAnimationListener;
import util.positions.Pos;
import util.texture.animation.Animation;

import java.util.List;

public class PlayerLocationLoader extends StateObject {

    @Override
    public String setKey() {
        return "playerLocation";
    }

    @Override
    public void load(GameState state) {
        int x = Integer.parseInt(getEntry().split(",")[0]);
        int y = Integer.parseInt(getEntry().split(",")[1]);
        state.player = new PlayerEntity(new Pos(x, y), state.getMoveChecker(), getListener(state));
    }

    @Override
    public void save(GameState state) {
        String positionString = state.getPlayer().getPosition().x() + "," + state.getPlayer().getPosition().y();
        this.replaceEntry(positionString);
    }

    private IAnimationListener getListener(GameState state) {
        return new IAnimationListener() {
            @Override
            public void startAnimation(Animation animation) {
                state.getListenerManager().startAnimation(animation);
            }

            @Override
            public List<Animation> getAnimations() {
                return state.getListenerManager().getAnimations();
            }
        };
    }

}
