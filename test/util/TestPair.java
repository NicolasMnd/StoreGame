package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPair {

    Pair<Integer, String> pair;

    @BeforeEach
    public void init() {
        this.pair = new Pair<>(1, "hello");
    }

    @Test
    public void testGetFirst() {
        assertEquals(pair.getFirst(), 1);
    }

    @Test
    public void testGetSecond() {
        assertEquals(pair.getSecond(), "hello");
    }

    @Test
    public void testToString() {
        assertEquals(pair.toString(), "<1, hello>");
    }

    @Test
    public void testEquals() {
        assertEquals(pair, new Pair<>(1, "hello"));
        assertNotEquals(pair, new Pair<>(2, "hello"));
        assertNotEquals(pair, new Pair<>(1, "hello1"));
        assertNotEquals(pair, "hello mister");
    }

}
