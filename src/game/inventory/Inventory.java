package game.inventory;

import game.item.GameItem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<GameItem> items;

    public Inventory() {
        this.items = new ArrayList<GameItem>();
    }

}
