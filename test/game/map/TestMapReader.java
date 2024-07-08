package game.map;

import game.item.ItemTomato;
import game.property.PropertyContainer;
import game.property.PropertyType;
import game.tile.GameTile;
import listeners.IContainerInteraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMapReader {

    @TempDir
    Path p;

    String stringMap =
            """
            WALL;WALL;GROUND
            WALL;shelf_or:n_cc:aa;GROUND
            GROUND;GROUND;GROUND
            """;
    GameTile[][] tiles;
    MapHandler handler;

    @BeforeEach
    public void init() throws IOException {
        p = p.resolve("mapLocation.txt");
        Files.write(p, stringMap.getBytes());
        handler = new MapHandler(p.toString(), 0);
        tiles = handler.readMap();
    }

    @Test
    public void testContainer_Interactor_AddItem() {
        IContainerInteraction interactor = getProperty(tiles[1][1]).getContainerInteracter();
        interactor.addItem(new DummyItem(1));
        assertEquals(handler.getContainers()[0].getItems().get(0), new DummyItem(1));
    }

    @Test
    public void testContainer_Interactor_RemoveItem() {
        IContainerInteraction interactor = getProperty(tiles[1][1]).getContainerInteracter();
        interactor.addItem(new DummyItem(1));
        interactor.removeItem(new DummyItem(1).getId());
        assertEquals(handler.getContainers()[0].getItems().size(), 0);
    }

    @Test
    public void testContainer_Interactor_GetItems() {
        IContainerInteraction interactor = getProperty(tiles[1][1]).getContainerInteracter();
        interactor.addItem(new DummyItem(1));
        interactor.addItem(new DummyItem(1));
        assertEquals(handler.getContainers()[0].getItems().size(), 2);
    }

    @Test
    public void testContainer_Interactor_GetItems_Zero() {
        IContainerInteraction interactor = getProperty(tiles[1][1]).getContainerInteracter();
        assertEquals(handler.getContainers()[0].getItems().size(), 0);
    }

    @Test
    public void testContainer_Interactor_GetItems_Equal() {
        IContainerInteraction interactor = getProperty(tiles[1][1]).getContainerInteracter();
        interactor.addItem(new DummyItem(1));
        assertEquals(handler.getContainers()[0].getItems().get(0), interactor.getContainerItems()[0]);
    }

    private PropertyContainer getProperty(GameTile tile) {
        return ((PropertyContainer) tile.getProperties().getProperty(PropertyType.STOCK_HOLDER));
    }

    private static class DummyItem extends ItemTomato {
        private final int code;
        public DummyItem(int code) {
            super();
            this.code = code;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof DummyItem d)
                return d.code == this.code;
            return false;
        }
    }


    @AfterEach
    @EnabledOnOs(OS.WINDOWS)
    void cleanUp() {
        System.gc();
    }

}
