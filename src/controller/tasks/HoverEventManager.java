package controller.tasks;

import game.state.GameState;
import listeners.IHoverListener;
import util.positions.Hitbox;

import java.util.ArrayList;
import java.util.List;

public class HoverEventManager implements Event {

    private List<Hitbox> hoverEvents;
    private final int deselectTime = 100;
    private int counter = 0;

    public HoverEventManager() {
        this.hoverEvents = new ArrayList<>();
    }

    @Override
    public void call(GameState state) {
        if(!hoverEvents.isEmpty()) {
            counter++;
            if(counter >= deselectTime) {
                counter = 0;
                hoverEvents.clear();
            }
        }
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
