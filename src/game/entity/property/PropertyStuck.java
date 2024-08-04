package game.entity.property;

import game.entity.Entity;

public class PropertyStuck {

    private int tries = 0;

    public PropertyStuck(Entity e) {

    }

    public void tryMove() {
        tries++;
    }

}
