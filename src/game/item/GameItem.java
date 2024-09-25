package game.item;

import game.GameObject;
import util.positions.Pos;

public abstract class GameItem extends GameObject {

    private final String id;

    public GameItem(String id) {
        super(new Pos(0,0));
        this.id = id;
        this.setHeight(32);
        this.setWidth(32);
    }

    /**
     * @return the Y offset where the texture begins
     */
    public abstract int getYStart();

    /**
     * @return the item id
     */
    public String getId() {
        return new String(this.id);
    }

}
