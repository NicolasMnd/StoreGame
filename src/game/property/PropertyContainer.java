package game.property;

import game.GameObject;
import listeners.IContainerInteraction;

public class PropertyContainer extends Property {

    /**
     * The {@link GameObject} that has a container
     */
    private final GameObject objects;
    /**
     * The code of the {@link game.container.Container}
     */
    private final IContainerInteraction containerInteractor;

    public PropertyContainer(GameObject object, IContainerInteraction containerInteractor) {
        super(PropertyType.STOCK_HOLDER);
        this.objects = object;
        this.containerInteractor = containerInteractor;
    }



}
