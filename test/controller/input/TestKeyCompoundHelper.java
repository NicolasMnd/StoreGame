package controller.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKeyCompoundHelper {

    Component c;
    KeyCompoundHelper helper;

    @BeforeEach
    public void init() {
        c = new JFrame();
        helper = new KeyCompoundHelper();
    }

    @Test
    public void test_NorthWest() throws AWTException {
        List<KeyEvent> events = List.of(
                new KeyEvent(c, 1, 20, 1, 10, 'q'),
                new KeyEvent(c, 1, 20, 1, 10, 'z')
        );
        KeyCompoundHelper keyCompoundHelper = new KeyCompoundHelper();
        List<KeyEvent> compounded = keyCompoundHelper.translateKeys(events);
        assertEquals(compounded.getFirst().getKeyChar(), DirectionInput.NORTH_WEST);
    }

    @Test
    public void test_NorthEast() throws AWTException {
        List<KeyEvent> events = List.of(
                new KeyEvent(c, 1, 20, 1, 10, 'z'),
                new KeyEvent(c, 1, 20, 1, 10, 'd')
        );
        KeyCompoundHelper keyCompoundHelper = new KeyCompoundHelper();
        List<KeyEvent> compounded = keyCompoundHelper.translateKeys(events);
        assertEquals(compounded.getFirst().getKeyChar(), DirectionInput.NORTH_EAST);
    }

    @Test
    public void test_SouthWest() throws AWTException {
        List<KeyEvent> events = List.of(
                new KeyEvent(c, 1, 20, 1, 10, 's'),
                new KeyEvent(c, 1, 20, 1, 10, 'q')
        );
        KeyCompoundHelper keyCompoundHelper = new KeyCompoundHelper();
        List<KeyEvent> compounded = keyCompoundHelper.translateKeys(events);
        assertEquals(compounded.getFirst().getKeyChar(), DirectionInput.SOUTH_WEST);
    }

    @Test
    public void test_SouthEast() throws AWTException {
        List<KeyEvent> events = List.of(
                new KeyEvent(c, 1, 20, 1, 10, 'd'),
                new KeyEvent(c, 1, 20, 1, 10, 's')
        );
        KeyCompoundHelper keyCompoundHelper = new KeyCompoundHelper();
        List<KeyEvent> compounded = keyCompoundHelper.translateKeys(events);
        assertEquals(compounded.getFirst().getKeyChar(), DirectionInput.SOUTH_EAST);
    }

    @Test
    public void test_RemoveCombination_NE_dGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.NORTH_EAST, c)
        );
        events = helper.removeCombination(events, 'd');

        assertEquals(events.getFirst().getKeyChar(), 'z');
    }

    @Test
    public void test_RemoveCombination_NE_zGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.NORTH_EAST, c)
        );
        events = helper.removeCombination(events, 'z');

        assertEquals(events.getFirst().getKeyChar(), 'd');
    }

    @Test
    public void test_RemoveCombination_NW_qGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.NORTH_WEST, c)
        );
        events = helper.removeCombination(events, 'q');

        assertEquals(events.getFirst().getKeyChar(), 'z');
    }

    @Test
    public void test_RemoveCombination_NW_zGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.NORTH_WEST, c)
        );
        events = helper.removeCombination(events, 'z');

        assertEquals(events.getFirst().getKeyChar(), 'q');
    }

    @Test
    public void test_RemoveCombination_SE_sGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.SOUTH_EAST, c)
        );
        events = helper.removeCombination(events, 's');

        assertEquals(events.getFirst().getKeyChar(), 'd');
    }

    @Test
    public void test_RemoveCombination_SE_dGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.SOUTH_EAST, c)
        );
        events = helper.removeCombination(events, 'd');

        assertEquals(events.getFirst().getKeyChar(), 's');
    }

    @Test
    public void test_RemoveCombination_SW_sGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.SOUTH_WEST, c)
        );
        events = helper.removeCombination(events, 's');

        assertEquals(events.getFirst().getKeyChar(), 'q');
    }

    @Test
    public void test_RemoveCombination_SW_qGone() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.SOUTH_WEST, c)
        );
        events = helper.removeCombination(events, 'q');

        assertEquals(events.getFirst().getKeyChar(), 's');
    }

    @Test
    public void test_removeCombination_MultipleActives() {
        List<KeyEvent> events = List.of(
                DirectionInput.getKeyInput(Direction.SOUTH_WEST, c),
                new KeyEvent(c, 1, 20, 1, 10, 'z')
        );
        events = helper.removeCombination(events, 'q');

        assertEquals(events.size(), 2);
        assertEquals(events
                .stream()
                .map(KeyEvent::getKeyChar)
                .map(String::valueOf)
                .collect(Collectors.joining(",")),

                "z,s");
    }

}
