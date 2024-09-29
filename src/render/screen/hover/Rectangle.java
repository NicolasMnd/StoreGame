package render.screen.hover;

import util.positions.Hitbox;

import java.awt.*;

public class Rectangle {

    private Color color = Color.RED;
    private final Hitbox box;

    public Rectangle(Hitbox box) {
        this.box = box;
    }

    public Rectangle setColor(Color color) {
        this.color = color;
        return this;
    }

    public Hitbox getHitbox() {
        return this.box;
    }

}
