package game;

import util.Pos;

public abstract class GameObject {

    private Pos pos;

    public GameObject(Pos pos, String texture) {

    }

    public abstract void updatePosition(Pos pos);

}
