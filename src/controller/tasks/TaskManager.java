package controller.tasks;

import game.state.GameState;

import java.util.List;

public class TaskManager {

    public AnimationEventManager animationEventManager;
    private List<Event> events;

    public TaskManager() {
        animationEventManager = new AnimationEventManager();
        this.events = List.of(
                new TickEvent(),
                animationEventManager
        );
    }

    public void processEvents(GameState state) {

        for(Event event : events) {
            event.call(state);
        }

    }

}
