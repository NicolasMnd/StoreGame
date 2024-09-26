package game.inventory;

import game.item.GameItem;
import game.item.vegetables.ItemTomato;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Inventory {

    private Stack<GameItem> items;
    private GameItem lefthand, righthand;

    public Inventory() {
        this.items = new Stack<>();
        this.lefthand = new ItemTomato();
        this.righthand = new ItemTomato();
    }

    public GameItem getItemLefthand() {
        return lefthand;
    }

    public GameItem getItemRighthand() {
        return this.righthand;
    }

    public void selectItem() {
        System.out.println("Select");
        if(this.lefthand != null) {
            items.add(this.lefthand);
            this.lefthand = null;
        } else
            lefthand = items.pop();
    }



}
