package game.map;

import game.tile.GameTile;
import game.tile.TileGround;
import game.tile.TileWall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import util.Dimension;
import util.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMapRotator {

    @TempDir
    Path p;

    String stringMap =
            """
            WALL;WALL;GROUND;GROUND
            WALL;GROUND;GROUND;GROUND
            GROUND;GROUND;GROUND;GROUND
            """;
    GameTile[][] tiles;

    @BeforeEach
    public void init() throws IOException {
        p = p.resolve("mapLocation.txt");
        Files.write(p, stringMap.getBytes());
        MapHandler handler = new MapHandler(p.toString(), 0, () -> 1d);
        tiles = handler.readMap();
    }

    @Test
    public void testRotate_Left() {
        MapRotator rotator = new MapRotator(new Dimension(3, 4));
        GameTile[][] arr = rotator.rotate(tiles, Direction.LEFT, 32);
        assertTrue(arr[0][0] instanceof TileGround);
        assertTrue(arr[0][1] instanceof TileWall);
        assertTrue(arr[0][2] instanceof TileWall);
    }

    @Test
    public void testRotate_Right() {
        MapRotator rotator = new MapRotator(new Dimension(3, 4));
        GameTile[][] arr = rotator.rotate(tiles, Direction.RIGHT,32);
        assertTrue(arr[0][0] instanceof TileGround);
        assertTrue(arr[0][1] instanceof TileGround);
        assertTrue(arr[0][2] instanceof TileGround);
    }

    @Test
    public void testRotate_North() {
        MapRotator rotator = new MapRotator(new Dimension(3, 3));
        GameTile[][] arr = rotator.rotate(tiles, Direction.NORTH,32);
        assertNull(arr);
    }

    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }

}
