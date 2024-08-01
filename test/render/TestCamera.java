package render;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Dimension;
import util.positions.Pos;

public class TestCamera {

    Camera camera, smallCamera;
    Pos center;
    Dimension dim;
    View v, smallView;
    int size, columns, lines;

    @BeforeEach
    public void init() {
        center = new Pos(3,3);
        size = 32;
        columns = 28;
        lines = 22;
        dim = new Dimension(lines, columns);
        v = new VirtualView(size, dim, center);
        smallView = new VirtualView(size, new Dimension(5,5), new Pos(2,2));
        camera = new Camera(center, v);
        smallCamera = new Camera(center, smallView);
    }

    @Test
    public void testFloor() {

    }

}
