package game.map;

import listeners.IContainerNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Pair;

import java.util.List;

public class TileReaderTest {

    TileReader reader;
    List<Pair<Integer, Integer>> addedcontainer;

    @BeforeEach
    public void init() {
        reader = new TileReader(getNotifier(), () -> 1d);
    }

    @Test
    public void testReadTextures() {



    }

    private IContainerNotifier getNotifier() {
        return null;
    }

}
