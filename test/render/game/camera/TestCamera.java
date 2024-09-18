package render.game.camera;

import game.GameObject;
import game.entity.PlayerEntity;
import org.junit.jupiter.api.BeforeEach;
import render.View;
import render.VirtualView;
import util.Dimension;
import util.positions.Pos;

public class TestCamera {

    Camera camera, smallCamera;
    GameObject center;
    Dimension dim;
    View v, smallView;
    int size, columns, lines;

    @BeforeEach
    public void init() {
        center = new PlayerEntity(new Pos(3,3), (pos) -> true, null);
        size = 32;
        columns = 28;
        lines = 22;
        dim = new Dimension(lines, columns);
        v = new VirtualView(size, dim);
        smallView = new VirtualView(size, new Dimension(5,5));
        camera = new Camera(center, v);
        smallCamera = new Camera(center, smallView);
    }


}
