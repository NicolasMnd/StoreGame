package render;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Dimension;
import util.Pos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCamera {

    Camera camera;
    Pos center;
    Dimension dim;
    int size, columns, lines;

    @BeforeEach
    public void init() {
        center = new Pos(3,3);
        size = 32;
        columns = 28;
        lines = 22;
        dim = new Dimension(lines, columns);
        View v = new VirtualView(size, dim, center);
        camera = new Camera(center, v);
    }

    @Test
    public void testFloor() {
        assertEquals(camera.floor(539, size), 16);
        assertEquals(camera.floor(542, size), 16);
    }

}
