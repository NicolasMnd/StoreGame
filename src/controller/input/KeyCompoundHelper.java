package controller.input;

import util.Direction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyCompoundHelper {

    Map<Direction, char[]> compoundKeys;
    private final JFrame dummy;

    public KeyCompoundHelper() {
        this.compoundKeys = new HashMap<>();
        compoundKeys.put(Direction.NORTH_EAST, new char[] {'z', 'd'});
        compoundKeys.put(Direction.NORTH_WEST, new char[] {'z', 'q'});
        compoundKeys.put(Direction.SOUTH_EAST, new char[] {'s', 'd'});
        compoundKeys.put(Direction.SOUTH_WEST, new char[] {'s', 'q'});
        this.dummy = new JFrame();
    }

    public List<KeyEvent> translateKeys(List<KeyEvent> translate) {
        List<Character> characterList = new ArrayList<>(translate.stream().map(KeyEvent::getKeyChar).toList());

        // For each compound key set we have, we check if the character list contains these characters and we replace them.
        for(Map.Entry<Direction, char[]> mapEntry : compoundKeys.entrySet())
            if(characterList.contains(mapEntry.getValue()[0]) && characterList.contains(mapEntry.getValue()[1]))
                translate = replace(mapEntry.getValue()[0], mapEntry.getValue()[1], DirectionInput.getKeyInput(mapEntry.getKey(), dummy), translate);

        return translate;
    }

    /**
     * Removes character a and b from the list and replaces it with a new {@link KeyEvent}
     * @param a first character
     * @param b second character
     * @param replacement the replacement {@link KeyEvent}
     * @param toTranslate the list in which all the {@link KeyEvent}s are
     * @return a list of {@link KeyEvent} without the keys which characters map to a and b and contains a new {@link KeyEvent}
     */
    List<KeyEvent> replace(char a, char b, KeyEvent replacement, List<KeyEvent> toTranslate) {
        List<KeyEvent> result = new ArrayList<>(
                toTranslate.stream().filter(
                    keyEvents -> !String.valueOf(keyEvents.getKeyChar()).equals(String.valueOf(a))
                                &&
                                 !String.valueOf(keyEvents.getKeyChar()).equals(String.valueOf(b))
                )
                .toList()
        );

        result.add(replacement);
        return result;
    }

    public List<KeyEvent> removeCombination(List<KeyEvent> keyEventList, char releasedCharacter) {
        keyEventList = new ArrayList<>(keyEventList);

        // For each combination, we check if that combination has the released character
        for(Map.Entry<Direction, char[]> mapEntry : compoundKeys.entrySet())
            for(KeyEvent event : keyEventList)
                // If there is an event for which a compound key matches AND the released character is in that event's char array then we replce
                if(isCharCombinationForThisEntry(mapEntry, event.getKeyChar())
                    && (charsEqual(releasedCharacter, mapEntry.getValue()[0]) || charsEqual(releasedCharacter, mapEntry.getValue()[1]))
                ) {
                    System.out.println("Found entry to replace");
                    keyEventList.remove(event);
                    char replaceCharacter = charsEqual(releasedCharacter, mapEntry.getValue()[0]) ? mapEntry.getValue()[1] : mapEntry.getValue()[0];
                    System.out.println("The replaced character will be : " + String.valueOf(replaceCharacter));
                    return replace(event.getKeyChar(), event.getKeyChar(), new KeyEvent(dummy, 1, 20, 1, 10, replaceCharacter), keyEventList);
                }
                else System.out.println("Checking entry " + mapEntry.getKey() + isCharCombinationForThisEntry(mapEntry, event.getKeyChar()) + " plus " + ((charsEqual(releasedCharacter, mapEntry.getValue()[0]) || charsEqual(releasedCharacter, mapEntry.getValue()[1]))));

        return keyEventList;

    }

    /**
     * The characters in the compound map are multiples of 2 for a specified {@link Direction}, however, {@link KeyEvent} can only have
     * one char value. Therefore, the {@link DirectionInput} keys have special character values specified. We will check if the character
     * of the {@link KeyEvent}, possibly a {@link DirectionInput}, represents the char array of a given entry
     * @param compoundEntry the entry for which we check the keyChar is correct
     * @param keyChar the char value of a certain {@link KeyEvent}
     * @return a boolean determining if the compound entry is the same representation as the keyChar
     */
    boolean isCharCombinationForThisEntry(Map.Entry<Direction, char[]> compoundEntry, char keyChar) {
        return switch (keyChar) {
            case DirectionInput.NORTH_EAST -> compoundEntry.getKey() == Direction.NORTH_EAST;
            case DirectionInput.NORTH_WEST -> compoundEntry.getKey() == Direction.NORTH_WEST;
            case DirectionInput.SOUTH_EAST -> compoundEntry.getKey() == Direction.SOUTH_EAST;
            case DirectionInput.SOUTH_WEST -> compoundEntry.getKey() == Direction.SOUTH_WEST;
            default -> false;
        };
    }

    private boolean charsEqual(char a, char b) {
        return String.valueOf(a).equals(String.valueOf(b));
    }

}
