package listeners;

import game.item.GameItem;

public interface IContainerInteraction {

    GameItem removeItem(String id);

    void addItem(GameItem item);

    GameItem[] getContainerItems();

}
