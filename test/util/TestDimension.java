package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDimension {

    Dimension d;

    @BeforeEach
    public void init() {
        d = new Dimension(1, 10);
    }

    @Test
    public void testDimension_Variables() {
        assertEquals(d.getHeight(), 1);
        assertEquals(d.getWidth(), 10);
    }

    @Test
    public void testDimension_Format() {
        assertEquals(d.getFormat(), "[l:" + d.getHeight() + ", c:" + d.getWidth() + "]");
    }

    @Test
    public void testEquals() {
        assertTrue(d.equals(new Dimension(1, 10)));
        assertFalse(d.equals(new Dimension(1, 11)));
        assertFalse(d.equals(new Dimension(11, 11)));
        assertFalse(d.equals("ello misters"));
    }

}
