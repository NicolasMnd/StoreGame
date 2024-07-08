package game.map;

import game.container.Container;
import game.tile.TileGround;
import game.tile.TileShelf;
import game.tile.TileWall;
import listeners.IContainerInteraction;
import listeners.IContainerNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Direction;
import util.Pos;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTileReader {

    TileReader reader;
    IContainerNotifier notifier;
    List<Container> containers;

    @BeforeEach
    public void init() {
        containers = new ArrayList<>();
        notifier = setNotifier();
        this.reader = new TileReader(notifier);
    }

    @Test
    public void testReadShelf_CorrectInstance() {
        assertInstanceOf(TileShelf.class, reader.getTileFor("shelf_or:n_cc:aa", new Pos(0,0)));
        assertInstanceOf(TileGround.class, reader.getTileFor("ground", new Pos(0,0)));
        assertInstanceOf(TileWall.class, reader.getTileFor("wall", new Pos(0,0)));
        assertNull(reader.getTileFor("notile", new Pos(0,0)));

    }

    @Test
    public void testReadShelf_CorrectFacing() {
        assertEquals(reader.getTileFor("shelf_or:n_cc:aa", new Pos(0,0)).getFacing(), Direction.NORTH);
        assertEquals(reader.getTileFor("shelf_or:s_cc:aa", new Pos(0,0)).getFacing(), Direction.SOUTH);
        assertEquals(reader.getTileFor("shelf_or:e_cc:aa", new Pos(0,0)).getFacing(), Direction.EAST);
        assertEquals(reader.getTileFor("shelf_or:w_cc:aa", new Pos(0,0)).getFacing(), Direction.WEST);
    }

    @Test
    public void testReadShelf_CorrectPosition() {
        assertEquals(reader.getTileFor("shelf_or:n_cc:aa", new Pos(0,0)).getPosition(), new Pos(0,0));
    }

    @Test
    public void testContainerAdded() {
        String containerCode = "aa";
        assertEquals(this.containers.size(), 0);
        reader.getTileFor("shelf_or:n_cc:" + containerCode, new Pos(0,0));
        assertEquals(this.containers.size(), 1);
        assertEquals(this.containers.get(0).getContainerCode(), containerCode);
    }

    @Test
    public void testGetValue_KeyExists() {
        assertEquals(reader.getValue("test_mister:tee", "mister", "ok"), "tee");
    }

    @Test
    public void testGetValue_KeyNonExisting() {
        assertEquals(reader.getValue("test_mister:tee", "misterd", "ok"), "ok");
    }


    private IContainerNotifier setNotifier() {
        return containerCode -> {
            Container newContainer = null;
            if(containers.stream().noneMatch(c -> c.getContainerCode().equals(containerCode))) {
                newContainer = new Container(containerCode);
                this.containers.add(newContainer);
            }
            else
                newContainer = containers.stream().filter(c -> c.getContainerCode().equals(containerCode)).toList().get(0);
            return newContainer.getInteractor();
        };
    }

}
