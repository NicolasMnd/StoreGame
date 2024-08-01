package controller.tasks;

import game.GameObject;
import game.state.GameState;
import game.property.PropertyTickable;
import game.property.PropertyType;

class TickTask implements Task {

    @Override
    public void call(GameState state) {

        for(GameObject[] objArr : state.getTiles())
            for(GameObject obj : objArr)
                if(obj.getProperties().getProperty(PropertyType.TICKABLE) != null)
                    ((PropertyTickable) obj.getProperties().getProperty(PropertyType.TICKABLE)).execute();

        if(state.getPlayer().getProperties().getProperty(PropertyType.TICKABLE) != null)
            ((PropertyTickable) state.getPlayer().getProperties().getProperty(PropertyType.TICKABLE)).execute();

    }

}
