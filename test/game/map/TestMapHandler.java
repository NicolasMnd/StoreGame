package game.map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import util.Dimension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMapHandler {

    @TempDir
    Path path1;

    MapHandler handler;

    @BeforeEach
    public void init() throws IOException {
        path1 = path1.resolve("test.txt");
        Files.write(path1,
                """
                WALL;WALL;WALL;WALL;WALL;WALL;WALL;WALL;WALL;WALL;WALL
                WALL;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND
                WALL;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND
                WALL;GROUND;SHELF_or:or:N_CC:aa;SHELF_or:N_CC:aa;SHELF_or:N_CC:aa;SHELF_or:N_CC:aa;SHELF_or:N_CC:ab;SHELF_or:N_CC:ab;SHELF_or:N_CC:ab;SHELF_or:N_CC:ab;GROUND;GROUND
                WALL;GROUND;SHELF_or:S_CC:ac;SHELF_or:S_CC:ac;SHELF_or:S_CC:ac;SHELF_or:S_CC:ac;SHELF_or:S_CC:ad;SHELF_or:S_CC:ad;SHELF_or:S_CC:ad;SHELF_or:S_CC:ad;GROUND;GROUND
                WALL;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND;GROUND
                """.getBytes());
        handler = new MapHandler(path1.toString());
    }

    @Test
    public void testReadMap() {

    }

    @Test
    public void testReadDimensions() {
        assertEquals(handler.getMapDimensions(path1.toString()), new Dimension(6, 12));
    }

    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }

}
