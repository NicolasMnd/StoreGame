package controller.tasks;

import game.state.GameState;

import java.util.List;

public class TaskManager {

    public AnimationEventManager animationEventManager;
    public HoverEventManager hoverEventManager;
    private List<Event> events;

    public TaskManager() {
        animationEventManager = new AnimationEventManager();
        hoverEventManager = new HoverEventManager();
        this.events = List.of(
                new TickEvent(),
                animationEventManager,
                hoverEventManager
        );
    }

    public void processEvents(GameState state) {

        for(Event event : events) {
            event.call(state);
        }

    }

}
