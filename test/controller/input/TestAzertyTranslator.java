package controller.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAzertyTranslator {

    Component c;

    @BeforeEach
    public void init() {
        c = new JFrame();
    }

    @Test
    public void testSame() {
        assertEquals('q',
                new AzertyTranslator().translateKey(new KeyEvent(c, 1, 20, 1, 10, 'q')).getKeyChar());
    }

    @Test
    public void testSame23() {
        assertEquals('a',
                new AzertyTranslator().translateKey(new KeyEvent(c, 1, 20, 1, 10, 'a')).getKeyChar());
    }

}
