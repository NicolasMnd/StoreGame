package game;

import util.Direction;
import util.texture.comp.TextureHolder;

public class GameObjectBuilder {

    private final GameObject o;

    public GameObjectBuilder(GameObject o) {
        this.o = o;
    }

    public GameObject getFinishedObject() {
        return this.o;
    }

    public void addTexture(TextureHolder textureHolder) {
        this.o.setTexture(textureHolder);
    }

    public void setFacing(Direction facing) {
        this.o.setFacing(facing);
    }

}
