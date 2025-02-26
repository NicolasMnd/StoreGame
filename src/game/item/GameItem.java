package game.item;

import game.GameObject;

public abstract class GameItem extends GameObject {

    private final String id;

    public GameItem(String id) {
        super(null);
        this.id = id;
    }

    /**
     * @return the item id
     */
    public String getId() {
        return new String(this.id);
    }

}
