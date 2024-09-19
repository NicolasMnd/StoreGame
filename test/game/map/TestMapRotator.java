package game.map;

import game.tile.GameTile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import util.Dimension;
import util.Direction;
import util.Rotator;
import util.positions.Pos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestMapRotator {

    @TempDir
    Path p;

    String stringMap =
            """
            WALL;WALL;GROUND;GROUND;WALL
            WALL;GROUND;GROUND;WALL;GROUND
            GROUND;GROUND;WALL;GROUND;GROUND
            """;
    GameTile[][] tiles;
    Dimension dimensionMap1 = new Dimension(4,3);

    @BeforeEach
    public void init() throws IOException {
        p = p.resolve("mapLocation.txt");
        Files.write(p, stringMap.getBytes());
        MapHandler handler = new MapHandler(p.toString(), 0, () -> 1d);
        tiles = handler.readMap();
    }

    @Test
    public void testRotatePosition_Left() {
        MapRotator rotator = new MapRotator(dimensionMap1);
        Pos pos = new Pos(2, 1);
        System.out.println("Position: " + new Rotator<>(Integer.class).rotatePos(pos, dimensionMap1, Direction.LEFT, 1).getFormat());
        assertEquals(new Pos(1, 2), new Rotator<>(Integer.class).rotatePos(pos, dimensionMap1, Direction.LEFT, 1));
    }

    @Test
    public void testRotate_Left() throws IOException {
        MapRotator rotator = new MapRotator(new Dimension(3, 5));
        GameTile[][] arr = rotator.rotate(tiles, Direction.LEFT, 32);
        assertTrue(
                areEqual(
                        arr,
                        createMap("""
                                  GROUND;WALL;WALL
                                  GROUND;GROUND;WALL
                                  WALL;GROUND;GROUND
                                  GROUND;WALL;GROUND
                                  GROUND;GROUND;WALL
                                  """)
                )
        );
    }

    @Test
    public void testRotate_Right() throws IOException {
        MapRotator rotator = new MapRotator(new Dimension(3, 5));
        GameTile[][] arr = rotator.rotate(tiles, Direction.RIGHT,32);
    }

    @Test
    public void testRotate_North() {
        MapRotator rotator = new MapRotator(new Dimension(3, 3));
        GameTile[][] arr = rotator.rotate(tiles, Direction.NORTH,32);
        assertNull(arr);
    }

    boolean areEqual(GameTile[][] tiles, GameTile[][] tiles2) {
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                if(tiles2.length != tiles.length || tiles2[0].length != tiles[0].length)
                    return false;
                if(!tiles[i][j].getClass().getName().equals(tiles2[i][j].getClass().getName()))
                    return false;
            }
        }
        return true;
    }

    private GameTile[][] createMap(String map) throws IOException {
        Random r = new Random();
        int number = r.nextInt(100000);
        Path p = new File("./mapLocation" + number + ".txt").toPath();
        Files.write(p, map.getBytes());
        MapHandler handler = new MapHandler(p.toString(), 0, () -> 1d);
        System.out.println("");
        return handler.readMap();
    }

    private void printMap(GameTile[][] tiles) {
        for(GameTile[] tile : tiles) {
            for(GameTile t : tile) {
                System.out.print(t.getClass().getName() + ";");
            }
            System.out.println();
        }

    }

    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }

}
