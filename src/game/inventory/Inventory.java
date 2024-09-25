package game.inventory;

import game.item.GameItem;
import game.item.vegetables.ItemTomato;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<GameItem> items;
    private GameItem lefthand, righthand;

    public Inventory() {
        this.items = new ArrayList<GameItem>();
        this.lefthand = new ItemTomato();
        this.righthand = new ItemTomato();
    }

    public GameItem getItemLefthand() {
        return lefthand;
    }

    public GameItem getItemRighthand() {
        return this.righthand;
    }

}
