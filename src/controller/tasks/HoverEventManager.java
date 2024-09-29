package controller.tasks;

import game.state.GameState;
import listeners.IHoverListener;
import util.positions.Hitbox;

import java.util.ArrayList;
import java.util.List;

public class HoverEventManager implements Event {

    private List<Hitbox> hoverEvents;

    public HoverEventManager() {
        this.hoverEvents = new ArrayList<>();
    }

    @Override
    public void call(GameState state) {

    }

    public void addHover(Hitbox hitbox) {
        hoverEvents.clear();
        if(hitbox == null) {
            return;
        }
        this.hoverEvents.add(hitbox);
    }

    public IHoverListener getListener() {
        return () -> hoverEvents;

    }

}
